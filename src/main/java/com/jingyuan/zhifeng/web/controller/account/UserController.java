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

import com.jingyuan.zhifeng.core.constant.UserType;
import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.ReturnDatas;
import com.jingyuan.zhifeng.core.utils.UserRealm;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.service.UserService;
import com.jingyuan.zhifeng.web.controller.BaseController;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 管理员注册页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("submit")
	public String submit(HttpServletRequest request) {
		request.setAttribute("formToken", this.resetToken(request));
		Subject s = SecurityUtils.getSubject();
		if (s.isAuthenticated()) {
			return "redirect:/admin/index";
		}
		return "account/submit";
	}

	/**
	 * 管理员注册
	 * 
	 * @param request
	 * @param user
	 * @return
	 * @author wangxu
	 */
	
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public String normalSubmit(HttpServletRequest request, HttpSession session,
			@ModelAttribute SysAdmin admin) {
		Subject s = SecurityUtils.getSubject();
		if (s.isAuthenticated()) {
			return "redirect:/admin/index";
		}
		String name = admin.getName();
		String password = admin.getPassword();
		String des = admin.getDecription();
		if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(password) && StringUtils.isNoneEmpty(des)) {
			
			if (userService.isExistUser(name, password, UserType.SYSADMIN.getType()) != null) {
				request.setAttribute("errorMsg", "用户已存在");
				return "account/submit";
			}

			// 新建用户登录信息token
			UsernamePasswordToken token = new UsernamePasswordToken(
					admin.getName() + "," + UserType.SYSADMIN.getType(), admin.getPassword());
			userService.insertAdmin(admin);

			// 手动登录
			s.login(token);
			// 登录成功后将当前用户信息放入缓存中
			UserRealm.ShiroUser currentUser = (UserRealm.ShiroUser) s
					.getPrincipal();
			logger.info(currentUser.toString());
			session.setAttribute("currentUser", currentUser);
			return "redirect:/admin/index";
		}
		request.setAttribute("errorMsg", "表单信息不全");
		return "account/submit";
	}

	/**
	 * 三种登录同一个登陆页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
			return "redirect:" + UserType.getUrl(user.getType()) + "/index";
		}

		return "account/login";
	}

	/**
	 * 登陆请求 三种用户使用同一个action,用type区分
	 * 
	 * @param request
	 * @param value
	 * @param type  0-学生，1-老师，2-管理员
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, HttpSession session,
			@RequestParam String username, @RequestParam String logintype,
			@RequestParam String password) {
		request.setAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				username);
		request.setAttribute(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM,
				password);
		// ****************************token-begin**************************************//
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(username + ","
				+ logintype, password);

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
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("",
					"账号不存在!"));
			return "account/login";
		} catch (IncorrectCredentialsException ice) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("",
					"密码错误!"));
			return "account/login";
		} catch (LockedAccountException lae) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("",
					"账号被锁定!"));
			return "account/login";
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("",
					"未知错误,请联系管理员."));
			e.printStackTrace();
			return "account/login";
		}
		// ****************************跳转前面链接**************************************//

		// 将用户id放入session，方便通用头使用
		UserRealm.ShiroUser currentUser = (UserRealm.ShiroUser) SecurityUtils
				.getSubject().getPrincipal();
		session.setAttribute("currentUser", currentUser);
		String successUrl = null;
		SavedRequest savedRequest = (SavedRequest) session
				.getAttribute(GlobalStatic.SAVED_REQUEST_KEY);
		if (savedRequest != null
				&& savedRequest.getMethod().equalsIgnoreCase(
						AccessControlFilter.GET_METHOD)) {
			successUrl = savedRequest.getRequestUrl();

		}
		if (successUrl == null || !chekSavedUrl(successUrl)) {
			successUrl = UserType.getUrl(currentUser.getType()) + "/index";
		}
		request.removeAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		request.removeAttribute(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM);
		System.out.println(successUrl);
		return redirect + successUrl;
	}

}
