package com.jingyuan.zhifeng.web.controller;


import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class CommonController extends BaseController{
	
	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model)
	{
//		新闻动态
//		最新发布的6个知识产权项目
//		最新发布的6个难题招标
//		最新发布的需求
//		平台案例展示(可收费)，知识产权交易、法务、服务各两个
//		法务咨询10个
		return "index";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "redirect:/";
	}

}
