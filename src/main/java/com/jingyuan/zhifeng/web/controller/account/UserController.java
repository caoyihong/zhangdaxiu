package com.jingyuan.zhifeng.web.controller.account;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.ReturnDatas;
import com.jingyuan.zhifeng.core.utils.UserRealm;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.service.UserService;
import com.jingyuan.zhifeng.web.controller.BaseController;


@Controller
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	/**
	 * 管理员注册页面
	 * @param request
	 * @return
	 */
	@RequestMapping("submit")
	public String submit(HttpServletRequest request)
	{
		request.setAttribute("formToken", this.resetToken(request));
		Subject s = SecurityUtils.getSubject();
		if(s.isAuthenticated())
		{
			return "redirect:/";
		}
		return "account/submit";
	}
	
	/**
	 * 管理员注册
	 * @param request
	 * @param user
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value="submit",method=RequestMethod.POST)
	public String normalSubmit(HttpServletRequest request,HttpSession session,@ModelAttribute SysAdmin admin)
	{
		if (isTokenValid(request, true)) {
			Subject s = SecurityUtils.getSubject();
			if(s.isAuthenticated())
			{
				return "redirect:/";
			}
//		三类用户：个人用户、企业用户、中介机构从业人员用户
//		根据type来区分，执行不同的注册步骤	1是个人用户 2是企业用户
			String formType = request.getParameter("formType");
			String name = admin.getName();
//		前端需要检查密码和确认密码是否一致
			if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(user.getPassword()))
			{
				//企业用户需要判断行业是否为空
				if (formType.equals("2")) {
					if (StringUtils.isEmpty(user.getIndustry())) {
						return "error";
					}
				}
				
				//根据用户名和手机号查询用户是否已经注册过
				ZFUser user2 = (ZFUser) userService.isExistName(user.getName(),null, Integer.parseInt(formType));
				if (user2 == null) {
					user2 = (ZFUser) userService.isExistPhone(phone,null, Integer.parseInt(formType));
					if (user2 != null) {
						return "error";
					}
				}else {
					return "error";
				}
				
				user.setType(Integer.parseInt(formType));
				user.setHead("images/user-images/user-pic.png");
				user.setStatus(GlobalStatic.USER_NAUTHEN);
//			新建用户登录信息token
				UsernamePasswordToken token = new UsernamePasswordToken(user.getName() + "," + formType,user.getPassword());
				userService.insertPersonUser(user);
				
//			手动登录
				s.login(token);
//			登录成功后将当前用户信息放入缓存中
				UserRealm.ShiroUser currentUser = (UserRealm.ShiroUser) s.getPrincipal();
				logger.info(currentUser.toString());
				session.setAttribute("currentUser",currentUser);
				return "success";
			}
		}
		return "error";
	}

	
	/**
	 * 三种登录同一个登陆页面
	 * @return
	 */
	@RequestMapping(value="adminLogin",method=RequestMethod.GET)
	public String login(HttpServletRequest request)
	{
		request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas());
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/";
		}
		
		return "account/login";
	}
	
	/**
	 * 登陆请求	三种用户使用同一个action,用type区分
	 * @param request
	 * @param value
	 * @param type	1 个人用户和企业用户	2中介机构用户
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginPost(HttpServletRequest request,HttpSession session,@RequestParam String username,@RequestParam String logintype,@RequestParam String password)
	{
		request.setAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				username);
		request.setAttribute(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM,
				password);
		// ****************************token-begin**************************************//
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username +"," + logintype,
				password);

		if (WebUtils.isTrue(request, "rememberMe")) {
			token.setRememberMe(true);
		} else {
			token.setRememberMe(false);
		}
		try {
			// 会调用 shiroDbRealm 的认证方法
			// UserRealm.doGetAuthenticationInfo(AuthenticationToken)
			subject.login(token);
		} catch (UnknownAccountException uae) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","账号不存在!"));
			return "account/login";
		} catch (IncorrectCredentialsException ice) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","密码错误!"));
			return "account/login";
		} catch (LockedAccountException lae) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","账号被锁定!"));
			return "account/login";
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","未知错误,请联系管理员."));
			e.printStackTrace();
			return "account/login";
		}
		// ****************************跳转前面链接**************************************//

		// 将用户id放入session，方便通用头使用
		UserRealm.ShiroUser currentUser = (UserRealm.ShiroUser) SecurityUtils
				.getSubject().getPrincipal();
		session.setAttribute("currentUser", currentUser);
		String successUrl = "/";
		SavedRequest savedRequest = (SavedRequest) session
				.getAttribute(GlobalStatic.SAVED_REQUEST_KEY);
		if (savedRequest != null
				&& savedRequest.getMethod().equalsIgnoreCase(
						AccessControlFilter.GET_METHOD)) {
			successUrl = savedRequest.getRequestUrl();

			if (successUrl == null || !chekSavedUrl(successUrl)) {
				successUrl = "/";
			}

		}
		request.removeAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		request.removeAttribute(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM);
		return redirect + successUrl;
	}
	
}
