package com.jingyuan.zhifeng.repository;

import com.jingyuan.zhifeng.entity.ZFProblem;

public interface ZFProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFProblem problem);

    int insertSelective(ZFProblem problem);

    ZFProblem selectByPrimaryKey(Integer id);
    
    int countProblemByUserId(Integer userId);

}