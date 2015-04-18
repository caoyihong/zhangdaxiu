package com.jingyuan.zhifeng.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFApply;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.repository.ZFApplyMapper;
import com.jingyuan.zhifeng.repository.ZFNeedMapper;
import com.jingyuan.zhifeng.service.ZFApplyService;

@Service
public class ZFApplyServiceImpl implements ZFApplyService{
	@Autowired
	ZFApplyMapper applyMapper;
	@Autowired
	ZFNeedMapper needMapper;

	@Override
	public List<ZFApply> findApplyByUserId(Integer userId) {
		List<ZFApply> applies = applyMapper.findApplyByUserId(userId);
		return applies;
	}

	@Override
	public List<ZFApply> findApplyByNeed(Integer needId) {
		List<ZFApply> applies = applyMapper.findApplyByNeed(needId);
		return applies;
	}

	@Override
	public void insertEmployeeApply(Integer needId,ShiroUser user, String content) {
		//因为这里的用户肯定是从业人员
		ZFApply apply = new ZFApply();
		ZFNeed need = needMapper.selectByPrimaryKey(needId);
		apply.setNeedid(needId);
		apply.setUserid(need.getUserid());
		apply.setEmployeeid(user.getId());
		apply.setContent(content);
		apply.setCreatetime(new Date());
		apply.setUpdatetime(new Date());
		apply.setEnabled((byte) 0);
		applyMapper.insertSelective(apply);
	}

	@Override
	public ZFApply findApplyByEmployeeId(Integer needId, Integer employeeId) {
		ZFApply apply = applyMapper.findApplyByEmployeeId(needId, employeeId);
		return apply;
	}

	@Override
	public void updateApply(Integer applyId, String content) {
		ZFApply apply = applyMapper.selectByPrimaryKey(applyId);
		apply.setContent(content);
		apply.setUpdatetime(new Date());
		applyMapper.updateByPrimaryKeySelective(apply);
	}

}
