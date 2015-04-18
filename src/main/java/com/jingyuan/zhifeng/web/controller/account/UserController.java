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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingyuan.zhifeng.core.utils.FileUtil;
import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.MessageUtil;
import com.jingyuan.zhifeng.core.utils.ReturnDatas;
import com.jingyuan.zhifeng.core.utils.UserRealm;
import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFUser;
import com.jingyuan.zhifeng.service.ZFAgencyEmployeeService;
import com.jingyuan.zhifeng.service.ZFAgencyService;
import com.jingyuan.zhifeng.service.ZFUserService;
import com.jingyuan.zhifeng.web.controller.BaseController;


@Controller
public class UserController extends BaseController{
	
	@Autowired
	private ZFAgencyService agencyService;
	
	@Autowired
	private ZFUserService userService;
	
	@Autowired
	private ZFAgencyEmployeeService agencyEmployeeService;
	
	/**
	 * 普通用户、从业人员用户注册页面
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
	 * 普通用户 企业用户注册
	 * @param request
	 * @param user
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value="normalsubmit",method=RequestMethod.POST)
	public String normalSubmit(HttpServletRequest request,HttpSession session,@ModelAttribute ZFUser user)
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
			String name = user.getName();
			String phone = user.getPhone();
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
	 * 中介机构用户注册
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value="agencysubmit",method=RequestMethod.POST)
	public String AgencySubmit(HttpServletRequest request,HttpSession session,@ModelAttribute ZFAgencyemployee agencyemployee)
	{
		if (isTokenValid(request, true)) {
			Subject s = SecurityUtils.getSubject();
			if(s.isAuthenticated())
			{
				return "redirect:/";
			}
			String name = agencyemployee.getName();
			String phone = agencyemployee.getPhone();
			if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(agencyemployee.getPassword()))
			{
				ZFAgencyemployee agencyemployee2 = (ZFAgencyemployee) userService.isExistName(name,null, 2);
				if (agencyemployee2 == null) {
					agencyemployee2 = (ZFAgencyemployee) userService.isExistPhone(phone,null, 2);
					if (agencyemployee2 != null) {
						return "error";
					}
				}else {
					return "error";
				}
				agencyemployee.setHead("images/user-images/user-pic.png");
				agencyemployee.setStatus(GlobalStatic.USER_NAUTHEN);
//			新建用户登录信息token
				UsernamePasswordToken token = new UsernamePasswordToken(agencyemployee.getName() + ",2",agencyemployee.getPassword());
				agencyEmployeeService.insertEmployee(agencyemployee);
				
				
//			手动登录
				s.login(token);
//			登录成功后将当前用户信息放入缓存中
				UserRealm.ShiroUser currentUser = (UserRealm.ShiroUser) s.getPrincipal();
				logger.info(currentUser.toString());
				session.setAttribute("currentUser",currentUser);
				return "success";
			}else {
				return "success";
			}
		}
		
		return "error";
	}
	
	/**
	 * 中介结构注册跳转
	 * @return
	 */
	@RequestMapping("/bgAgencySubmit")
	public String agencysubmit(){
		
		return "agency/agencySubmit";
	}
	
	/**
	 * 中介机构注册提交
	 * @param file
	 * @param request
	 * @param agency
	 * @param address
	 * @return
	 */
	@RequestMapping(value = "/bgAgencySubmit",method=RequestMethod.POST)
	public String agencysubmitPost(@RequestParam MultipartFile[] file, HttpServletRequest request, ZFAgency agency, ZFAddress address){
		
		String[] services = request.getParameterValues("serverItem");
		String modelPath = FileUtil.MultiUpload(file, request, 2);
		
		agencyService.insertAgency(agency, address, services, modelPath);
		return "/";
	}
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request)
	{
		request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas());
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/";
		}
		
		return "account/login";
	}
	
	/**
	 * 中介机构登陆页面
	 * @return
	 */
	@RequestMapping(value="agencylogin",method=RequestMethod.GET)
	public String agencylogin(HttpServletRequest request)
	{
		request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas());
		if (SecurityUtils.getSubject().isAuthenticated()) {
//			跳转到中介机构管理后台
			return "redirect:/";
		}
		
		return "agency/agencyLogin";
	}
	
	/**
	 * 中介机构登陆页面
	 * @return
	 */
	@RequestMapping(value="agencylogin",method=RequestMethod.POST)
	public String agencyloginPost(HttpServletRequest request,HttpSession session,@RequestParam String username,@RequestParam String password,Model model)
	{
		request.setAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,username);
		request.setAttribute(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM,password);
		// ****************************token-begin**************************************//
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username+"," + 3,password);
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
			return "agency/agencyLogin";
		} catch (IncorrectCredentialsException ice) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","密码错误!"));
			return "agency/agencyLogin";
		} catch (LockedAccountException lae) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","账号被锁定!"));
			return "agency/agencyLogin";
		} catch (Exception e) {
			request.setAttribute(GlobalStatic.RETURNDATAS, new ReturnDatas("","未知错误,请联系管理员."));
			return "agency/agencyLogin";
		}
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
	
	/**
	 * 根据用户姓名查找用户是否已经注册
	 * @param name
	 * @param type 1 普通用户 企业用户 2中介机构用户
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping("/checkuserbyname")
	public String checkUserByName(String name,String type){
		Object user = userService.isExistName(name,null, Integer.parseInt(type));
		if (user == null) {
			return "success";
		}else {
			return "error";
		}
		
	}
	
	/**
	 * 根据手机号查找用户是否已经注册
	 * @param phone
	 * @param type
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping("/checkuserbyphone")
	public String checkUserByPhobe(String phone,String type){
		Object user = userService.isExistPhone(phone,null, Integer.parseInt(type));
		if (user == null) {
			return "success";
		}else {
			return "error";
		}
	}
	
	/**
	 * 根据手机号查找用户是否已经注册
	 * @param phone
	 * @param type
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping("/checkuserexist")
	public String checkUserExist(String value,String type){
		Object user = userService.isExistUser(value, null, Integer.parseInt(type));
		if (user == null) {
			return "success";
		}else {
			return "error";
		}
	}
	
	/**
	 * Ajax验证手机验证码是否正确，后期添加
	 * @param phone
	 * @param code
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping("/checkmessagecode")
	public String checkMessageCode(String phone,String code){
		// TODO Auto-generated method stub
		int verification = MessageUtil.getCode(phone).getCode();
		if (verification == Integer.parseInt(code)) {
			return "success";
		}
		return "error";
	}
	
}
