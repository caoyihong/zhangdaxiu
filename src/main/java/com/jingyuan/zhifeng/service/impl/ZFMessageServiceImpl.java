package com.jingyuan.zhifeng.service.impl;


import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFMessage;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.repository.ZFMessageMapper;
import com.jingyuan.zhifeng.repository.ZFOrderMapper;
import com.jingyuan.zhifeng.service.ZFMessageService;

@Service
public class ZFMessageServiceImpl implements ZFMessageService{
	@Autowired
	ZFMessageMapper messageMapper;
	@Autowired
	ZFOrderMapper orderMapper;

	@Override
	public void insertMsg(ZFMessage message) {
		
		messageMapper.insertSelective(message);
	}

	@Override
	public void insertMsgByUserAndNeed(ShiroUser user, Integer needId,
			String content) {
		ZFOrder order = orderMapper.findOrderByNeedId(needId);
		ZFMessage message = new ZFMessage();
		message.setNeedid(needId);
		message.setUserid(order.getUserid());
		message.setEmployeeid(order.getAgencyemployeeid());
		message.setContent(content);
		if (user.getType() == 2) {
			message.setType(1);
		}else {
			message.setType(0);
		}
		message.setIsread(GlobalStatic.Msg_NREAD);
		message.setCreatetime(new Date());
		message.setUpdatetime(new Date());
		message.setEnabled((byte) 0);
		messageMapper.insertSelective(message);
	}

}
