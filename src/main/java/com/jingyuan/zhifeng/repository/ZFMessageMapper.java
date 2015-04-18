package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFMessage;

public interface ZFMessageMapper {
    int insert(ZFMessage record);

    int insertSelective(ZFMessage record);
    
    List<ZFMessage> findMessageByNeedId(Integer needId);
}