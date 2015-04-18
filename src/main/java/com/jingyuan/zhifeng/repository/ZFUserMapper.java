package com.jingyuan.zhifeng.repository;

import java.util.List;
import java.util.Set;

import com.jingyuan.zhifeng.entity.ZFUser;

public interface ZFUserMapper {
	
	/**
	 * 新增用户(每个字段必须都有)
	 * @param record
	 * @return
	 */
    int insert(ZFUser record);

    /**
     * 新增用户(每个字段可以不存在)
     * @param record
     * @return
     */
    int insertSelective(ZFUser record);
    
    /**
     * 根据用户id和用户类型查询普通用户
     * @param userId
     * @param type
     * @return
     */
    ZFUser findNormalUserById(Integer userId,Integer type);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    ZFUser selectByPhone(String phone);
	
    /**
     * 根据用户名查询
     * @param phone
     * @return
     */
	ZFUser selectByUserName(String userName);

	/**
     * 根据邮箱查询
     * @param phone
     * @return
     */
	Object selectByEmail(String email);
	
	ZFUser selectByUser(ZFUser user);
	
	ZFUser selectByUserId(Integer userId);
	
	int updateByPrimaryKeySelective(ZFUser record);

	/**
	 * 根据用户类型和用户id查找用户的角色
	 * @param userId
	 * @param userType
	 * @return
	 */
	Set<String> findRoles(int userId, int userType);
	
	/**
	 * 根据角色名称查询权限
	 * @param roleIds
	 * @return
	 */
	Set<String> findPermissions(List<String> roles);

	/**
	 * 插入用户和角色关联数据
	 * @param roleId	角色id	1-person,2-enterprise,3-agencye,4-agency
	 * @param userId	用户id
	 * @param type		用户类型	0-person,2-enterprise,3-agencye
	 */
	void insertRoleUser(int roleId, Integer id, Integer type,int enabled);
	
}