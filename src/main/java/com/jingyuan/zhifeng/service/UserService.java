package com.jingyuan.zhifeng.service;

import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.entity.Teacher;

public interface UserService {

	/**
	 * 根据姓名和类型查询用户
	 * @param name
	 * @param type
	 * @return
	 */
	public Object findUserByNameAndType(String name,int type);
	/**
	 * 根据用户姓名、密码和类型查询用户
	 * @param name
	 * @param pass 需要手动加密与数据库进行比对
	 * @param type
	 * @return
	 */
	public Object isExistUser(String name, String pass,int type);
	
	/**
	 * 根据用户名和密码查询学生
	 * @param name
	 * @param pass
	 * @return
	 */
	public Student isExistStuWithNameAndPass(String name, String pass);
	
	/**
	 * 根据用户名和密码查询老师
	 * @param name
	 * @param pass
	 * @return
	 */
	public Teacher isExistTeachWithNameAndPass(String name, String pass);
	
	/**
	 * 根据用户和密码查询管理员
	 * @param name
	 * @param pass
	 * @return
	 */
	public SysAdmin isExistAdminWithNameAndPass(String name, String pass);

	/**
	 * 注册管理员账户 ： 需要有用户名、密码、描述
	 * @param admin
	 */
	public void insertAdmin(SysAdmin admin);

	/**
	 * 根据管理员id注销用户
	 * @param adminId
	 */
	public void deleteAdmin(Integer adminId);
}
