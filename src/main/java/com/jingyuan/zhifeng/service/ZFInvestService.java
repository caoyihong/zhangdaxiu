package com.jingyuan.zhifeng.service;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFInvest;

public interface ZFInvestService {
	
	public List<ZFInvest> findInvestByStageAndField(String stage,String field);

}
