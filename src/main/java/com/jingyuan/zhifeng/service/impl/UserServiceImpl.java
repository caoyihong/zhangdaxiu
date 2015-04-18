package com.jingyuan.zhifeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.entity.Teacher;
import com.jingyuan.zhifeng.repository.StudentMapper;
import com.jingyuan.zhifeng.repository.SysAdminMapper;
import com.jingyuan.zhifeng.repository.TeacherMapper;
import com.jingyuan.zhifeng.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private SysAdminMapper adminMapper;
	
	@Override
	public Object isExistUser(String name, String pass, int type) {
		
		Object user = null;
		switch(type)
		{
		case 0 : user = findStuByNameAndPass(new Student(name,pass));
		case 1 : user = findTeachByNameAndPass(new Teacher(name,pass));
		case 2 : user = findAdminByNameAndPass(new SysAdmin(name,pass));
		}
		return user;
	}

	@Override
	public Student findStuByNameAndPass(Student stu) {
		
		return studentMapper.selectByStudent(stu);
	}

	@Override
	public Teacher findTeachByNameAndPass(Teacher teach) {
		
		return teacherMapper.selectByTeacher(teach);
	}

	@Override
	public SysAdmin findAdminByNameAndPass(SysAdmin admin) {
		
		return adminMapper.selectByAdmin(teach);
	}

}
