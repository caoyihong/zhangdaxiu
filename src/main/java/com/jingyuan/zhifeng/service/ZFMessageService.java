package com.jingyuan.zhifeng.service;

import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFMessage;

public interface ZFMessageService {

	public void insertMsg(ZFMessage message);
	
	/**
	 * 根据用户的type插入msg
	 * @param user
	 * @param needId
	 * @param content
	 */
	public void insertMsgByUserAndNeed(ShiroUser user,Integer needId,String content);
}
