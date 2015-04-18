package com.jingyuan.zhifeng.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jingyuan.zhifeng.entity.ZFNeed;

public interface ZFNeedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFNeed record);

    int insertSelective(ZFNeed record);

    ZFNeed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFNeed record);

    int updateByPrimaryKey(ZFNeed record);
    
    int countNeedByUserId(Integer userId);
    
    /**
     * 根据用户的id进行查询，需求的标题，类型和接受的从业人员的名字进行模糊查询
     * @param userId
     * @param title
     * @param starttime
     * @param endtime
     * @param type
     * @param employeename
     * @return
     */
    List<Map<String, Object>> findNeedByUserId(Integer userId,String title,Date starttime,Date endtime,String type,String employeename,Integer status);

    /**
     * 根据地区和服务类型查询需求，默认查询刚发布的(没有关联订单的)
     * @param province
     * @param city
     * @param services
     * @return
     */
	List<Map<String, Object>> findNeeds(String province, String city,
			String[] services);
	
    /**
	 * 查找逻辑删除的需求
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> deleteNeeds(Integer userId);
    
    /**
     * 根据城市和类别查询需求
     * @param province
     * @param city
     * @param services
     * @return
     */
    List<ZFNeed> findNeedByCityAndType(String province, String city, String services);
    
    /**
	 * 查找用户待处理的需求
	 * @return
	 */
	List<ZFNeed> waitNeeds(Integer userId);
	
	/**
	 * 查找用户的所有需求类别
	 */
	List<String> findNeedTypesByUserId(Integer userId);
	
}