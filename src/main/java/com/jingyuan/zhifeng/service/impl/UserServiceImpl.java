package com.jingyuan.zhifeng.service.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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
		String password = null;
		String credentialsSalt = null;
		switch(type)
		{
		case 0 : user = findStuByName(name);
		case 1 : user = findTeachByName(name);
		case 2 : user = findAdminByName(name);
		}
//		如果用户存在则比对密码
		if(user != null)
		{
			try {
				PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(user.getClass())
				        .getPropertyDescriptors();
				for(PropertyDescriptor property : propertyDescriptors)
				{
					if ("password".equals(property.getName())) 
					{
			            Method writer = property.getReadMethod();
			            password = (String)writer.invoke(user, new Object[]{});
			            break;
					}
				}
				for(Method method_ : user.getClass().getMethods())
				{
					if(method_.getName().equals("getCredentialsSalt"))
					{
						credentialsSalt = (String)method_.invoke(user, new Object[]{});
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			credentialsSalt = Optional.of(credentialsSalt).get();
			if(Optional.of(password).get().equals(new SimpleHash(
	                "md5",
	                pass,
	                ByteSource.Util.bytes(credentialsSalt),
	                2).toHex()))
			{
				return user;
			}
		}
		return null;
	}

	@Override
	public Student isExistStuWithNameAndPass(String name, String pass) {
		
		Student stu = findStuByName(name);
		if(stu != null)
		{
			if(stu.getPassword().equals(new SimpleHash(
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
		
		Teacher teach = findTeachByName(name);
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
		
		SysAdmin admin = findAdminByName(name);
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
		case 0 : user = findStuByName(name);
		case 1 : user = findTeachByName(name);
		case 2 : user = findAdminByName(name);
		}
		return user;
	}

	@Override
	public Student findStuByName(String name) {
		return studentMapper.selectByName(name);
	}

	@Override
	public Teacher findTeachByName(String name) {
		return teacherMapper.selectByName(name);
	}

	@Override
	public SysAdmin findAdminByName(String name) {
		return adminMapper.selectByName(name);
	}

	@Override
	public List<Map<String, String>> selectStus(String colleage,
			String specialty, Integer stuId) {
		
		return studentMapper.selectStus(colleage,specialty, stuId);
	}

	@Override
	public Student selectStuByKey(Integer stuId) {
		
		return studentMapper.selectByPrimaryKey(stuId);
	}

	@Override
	public void deleteStudent(Integer stuId) {
		
		studentMapper.deleteByPrimaryKey(stuId);
	}

}
