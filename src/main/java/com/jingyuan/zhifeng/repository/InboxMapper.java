package com.jingyuan.zhifeng.repository;

import com.jingyuan.zhifeng.entity.Inbox;

public interface InboxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inbox record);

    int insertSelective(Inbox record);

    Inbox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inbox record);

    int updateByPrimaryKey(Inbox record);
}