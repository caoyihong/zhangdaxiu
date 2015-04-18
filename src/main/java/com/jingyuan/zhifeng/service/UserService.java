package com.jingyuan.zhifeng.service;

import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.entity.Teacher;

public interface UserService {

	/**
	 * 根据用户姓名、密码和类型查询用户
	 * @param name
	 * @param sysadmin
	 * @return
	 */
	public Object isExistUser(String name, String pass,int type);
	
	/**
	 * 根据用户名和密码查询学生
	 * @param name
	 * @param pass
	 * @return
	 */
	public Student findStuByNameAndPass(Student stu);
	
	/**
	 * 根据用户名和密码查询老师
	 * @param name
	 * @param pass
	 * @return
	 */
	public Teacher findTeachByNameAndPass(Teacher teach);
	
	/**
	 * 根据用户和密码查询管理员
	 * @param name
	 * @param pass
	 * @return
	 */
	public SysAdmin findAdminByNameAndPass(SysAdmin admin);

}
