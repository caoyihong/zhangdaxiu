package com.jingyuan.zhifeng.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 老师管理后台路由
 * @author cloud_000
 * @version Apr 18, 2015
 */
@Controller
@RequestMapping("admin")
public class AdminController {
	
	/**
	 * 管理员首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String admin(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/index";
	}
	
	/**
	 * 学生管理 - 主界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/stus")
	public String stuControl(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 学生管理 - 查看具体学生信息(可编辑状态，即修改表单)
	 * @param request
	 * @return
	 */
	@RequestMapping("/stus/{stuId}")
	public String viewStu(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 学生管理 - 更新具体学生信息(可编辑状态，即修改表单)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stus/{stuId}",method = RequestMethod.POST)
	public String updateStu(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 学生管理 - 删除指定学生
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stus/{stuId}/delete",method = RequestMethod.POST)
	public String deleteStu(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 学生管理 - 更新具体学生信息(可编辑状态，即修改表单)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addStu",method = RequestMethod.GET)
	public String addStu(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 学生管理 - 新增学生
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addStu",method = RequestMethod.POST)
	public String addStuPOST(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 老师管理 - 主界面 - 老师列表 + 检索部分
	 * @param request
	 * @return
	 */
	@RequestMapping("/teachs")
	public String teachControl(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 老师管理 - 查看具体学生信息(可编辑状态，即修改表单)
	 * @param request
	 * @return
	 */
	@RequestMapping("/teachs/{teachId}")
	public String viewTeach(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 老师管理 - 更新具体老师信息(可编辑状态，即修改表单)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/teachs/{teachId}",method = RequestMethod.POST)
	public String updateTeach(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 老师管理 - 删除指定老师
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/teachs/{teachId}/delete",method = RequestMethod.POST)
	public String deleteTeach(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 老师管理 - 新增老师
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addTeach",method = RequestMethod.GET)
	public String addTeach(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/stuControl";
	}
	
	/**
	 * 老师管理 - 新增老师
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addTeach",method = RequestMethod.POST)
	public String addTeachPOST(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "admin/teachControl";
	}
}
