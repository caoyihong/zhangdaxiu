package com.jingyuan.zhifeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.ZFInvest;
import com.jingyuan.zhifeng.repository.ZFInvestMapper;
import com.jingyuan.zhifeng.service.ZFInvestService;

@Service
public class ZFInvestServiceImpl implements ZFInvestService {

	@Autowired
	ZFInvestMapper investMapper;
	
	@Override
	public List<ZFInvest> findInvestByStageAndField(String stage, String field) {
		List<ZFInvest> invests = investMapper.findInvestByStageAndField(stage, field);
		return invests;
	}

}
