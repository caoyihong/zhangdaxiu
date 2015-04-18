package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFInvest;

public interface ZFInvestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFInvest record);

    int insertSelective(ZFInvest record);

    ZFInvest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFInvest record);

    int updateByPrimaryKey(ZFInvest record);
    
    List<ZFInvest> findInvestByStageAndField(String stage, String field);
}