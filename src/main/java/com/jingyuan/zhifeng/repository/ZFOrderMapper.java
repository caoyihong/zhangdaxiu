package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFOrder;

public interface ZFOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFOrder record);

    int insertSelective(ZFOrder record);

    ZFOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFOrder record);

    int updateByPrimaryKey(ZFOrder record);
    
    /**
     * 根据中介机构从业人员id和状态查询order
     * @param employeeid
     * @param status
     * @return
     */
    List<ZFOrder> selectByEmployeeIdAndStatus(Integer employeeid,Integer status);
    
    /**
     * 根据中介机构从业人员id和状态查询order数量
     * @param employeeid
     * @param status
     * @return
     */
    int countByEmployeeIdAndStatus(Integer employeeid,Integer status);
    
    /**
     * 根据服务评价查询order数量
     * @param employeeid
     * @return
     */
    int countByServiceRank(Integer employeeid,String serviceRank);
    
    /**
     * 根据服务时间评价查询order数量
     * @param employeeid
     * @return
     */
    int countByTimeRank(Integer employeeid,String timeRank);
    
    /**
	 * 根据中介机构id查找状态为status的订单
	 * @param agencyId
	 * @return
	 */
	public List<ZFOrder> findSuccessOrder(int agencyId,Integer limit,Integer status);
	
	 /**
	 * 根据中介机构id查找状态为status的订单
	 * @param agencyId
	 * @return
	 */
	public int countOrderByStatus(int agencyId,Integer status);
	
	/**
	 * 根据用户id查找状态为status的订单
	 * @param userId
	 * @param status
	 * @param limit
	 * @return
	 */
	public List<ZFOrder> findOrderByUserId(Integer userId,Integer status, Integer limit,String orderType);
	
	/**
	 * 根据用户id查找状态为status的订单的数量
	 */
	public int countOrderByUserIdAndStatus(Integer userId,Integer status,Integer limit);
    
	/**
	 * 根据中介机构id获取所有完成的订单数量
	 * @param agencyId
	 * @return
	 */
	public int countOrderByAgencyAndStatus(Integer agencyId);
	
	public ZFOrder findOrderByNeedId(Integer needId);
}