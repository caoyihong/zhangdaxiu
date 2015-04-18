package com.jingyuan.zhifeng.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.repository.ZFAddressMapper;
import com.jingyuan.zhifeng.repository.ZFNeedMapper;
import com.jingyuan.zhifeng.repository.ZFOrderMapper;
import com.jingyuan.zhifeng.service.ZFOrderService;

@Service
public class ZFOrderServiceImpl implements ZFOrderService{

	@Autowired
	ZFOrderMapper orderMappers;
	@Autowired
	ZFAddressMapper addressMapper;
	@Autowired
	ZFNeedMapper needMapper;
	
	@Override
	public List<ZFOrder> findSuccessOrder(Integer agencyId,Integer limit,Integer status) {

		return orderMappers.findSuccessOrder(agencyId,limit, status);
	}

	@Override
	public void insertOrder(ZFOrder order) {
		orderMappers.insertSelective(order);
	}

	@Override
	public String orderServiceRank(List<ZFOrder> orders) {
		int servicerank = 0;
		int size = orders.size();
		for (int i = 0; i < size; i++) {
			ZFOrder order = orders.get(i);
			if (order.getServicerank()!= null && order.getServicerank().equals("不满意")) {
				servicerank++;
			}
		}
		DecimalFormat df = new DecimalFormat("0%");
		if (size == 0) {
			size = 1;
		}
		
		return df.format(1-servicerank*1.0/size) + "";
	}

	@Override
	public String orderTimeRank(List<ZFOrder> orders) {
		int timerank = 0;
		int size = orders.size();
		for (int i = 0; i < size; i++) {
			ZFOrder order = orders.get(i);
			if (order.getTimerank()!= null && order.getTimerank().equals("慢")) {
				timerank++;
			}
		}
		DecimalFormat df = new DecimalFormat("0%");
		if (size == 0) {
			size = 1;
		}
		return df.format(1-timerank*1.0/size) + "";
	}

	@Override
	public List<ZFOrder> findOrderByUserId(Integer userId,Integer status, Integer limit,String orderType) {
		return orderMappers.findOrderByUserId(userId, status, limit,orderType);
	}

	@Override
	public int countOrderByUserIdAndStatus(Integer userId, Integer status,Integer limit) {
		return orderMappers.countOrderByUserIdAndStatus(userId, status,limit);
	}

	@Override
	public int countOrderByStatus(int agencyId, Integer status) {
		
		return orderMappers.countOrderByStatus(agencyId, status);
	}

	@Override
	public void insertOrderAndNeed(ZFNeed need, String province, String city, String district, String agencyemployeeid,ShiroUser user) {
		ZFAddress address = new ZFAddress();
		address.setProvince(province);
		address.setCity(city);
		address.setDistrict(district);
		
		addressMapper.insertSelective(address);
		need.setUserid(user.getId());
		need.setAddress(address.getAddressid());
		
		needMapper.insertSelective(need);
		int employeeId = Integer.parseInt(agencyemployeeid);
		ZFOrder order = new ZFOrder();
		order.setUserid(user.getId());
		order.setAgencyemployeeid(employeeId);
		order.setType(need.getType());
		order.setNeedid(need.getId());
		order.setStatus(1);
		order.setAccepttime(new Date());
		order.setPrice(need.getPrice());
		order.setOrdertype(0);
		orderMappers.insertSelective(order);
		
	}

	@Override
	public ZFOrder findOrderByNeedId(Integer needId) {
		ZFOrder order = orderMappers.findOrderByNeedId(needId);
		return order;
	}

	@Override
	public void updateOrder(ZFOrder order) {
		orderMappers.updateByPrimaryKeySelective(order);
	}

	@Override
	public ZFOrder findOrderByOrderId(Integer orderId) {
		ZFOrder order = orderMappers.selectByPrimaryKey(orderId);
		return order;
	}

	@Override
	public void insertByNeedAndEmployee(String needId, String employeeid) {
		ZFNeed need = needMapper.selectByPrimaryKey(Integer.parseInt(needId));
		ZFOrder order = new ZFOrder();
		order.setNeedid(Integer.parseInt(needId));
		order.setAgencyemployeeid(Integer.parseInt(employeeid));
		order.setStatus(GlobalStatic.ORDER_ACCEPT);
		order.setType(need.getType());
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setEnabled((byte) 0);
		order.setPrice(need.getPrice());
		order.setUserid(need.getUserid());
		order.setOrdertype(0);
		order.setAccepttime(new Date());
		orderMappers.insertSelective(order);
	}

}
