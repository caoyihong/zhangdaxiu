package com.jingyuan.zhifeng.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingyuan.zhifeng.core.constant.UserType;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;



@Controller
public class CommonController extends BaseController{
	
	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model)
	{
		
		return "redirect:" + UserType.getUrl(((ShiroUser)SecurityUtils.getSubject().getPrincipal()).getType()) + "/index";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "redirect:/";
	}

}
