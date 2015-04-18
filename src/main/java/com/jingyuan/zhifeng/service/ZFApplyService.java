package com.jingyuan.zhifeng.service;

import java.util.List;

import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFApply;

public interface ZFApplyService {

	public List<ZFApply> findApplyByUserId(Integer userId);
	
	/**
	 * 查找一个需求对应的申请
	 * @param needId
	 * @return
	 */
	public List<ZFApply> findApplyByNeed(Integer needId);
	
	/**
	 * 企业机构从业人员申请服务需求
	 * @param needId
	 * @param user	肯定是从业人员
	 * @param content
	 */
	public void insertEmployeeApply(Integer needId,ShiroUser user, String content);
	
	public ZFApply findApplyByEmployeeId(Integer needId,Integer employeeId);
	
	public void updateApply(Integer applyId, String content);
}
