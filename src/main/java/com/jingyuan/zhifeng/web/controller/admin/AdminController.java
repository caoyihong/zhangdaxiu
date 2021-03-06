package com.jingyuan.zhifeng.web.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.service.UserService;

/**
 * 老师管理后台路由
 * 
 * @author cloud_000
 * @version Apr 18, 2015
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;

	/**
	 * 管理员首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String admin(HttpServletRequest request) {

		return "admin/index";
	}

	/**
	 * 学生管理 - 主界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/stus")
	public String stuControl(HttpServletRequest request,
			@RequestParam(required = false) String colleage,
			@RequestParam(required = false) String specialty,
			@RequestParam(required = false) Integer stuId) {

		// 检索出学生列表
		List<Map<String, String>> stus = userService.selectStus((""
				.equals(colleage) ? null : colleage),
				("".equals(specialty) ? null : specialty), stuId);
		request.setAttribute("stus", stus);
		return "admin/stuControl";
	}

	/**
	 * 学生管理 - 查看具体学生信息(可编辑状态，即修改表单)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/stus/{stuId}")
	public String viewStu(HttpServletRequest request,
			@PathVariable Integer stuId) {

		Student stu = userService.selectStuByKey(stuId);
		request.setAttribute("stu", stu);
		return "admin/stuControl";
	}

	/**
	 * 学生管理 - 更新具体学生信息(可编辑状态，即修改表单)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stus/{stuId}", method = RequestMethod.POST)
	public String updateStu(HttpServletRequest request,
			@PathVariable Integer stuId, @ModelAttribute Student student) {

		// 判断是否有属性修改过
		Student stu = userService.selectStuByKey(stuId);
		if (!(Objects.equal(stu.getSex(), student.getSex())
				&& Objects.equal(stu.getName(), student.getName())
				&& Objects.equal(stu.getClassBean().getDepartment(), student
						.getClassBean().getDepartment())
				&& Objects.equal(stu.getStartyear(), student.getStartyear()) && Objects
					.equal(stu.getClassBean().getSpeciality(), student
							.getClassBean().getSpeciality()))) {
			// 更新用户信息。

		}
		return "admin/stuControl";
	}

	/**
	 * 学生管理 - 删除指定学生
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stus/{stuId}/delete", method = RequestMethod.POST)
	public String deleteStu(HttpServletRequest request,@PathVariable Integer stuId) {

		userService.deleteStudent(stuId);
		return "admin/stuControl";
	}

	/**
	 * 学生管理 - 更新具体学生信息(可编辑状态，即修改表单)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addStu", method = RequestMethod.GET)
	public String addStu(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 学生管理 - 新增学生
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addStu", method = RequestMethod.POST)
	public String addStuPOST(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 老师管理 - 主界面 - 老师列表 + 检索部分
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/teachs")
	public String teachControl(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 老师管理 - 查看具体学生信息(可编辑状态，即修改表单)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/teachs/{teachId}")
	public String viewTeach(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 老师管理 - 更新具体老师信息(可编辑状态，即修改表单)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/teachs/{teachId}", method = RequestMethod.POST)
	public String updateTeach(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 老师管理 - 删除指定老师
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/teachs/{teachId}/delete", method = RequestMethod.POST)
	public String deleteTeach(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 老师管理 - 新增老师
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addTeach", method = RequestMethod.GET)
	public String addTeach(HttpServletRequest request) {

		return "admin/stuControl";
	}

	/**
	 * 老师管理 - 新增老师
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addTeach", method = RequestMethod.POST)
	public String addTeachPOST(HttpServletRequest request) {

		return "admin/teachControl";
	}
}
