package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFApply;

public interface ZFApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFApply record);

    int insertSelective(ZFApply record);

    ZFApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFApply record);

    int updateByPrimaryKey(ZFApply record);
    
    List<ZFApply> findApplyByUserId(Integer userId);
    
    int countApplyByNeedId(Integer needId);
    
    List<ZFApply> findApplyByNeed(Integer needId);
    
    ZFApply findApplyByEmployeeId(Integer needId, Integer employeeId);
}