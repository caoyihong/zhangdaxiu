package com.jingyuan.zhifeng.repository;

import com.jingyuan.zhifeng.entity.ZFIndustryc;

public interface ZFIndustrycMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFIndustryc record);

    int insertSelective(ZFIndustryc record);

    ZFIndustryc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFIndustryc record);

    int updateByPrimaryKey(ZFIndustryc record);
}