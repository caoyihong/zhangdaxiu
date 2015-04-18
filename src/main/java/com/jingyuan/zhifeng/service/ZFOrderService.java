package com.jingyuan.zhifeng.service;

import java.util.List;

import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.entity.ZFOrder;

public interface ZFOrderService {
	
	/**
	 * 根据中介机构id查找成功的订单
	 * @param agencyId
	 * @return
	 */
	public List<ZFOrder> findSuccessOrder(Integer agencyId,Integer limit,Integer status);
	
	public void insertOrder(ZFOrder order);
	
	/**
	 * 计算所有订单的服务满意度
	 * @param orders
	 * @return
	 */
	public String orderServiceRank(List<ZFOrder> orders);
	
	/**
	 * 计算所有订单的时间满意度
	 * @param orders
	 * @return
	 */
	public String orderTimeRank(List<ZFOrder> orders);
	
	/**
	 *	查找中介机构从业人员的订单
	 * @return
	 */
	public List<ZFOrder> findOrderByUserId(Integer userId,Integer status, Integer limit,String orderType);
	
	public int countOrderByUserIdAndStatus(Integer userId,Integer status,Integer limit);
	
	public int countOrderByStatus(int agencyId,Integer status);

	/**
	 * 预约时插入订单，需求表，地址
	 * @param need
	 * @param province
	 * @param city
	 * @param district
	 * @param agencyemployeeid
	 * @param user
	 */
	public void insertOrderAndNeed(ZFNeed need,String province,String city,String district,String agencyemployeeid,ShiroUser user);
	
	public ZFOrder findOrderByNeedId(Integer needId);
	
	public void updateOrder(ZFOrder order);
	
	public ZFOrder findOrderByOrderId(Integer orderId);
	
	/**
	 * 用户接受中介机构从业人员申请时插入对应的order
	 * @param needId
	 * @param employeeid
	 * @param user
	 */
	public void insertByNeedAndEmployee(String needId,String employeeid);
	
}
