package com.jingyuan.zhifeng.service;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFSkillc;

public interface ZFSkillcService {
	
	public List<String> findIPServices();
	
	public List<String> findIPLowServices();
	
	public List<ZFSkillc> findEmployeeSkills(ZFAgencyemployee agencyemployee);
}
