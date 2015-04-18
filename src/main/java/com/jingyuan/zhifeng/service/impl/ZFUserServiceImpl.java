package com.jingyuan.zhifeng.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.PasswordHelper;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFSkillc;
import com.jingyuan.zhifeng.entity.ZFUser;
import com.jingyuan.zhifeng.repository.ZFAgencyMapper;
import com.jingyuan.zhifeng.repository.ZFAgencyemployeeMapper;
import com.jingyuan.zhifeng.repository.ZFNeedMapper;
import com.jingyuan.zhifeng.repository.ZFProblemMapper;
import com.jingyuan.zhifeng.repository.ZFSkillcMapper;
import com.jingyuan.zhifeng.repository.ZFUserMapper;
import com.jingyuan.zhifeng.service.ZFUserService;

@Service
public class ZFUserServiceImpl implements ZFUserService{
	
	@Autowired
	ZFUserMapper userMapper;
	
	@Autowired
	ZFAgencyemployeeMapper employeeMapper;
	
	@Autowired
	ZFProblemMapper problemMapper;
	
	@Autowired
	ZFNeedMapper needMapper;
	
	@Autowired
	ZFSkillcMapper skillcMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Autowired
	private ZFAgencyMapper agencyMapper;

	@Override
	
	public ZFUser insertPersonUser(ZFUser user) {
		
		passwordHelper.encryptPasswordZFUser(user); 
		userMapper.insertSelective(user);
		userMapper.insertRoleUser(user.getType()+1,user.getId(),user.getType(),GlobalStatic.ENABLED_TRUE);
		return user;
	}

	@Override
	public boolean isExist(int id) {
		
		ZFUser user = userMapper.findNormalUserById(id, 1);
		return user == null ? false : true;
	}
	
	@Override
	public Object isExistPhone(String phone,String password, int type) {
		
//		1为企业和个人，2为中介
		if(type == 2)
		{
			ZFAgencyemployee agencyemployee = new ZFAgencyemployee();
			agencyemployee.setPhone(phone);
			if (password != null) {
				agencyemployee.setPassword(password);
			}
			return employeeMapper.selectByUser(agencyemployee);
		}
		else
		{
			ZFUser user = new ZFUser();
			user.setPhone(phone);
			user.setType(type);
			if (password != null) {
				user.setPassword(password);
			}
			
			return userMapper.selectByUser(user);
		}
	}

	@Override
	public Object isExistName(String name,String password, int type) {
//		1为企业和个人，2为中介
		if(type == 2)
		{
			ZFAgencyemployee agencyemployee = new ZFAgencyemployee();
			agencyemployee.setName(name);
			if (password != null) {
				agencyemployee.setPassword(password);
			}
			return employeeMapper.selectByUser(agencyemployee);
		}
		else
		{
			ZFUser user = new ZFUser();
			user.setName(name);
			user.setType(type);
			if (password != null) {
				user.setPassword(password);
			}
			return userMapper.selectByUser(user);
		}
		
	}

	@Override
	public Object isExistEmail(String email,String password, int type) {
		
//		1为企业和个人，2为中介
		if(type == 2)
		{
			ZFAgencyemployee agencyemployee = new ZFAgencyemployee();
			agencyemployee.setEmail(email);
			if (password != null) {
				agencyemployee.setPassword(password);
			}
			return employeeMapper.selectByUser(agencyemployee);
		}
		else
		{
			ZFUser user = new ZFUser();
			user.setEmail(email);
			user.setType(type);
			if (password != null) {
				user.setPassword(password);
			}
			return userMapper.selectByUser(user);
			
		}
	}

	@Override
	public Object isExistUser(String value,String password, int type) {
		String emailReq = "[\\w]+@[\\w]+.[\\w]+";//邮箱格式
		String phoneReq = "[0-9]{11}";//手机号格式
		String usernameReq = "[^0-9]([0-9]|[a-zA-Z]|[\u4E00-\u9FA5]|[_]){1,19}";//用户名格式 首字符不能为数字，由字母、汉字、数字、下划线组成
		if (type == 3) {
			return agencyMapper.findAgencyByRealName(value);
		}
		if(value.matches(emailReq))
		{
			return isExistEmail(value,password,type);
		}
		else if(value.matches(phoneReq))
		{
			return isExistPhone(value,password,type);
		}
		else if(value.matches(usernameReq))
		{
			return isExistName(value,password,type);
		}
		return null;
	}

	@Override
	public Object selectByUserId(Integer type, Integer userId) {
		if (type == 0) {
			return userMapper.selectByUserId(userId);
		}else {
			return employeeMapper.selectByUserId(userId);
		}
	}

	@Override
	public void updateByPrimaryKeySelective(Integer type,Object user) {
		if (type == 0) {
			userMapper.updateByPrimaryKeySelective((ZFUser) user);
		}else {
			employeeMapper.updateByPrimaryKeySelective((ZFAgencyemployee) user);
		}
	}

	@Override
	public List<String> userAccountInfo(ZFUser user) {
		List<String> list = new ArrayList<String>();
		Integer problemNum = problemMapper.countProblemByUserId(user.getId());
		Integer needNum = needMapper.countNeedByUserId(user.getId());
		String skill = null;
		String skillid = user.getAttention();
		if (skillid != null && skillid != "") {
			String[] skillids = skillid.split(",");
			for (int j = 0; j < skillids.length; j++) {
				ZFSkillc skillc = skillcMapper.selectByPrimaryKey(Integer.parseInt(skillids[j]));
				skill += skillc.getName()+" ";
			}
		}
		list.add(problemNum+"");
		list.add(needNum+"");
		list.add(skill);
		return list;
	}

	@Override
	public Set<String> findRoles(int userId, int userType) {
//		判断用户存在
		Set<String> roles = userMapper.findRoles(userId,userType);
		if(CollectionUtils.isNotEmpty(roles))
		{
			
			return roles;
		}
		return Collections.emptySet();
	}

	@Override
	public Set<String> findPermissions(int userId, int userType) {
		
		Set<String> roles = userMapper.findRoles(userId,userType);
		if(CollectionUtils.isNotEmpty(roles))
		{
			List<String> listRoles = new ArrayList<>();
			for(String s : roles)
			{
				listRoles.add(s);
			}
			return userMapper.findPermissions(listRoles);
		}
		return Collections.emptySet();
	}

	@Override
	public ZFAgency findAgencyByRealName(String realName) {
		// TODO Auto-generated method stub
		return null;
	}
}
