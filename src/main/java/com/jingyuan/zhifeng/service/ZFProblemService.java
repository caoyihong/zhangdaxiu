package com.jingyuan.zhifeng.service;

import com.jingyuan.zhifeng.entity.ZFProblem;

public interface ZFProblemService {
	
	public int insertProblem(ZFProblem problem);
	
	public int countProblemByUserId(Integer userId);
}
