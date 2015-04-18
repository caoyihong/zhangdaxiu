package com.jingyuan.zhifeng.web.controller.student;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学生管理后台路由
 * @author cloud_000
 * @version Apr 18, 2015
 */
@Controller
@RequestMapping("stu")
public class StuController {
	
	/**
	 * 学生管理主界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public String logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "redirect:/";
	}
	
	/**
	 * 私信管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/inbox")
	public String inbox(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "student/inboxList";
	}
	
	/**
	 * 私信管理 - 查看私信、回复私信
	 * @param request
	 * @return
	 */
	@RequestMapping("/inbox/{inboxId}")
	public String viewInbox(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "student/inbox";
	}
	
	/**
	 * 作业管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/work")
	public String work(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "student/inboxList";
	}
	
	/**
	 * 作业管理 - 作业列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/work/{workId}")
	public String viewWork(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "student/inbox";
	}
}
