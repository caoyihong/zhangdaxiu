package com.jingyuan.zhifeng.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFNeed;

public interface ZFNeedService {
	
	public int insertNeed(ZFNeed need);
	
	/**
	 * 收藏中介机构或者从业人员
	 * @param type	0代表个人关注中介机构，1代表个人关注中介机构从业人员，2代表中介机构人员关注中介机构，3代表中介机构人员关注中介机构人员
	 * @param userId
	 * @param agencyId
	 * @return
	 */
	public String storeAgency(int type,int userId,int agencyId);
	
	/**
	 * 个人发布需求
	 * @param need
	 * @param province
	 * @param city
	 * @param district
	 * @param user
	 */
	public void publicNeed(ZFNeed need,String province,String city,String district,ShiroUser user);
	
	/**
	 * 查找用户发布的需求的数量
	 * @param userId
	 * @return
	 */
	public int countNeedByUserId(Integer userId);
	
	/**
	 * 根据用户查找用户发布的需求
	 * @param userId
	 * @return
	 * @throws ParseException 
	 */
	public Page<Map<String, Object>> findNeedByUserId(Integer userId,String title,String starttime,String endtime,String type,String employeename,String status) throws ParseException;
	
	/**
	 * 根据需求id查找需求
	 * @param needId
	 * @return
	 */
	public ZFNeed findNeedByNeedId(Integer needId);
	
	/**
	 * 删除需求
	 */
	public void deleteNeed(String needid);
	
	/**
	 * 整合需求列表的信息
	 * @param needs
	 * @return
	 */
	public List<Map<String, Object>> needInfo(List<Map<String, Object>> needs);
	
	/**
	 * 需求类型的列表
	 * @param need
	 * @return
	 */
	public List<String> needTypeList(Integer userId);
	
	/**
	 * 根据地区、服务类型查询需求
	 * @param province
	 * @param city
	 * @param services
	 * @return
	 */
	public List<Map<String,Object>> findNeeds(String province,String city,String[] services);
	
	/**
	 * 查找逻辑删除的需求
	 * @param userId
	 * @return
	 */
	public Page<Map<String, Object>> deleteNeeds(Integer userId);
	
	public void recoverNeed(String needIds);
	
	/**
     * 根据城市和类别查询需求
     * @param province
     * @param city
     * @param services
     * @return
     */
	public Page<Map<String, Object>> findNeedByCityAndType(String province, String city, String services);
	
	/**
	 * 中介需求列表的展示页信息整合
	 * @return
	 */
	public List<Map<String, Object>> needListInfo(List<ZFNeed> needs);
	
	/**
	 * 查找用户待处理的需求
	 * @return
	 */
	public List<ZFNeed> waitNeeds(Integer userId);
}
