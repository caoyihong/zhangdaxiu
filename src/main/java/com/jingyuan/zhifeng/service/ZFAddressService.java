package com.jingyuan.zhifeng.service;

import com.jingyuan.zhifeng.entity.ZFAddress;

public interface ZFAddressService {
	
	public int insertAddress(ZFAddress address);

	public void updateByPrimaryKeySelective(ZFAddress address);
	
}
