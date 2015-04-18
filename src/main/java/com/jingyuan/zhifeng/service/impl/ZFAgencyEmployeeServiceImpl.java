package com.jingyuan.zhifeng.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jingyuan.zhifeng.core.utils.PasswordHelper;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFSkillc;
import com.jingyuan.zhifeng.repository.ZFAgencyemployeeMapper;
import com.jingyuan.zhifeng.repository.ZFOrderMapper;
import com.jingyuan.zhifeng.repository.ZFSkillcMapper;
import com.jingyuan.zhifeng.service.ZFAgencyEmployeeService;

@Service
public class ZFAgencyEmployeeServiceImpl implements ZFAgencyEmployeeService{
	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	ZFAgencyemployeeMapper employeeMapper;
	@Autowired
	ZFOrderMapper orderMapper;
	@Autowired
	ZFSkillcMapper skillcMapper;

	@Override
	public ZFAgencyemployee insertEmployee(ZFAgencyemployee agencyemployee) {
		
		passwordHelper.encryptPasswordZFA(agencyemployee);
		employeeMapper.insertSelective(agencyemployee);
		return agencyemployee;
	}
	
	/**
	 * 查找金牌律师
	 * @param agencyId
	 * @param limit
	 */
	@Override
	public List<Map<String, String>> employeeInfo(int agencyId,int limit) {
		List<ZFAgencyemployee> agencyemployees = employeeMapper.findGoldEmployee(agencyId,limit);
		
		return getShowAgencye(agencyemployees);
	}
	
	@Override
	public Page<Map<String, String>> findAgencyeWithNeeds(int agencyId,String[] services,String[] agencye,Integer ageRank) {
		List<ZFAgencyemployee> agencyemployees = employeeMapper.findAgencyeWithNeeds(agencyId,services,agencye,ageRank);
		Page<ZFAgencyemployee> page = (Page<ZFAgencyemployee>)agencyemployees;
		Page<Map<String, String>> pageMap = new Page<Map<String, String>>();
		pageMap.addAll(getShowAgencye(agencyemployees));
		pageMap.setPageNum(page.getPageNum());
		pageMap.setPageSize(page.getPageSize());
		pageMap.setPageSizeZero(page.getPageSizeZero());
		pageMap.setTotal(page.getTotal());
		return pageMap;
	}
	
	/**
	 * 组装中介机构从业人员展示信息List
	 * @param agencyemployees
	 * @return
	 */
	private List<Map<String, String>> getShowAgencye(List<ZFAgencyemployee> agencyemployees)
	{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < agencyemployees.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			ZFAgencyemployee agencyemployee = agencyemployees.get(i);
			int employeeid = agencyemployee.getId();
			int total = orderMapper.countByEmployeeIdAndStatus(employeeid, 2);
			int serviceRank = orderMapper.countByServiceRank(employeeid, "不满意");
			int timeRank = orderMapper.countByTimeRank(employeeid, "慢");
			DecimalFormat df = new DecimalFormat("0%");
			map.put("name", agencyemployee.getName());
			map.put("head", agencyemployee.getHead());
			map.put("id", agencyemployee.getId()+"");
			switch (agencyemployee.getIdentity()) {
			case 1:
				map.put("identity","律师");
				break;
			case 2:
				map.put("identity","评估师");
				break;
			case 3:
				map.put("identity","司法鉴定人");
				break;
			case 4:
				map.put("identity","商标代理人");
				break;
			case 5:
				map.put("identity","专利代理");
				break;

			default:
				break;
			}
			
			map.put("worktime", agencyemployee.getWorktime()== null ? "0" : agencyemployee.getWorktime()+"");
			String skill = "";
			String skillid = agencyemployee.getSkillid();
			if (skillid != null && skillid != "") {
				String[] skillids = skillid.split(",");
				for (int j = 0; j < skillids.length; j++) {
					ZFSkillc skillc = skillcMapper.selectByPrimaryKey(Integer.parseInt(skillids[j]));
					skill += skillc.getName()+" ";
				}
			}
			

			map.put("skill", skill);
			map.put("total", total+"");				//咨询量
			//因为如果total == 0 ，除法会出错
			if (total == 0) {
				total++;
			}
			map.put("serviceRank", df.format(1-serviceRank*1.0/total)+"");	//专业性
			map.put("timeRank", df.format(1-timeRank*1.0/total)+"");		//服务性
			list.add(map);
		}
		return list;
	}


	@Override
	public ZFAgencyemployee findByUserId(Integer userId) {
		ZFAgencyemployee agencyemployee = employeeMapper.findEmployeeById(userId);
		return agencyemployee;
	}


	@Override
	public List<Map<String, String>> findOtherByUserId(Integer agencyId,Integer userId,Integer limit) {
		List<ZFAgencyemployee> agencyemployees = employeeMapper.findOtherByUserId(agencyId,userId,limit);
		return getShowAgencye(agencyemployees);
	}

}
