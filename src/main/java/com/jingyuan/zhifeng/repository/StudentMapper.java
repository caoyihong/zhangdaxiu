package com.jingyuan.zhifeng.repository;

import java.util.List;
import java.util.Map;

import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    Student selectByStudent(Student stu);
    
    Student selectByName(String name);

    /**
     * 根据学院、专业、学号查询学生
     * @param colleage
     * @param specialty
     * @param stuId
     * @return
     */
	List<Map<String, String>> selectStus(String colleage, String specialty,
			Integer stuId);
}