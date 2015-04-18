package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFSkillc;

public interface ZFSkillcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFSkillc record);

    int insertSelective(ZFSkillc record);

    ZFSkillc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFSkillc record);

    int updateByPrimaryKey(ZFSkillc record);
    
    public ZFSkillc selectByAgencyeId(int agencyId);

	List<String> findIPServices();
	
	public List<ZFSkillc> findByParentId(Integer parentId);
	
	public List<String> selectByIds(int[] skillids);
}