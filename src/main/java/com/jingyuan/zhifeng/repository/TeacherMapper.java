package com.jingyuan.zhifeng.repository;

import com.jingyuan.zhifeng.entity.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    Teacher selectByTeacher(Teacher teach);
    
    Teacher selectByName(String name);
}