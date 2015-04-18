package com.jingyuan.zhifeng.service;


import java.util.List;
import java.util.Set;

import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFUser;


public interface ZFUserService {
	
	/**
	 * 个人用户和企业用户注册
	 * @param agency
	 * @return
	 */
	public ZFUser insertPersonUser(ZFUser user);
	
	
	/**
	 * 根据id和类型判断普通用户是否存在
	 * @param id
	 * @return
	 */
	public boolean isExist(int id);

	/**
	 * 根据类型查询手机号是否被注册过
	 * @param phone
	 * @param type
	 * @return
	 */
	public Object isExistPhone(String phone,String password, int type);
	
	/**
	 * 根据类型查询用户名是否被注册过
	 * @param name
	 * @param type
	 * @return
	 */
	public Object isExistName(String name,String password,int type);
	
	/**
	 * 根据类型查询邮箱是否被注册过
	 * @param email
	 * @param type
	 * @return
	 */
	public Object isExistEmail(String email,String password,int type);
	
	/**
	 * 根据类型查询用户信息
	 * @param value		1:用户名、2：邮箱、3：手机
	 * @param password	密码
	 * @param type		1为企业和个人，2为中介从业人员，3为中介机构
	 * @return
	 */
	public Object isExistUser(String value,String password,int type);
	
	/**
	 * 根据用户id查询用户
	 * @param type	0代表个人和企业用户	1代表中介机构从业人员
	 * @param userId
	 * @return
	 */
	public Object selectByUserId(Integer type,Integer userId);
	
	/**
	 * 更新用户
	 * @param type	0代表个人和企业用户	1代表中介机构从业人员
	 * @param user
	 */
	public void updateByPrimaryKeySelective(Integer type,Object user);
	
	public List<String> userAccountInfo(ZFUser user);
	
	/**
	 * 根据用户id和用户类型查找角色
	 * @param userId	用户id
	 * @param userType	用户类型：{@link #GlobalStatic}
	 * @return
	 */
	public Set<String> findRoles(int userId,int userType);
	
	/**
	 * 根据用户id和用户类型查找权限
	 * @param userId	用户id
	 * @param userType	用户类型：{@link #GlobalStatic}
	 * @return
	 */
	public Set<String> findPermissions(int userId,int userType);
	
	/**
	 * 根据中介机构联系人真实姓名查询
	 * @param realName
	 * @return
	 */
	public ZFAgency findAgencyByRealName(String realName);
}
