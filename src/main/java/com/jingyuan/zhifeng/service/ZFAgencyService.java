package com.jingyuan.zhifeng.service;


import java.util.List;
import java.util.Map;

import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFAgency;


public interface ZFAgencyService {
	
	/**
	 * 中介机构注册
	 * @param agency
	 * @return
	 */
	public ZFAgency insertAgency(ZFAgency agency, ZFAddress address, String[] services, String modelPath);
	
	/**
	 * 根据agencyId查找中介机构
	 * @param id
	 * @return
	 */
	public ZFAgency isExist(int agencyId);
	
	/**
	 * 查找分公司
	 * @return
	 */
	public List<ZFAgency> findBranch(ZFAgency agency);

	/**
	 * 导航页面--检索符合条件的中介机构
	 * 省可为空，如果省为空则查询全国，省不为空，市为空的情况下查询全省
	 * 服务类型：单选，为空的情况下选择列出的所有服务类型的机构
	 * 机构类型：可为空，单选
	 * @param province
	 * @param city
	 * @param services
	 * @param agencyType
	 */
	public List<Map<String,Object>> findFromNavigate(String provinceT, String cityT,
			String[] servicesT, String[] agencyTypeT);
	
	/**
	 * 查询指定中介机构所有的服务列表
	 * @param agencyId
	 * @return
	 */
	public List<String> findAllServicesByAgencyId(int agencyId);
	
	/**
	 * 查询指定中介机构所有的工作人员的职称
	 * @param agencyId
	 * @return
	 */
	public List<String> findAllWorksByAgencyId(int agencyId);
}
