package com.jingyuan.zhifeng.service;

import java.util.HashMap;
import java.util.List;

import com.jingyuan.zhifeng.entity.ZFStore;

public interface ZFStoreService {

	public ZFStore seleteById(int id);
	
	public int insertStore(ZFStore store);
	
	public void updateStore(ZFStore store);
	
	public ZFStore selectByUserIdAndAgencyIdAndType(int type, int userId, int agencyId);
	
	public List<ZFStore> selectByUserIdAndType(Integer userId, Integer type);
	
	public List<HashMap<String,Object>> selectResultMap(Integer userId, Integer type);
	
	public List<HashMap<String,Object>> searchResultMap(Integer userId, Integer type, String name, String agencyname, String province, String city, String district);
}
