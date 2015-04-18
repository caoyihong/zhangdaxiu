package com.jingyuan.zhifeng.repository;

import com.jingyuan.zhifeng.entity.StudyDatas;

public interface StudyDatasMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyDatas record);

    int insertSelective(StudyDatas record);

    StudyDatas selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyDatas record);

    int updateByPrimaryKey(StudyDatas record);
}