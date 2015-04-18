package com.jingyuan.zhifeng.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;

public interface ZFAgencyEmployeeService {
	
	/**
	 * 中介机构从业人员注册
	 * @param agencyemployee
	 * @return
	 */
	public ZFAgencyemployee insertEmployee(ZFAgencyemployee agencyemployee);

	/**
	 * 根据agencyId去中介机构员工表中查询律师
	 * @param agencyId
	 * @return
	 */
//	public List<ZFAgencyemployee> findGoldEmployee(int agencyId,int limit);
	
	/**
	 * 根据查询到的金牌律师获取对应的评价百分比
	 * @param agencyemployees
	 * @return
	 */
//	public List<List<Object>> employeeDa(List<ZFAgencyemployee> agencyemployees);
	
	/**
	 * findGoldEmployee 和 employeeDa的合并
	 * @param agencyId
	 * @param limit
	 * @return
	 */
	
	public List<Map<String, String>> employeeInfo(int agencyId,int limit);
	
	public ZFAgencyemployee findByUserId(Integer userId);
	
	/**
	 * 根据从业人员查找同机构的其他人员
	 * @param userid
	 * @return
	 */
	public List<Map<String, String>> findOtherByUserId(Integer agencyId,Integer userId,Integer limit);
	
	/**
	 * 法务预约：查找符合条件的律师
	 * @param agencyId
	 * @param services
	 * @param agencye
	 * @param ageRank	5-0~5/10-5~10/15-10~15
	 * @return
	 */
	public Page<Map<String, String>> findAgencyeWithNeeds(int agencyId,
			String[] services, String[] agencye, Integer ageRank);
}
