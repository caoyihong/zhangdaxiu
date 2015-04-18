package com.jingyuan.zhifeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFSkillc;
import com.jingyuan.zhifeng.repository.ZFSkillcMapper;
import com.jingyuan.zhifeng.service.ZFSkillcService;

@Service
public class ZFSkillcServiceImpl implements ZFSkillcService {

	@Autowired
	ZFSkillcMapper skillMapper;
	@Override
	public List<String> findIPServices() {
		// TODO Auto-generated method stub
		return skillMapper.findIPServices();
	}

	@Override
	public List<String> findIPLowServices() {
		// TODO Auto-generated method stub
		return skillMapper.findIPServices();
	}

	@Override
	public List<ZFSkillc> findEmployeeSkills(ZFAgencyemployee agencyemployee) {
		List<ZFSkillc> skillcs = skillMapper.findByParentId(agencyemployee.getIdentity());
		String skillid = agencyemployee.getSkillid();
		if (skillid != null && skillid != "") {
			String[] skillids = skillid.split(",");
			for (int j = 0; j < skillids.length; j++) {
				ZFSkillc skillc = skillMapper.selectByPrimaryKey(Integer.parseInt(skillids[j]));
				skillcs.add(skillc);
			}
		}
		return skillcs;
	}

}
