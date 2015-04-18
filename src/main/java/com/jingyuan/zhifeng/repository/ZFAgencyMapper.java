package com.jingyuan.zhifeng.repository;

import java.util.List;
import java.util.Map;

import com.jingyuan.zhifeng.entity.ZFAgency;

public interface ZFAgencyMapper {
    int insert(ZFAgency agency);

    int insertSelective(ZFAgency agency);
    
    /**
     * 根据中介机构id查找
     * @param agencyId
     * @return
     */
    ZFAgency findById(Integer agencyId);
    
    /**
     * 根据父分类查找
     * @param parentId
     * @return
     */
    List<ZFAgency> findByParentId(ZFAgency agency);

    /**
     * 导航页面--检索符合条件的中介机构
     * @param province
     * @param city
     * @param services
     * @param agencyType
     */
	List<Map<String,Object>> findWithKindsNeeds(String province, String city,
			String[] services, String[] agencyType);

	/**
	 * 查询指定中介机构所有的工作人员的职称
	 * @param agencyId
	 * @return
	 */
	List<String> findAllWorksByAgencyId(int agencyId);
	
	 /**
     * 根据中介机构联系人真实姓名查询
     * @param agencyId
     * @return
     */
    ZFAgency findAgencyByRealName(String realName);
    
}