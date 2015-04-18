package com.jingyuan.zhifeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.ZFProblem;
import com.jingyuan.zhifeng.repository.ZFProblemMapper;
import com.jingyuan.zhifeng.service.ZFProblemService;

@Service
public class ZFProblemServiceImpl implements ZFProblemService {

	@Autowired
	private ZFProblemMapper problemMapper;
	
	@Override
	public int insertProblem(ZFProblem problem) {
		problemMapper.insertSelective(problem);
		return problem.getId();
	}

	@Override
	public int countProblemByUserId(Integer userId) {
		return problemMapper.countProblemByUserId(userId);
	}
}
