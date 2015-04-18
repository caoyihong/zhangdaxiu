package com.jingyuan.zhifeng.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.jingyuan.zhifeng.core.utils.PasswordHelper;
import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.entity.Teacher;
import com.jingyuan.zhifeng.repository.StudentMapper;
import com.jingyuan.zhifeng.repository.SysAdminMapper;
import com.jingyuan.zhifeng.repository.TeacherMapper;
import com.jingyuan.zhifeng.service.UserService;

/**
 * 用户注册登录服务
 * @author cloud_000
 * @version Apr 18, 2015
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private SysAdminMapper adminMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Override
	public Object isExistUser(String name, String pass, int type) {
		
		Object user = null;
		switch(type)
		{
		case 0 : user = isExistStuWithNameAndPass(name,pass);
		case 1 : user = isExistTeachWithNameAndPass(name,pass);
		case 2 : user = isExistAdminWithNameAndPass(name,pass);
		}
		return user;
	}

	@Override
	public Student isExistStuWithNameAndPass(String name, String pass) {
		
		Student stu = studentMapper.selectByName(name);
		if(stu != null)
		{
			if(stu.getPass().equals(new SimpleHash(
	                "md5",
	                pass,
	                ByteSource.Util.bytes(stu.getCredentialsSalt()),
	                2).toHex()))
			{
				return stu;
			}
			return null;
		}
		else
		{return null;}
		
	}

	@Override
	public Teacher isExistTeachWithNameAndPass(String name, String pass) {
		
		Teacher teach = teacherMapper.selectByName(name);
		if(teach != null)
		{
			if(teach.getPassword().equals(new SimpleHash(
	                "md5",
	                pass,
	                ByteSource.Util.bytes(teach.getCredentialsSalt()),
	                2).toHex()))
			{
				return teach;
			}
			return null;
		}
		else
			return null;
	}


	
	@Override
	public SysAdmin isExistAdminWithNameAndPass(String name, String pass) {
		
		SysAdmin admin = adminMapper.selectByName(name);
		if(admin != null)
		{
			if(admin.getPassword().equals(new SimpleHash(
	                "md5",
	                pass,
	                ByteSource.Util.bytes(admin.getCredentialsSalt()),
	                2).toHex()))
			{
				return admin;
			}
			return null;
		}
		else
		{return null;}
	}

	@Override
	public void insertAdmin(SysAdmin admin) {
		
		try
		{
			passwordHelper.encryptPasswordZFAgency(admin);
			SysAdmin admin2 = new SysAdmin(Optional.of(admin.getName()).get(),Optional.of(admin.getPassword()).get(),Optional.of(admin.getDecription()).get(),Optional.of(admin.getSalt()).get());
			adminMapper.insert(admin2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

	@Override
	public void deleteAdmin(Integer adminId) {
		
		adminMapper.deleteByPrimaryKey(adminId);
	}

	@Override
	public Object findUserByNameAndType(String name, int type) {
		
		Object user = null;
		switch(type)
		{
		case 0 : user = studentMapper.selectByName(name);
		case 1 : user = teacherMapper.selectByName(name);
		case 2 : user = adminMapper.selectByName(name);
		}
		return user;
	}

}
