package com.jingyuan.zhifeng.repository;

import java.util.HashMap;
import java.util.List;

import com.jingyuan.zhifeng.entity.ZFStore;

public interface ZFStoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZFStore store);

    int insertSelective(ZFStore store);

    ZFStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFStore store);

    int updateByPrimaryKey(ZFStore store);

	ZFStore selectByUserIdAndAgencyIdAndType(int type, int userId, int agencyId);
	/**
	 * 根据登陆用户id和收藏类型返回
	 * @param userId
	 * @param type
	 * @return
	 */
	List<ZFStore> selectByUserIdAndType(Integer userId, Integer type);
	/**
	 * 根据登陆用户id和收藏类型返回List<map>，自动匹配数据库查询结果类型(agency或agencyemployee)
	 * @param userId
	 * @param type
	 * @return
	 */
	List<HashMap<String,Object>> selectResultMap(Integer userId, Integer type);
	/**
	 * 根据登陆用户id和收藏类型返回已经查询条件返回List<map>，自动匹配数据库查询结果类型(agency或agencyemployee)
	 * @param userId
	 * @param type
	 * @param name
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 * @author teli
	 */
	List<HashMap<String,Object>> searchResultMap(Integer userId, Integer type, String name, String agencyname, String province, String city, String district);
}