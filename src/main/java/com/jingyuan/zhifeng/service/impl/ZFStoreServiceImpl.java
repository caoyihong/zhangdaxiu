package com.jingyuan.zhifeng.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFStore;
import com.jingyuan.zhifeng.repository.ZFAddressMapper;
import com.jingyuan.zhifeng.repository.ZFAgencyMapper;
import com.jingyuan.zhifeng.repository.ZFStoreMapper;
import com.jingyuan.zhifeng.service.ZFStoreService;

@Service
public class ZFStoreServiceImpl implements ZFStoreService {
	
	@Autowired
	private ZFStoreMapper storeMapper;
	
	@Autowired
	private ZFAddressMapper addressMapper;
	
	@Autowired
	private ZFAgencyMapper agencyMapper;

	@Override
	public ZFStore seleteById(int id) {
		
		return storeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertStore(ZFStore store) {
		storeMapper.insertSelective(store);
		return store.getId();
	}

	@Override
	public void updateStore(ZFStore store) {
		storeMapper.updateByPrimaryKeySelective(store);

	}

	@Override
	public List<ZFStore> selectByUserIdAndType(Integer userId, Integer type) {
		return storeMapper.selectByUserIdAndType(userId, type);
	}

	@Override
	public ZFStore selectByUserIdAndAgencyIdAndType(int type, int userId, int agencyId) {
		
		return storeMapper.selectByUserIdAndAgencyIdAndType(type, userId, agencyId);
	}

	@Override
	public List<HashMap<String, Object>> selectResultMap(Integer userId, Integer type) {
		List<HashMap<String,Object>> list = storeMapper.selectResultMap(userId, type);
		//添加页面需要的值：当返回的为agencyemployee时，添加agency name；当返回agency时，添加address信息
		if(type==0 || type==2){
			for (HashMap<String, Object> map : list) {
				Integer addressId = (Integer) map.get("address");
				ZFAddress address = addressMapper.findAddressById(addressId);
				map.put("address", address.getProvince()+address.getCity()+address.getDistrict()+address.getDetail());
			}
		}else if(type==1 || type==3){
			for (HashMap<String, Object> map : list) {
				Integer agencyid = (Integer) map.get("agencyid");
				ZFAgency agency = agencyMapper.findById(agencyid);
				map.put("agencyname", agency.getName());
			}
		}
		return list;
	}
/**根据条件返回需要的结果集，agency或agencyemployee
 * @author teli
 */
	@Override
	public List<HashMap<String, Object>> searchResultMap(Integer userId,
			Integer type, String name, String agencyname, String province, String city, String district) {
		List<HashMap<String,Object>> list = storeMapper.searchResultMap(userId, type, name, agencyname, province, city, district);
		return list;
	}

}
