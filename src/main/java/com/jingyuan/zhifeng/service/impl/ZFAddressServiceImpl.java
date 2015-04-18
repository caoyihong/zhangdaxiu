package com.jingyuan.zhifeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.repository.ZFAddressMapper;
import com.jingyuan.zhifeng.service.ZFAddressService;

@Service
public class ZFAddressServiceImpl implements ZFAddressService{

	@Autowired
	private ZFAddressMapper addressMapper;
	
	@Override
	public int insertAddress(ZFAddress address) {
		addressMapper.insertSelective(address);
		return address.getAddressid();
	}

	@Override
	public void updateByPrimaryKeySelective(ZFAddress address) {
		addressMapper.updateByPrimaryKeySelective(address);
	}

}
