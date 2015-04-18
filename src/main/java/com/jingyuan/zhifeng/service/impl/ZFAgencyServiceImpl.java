package com.jingyuan.zhifeng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.core.utils.PasswordHelper;
import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.repository.ZFAddressMapper;
import com.jingyuan.zhifeng.repository.ZFAgencyMapper;
import com.jingyuan.zhifeng.repository.ZFOrderMapper;
import com.jingyuan.zhifeng.service.ZFAgencyService;

@Service
public class ZFAgencyServiceImpl implements ZFAgencyService{
	
	@Autowired
	ZFAgencyMapper agencyMapper;
	
	@Autowired
	ZFOrderMapper orderMapper;
	@Autowired
	ZFAddressMapper addressMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	@Override
	public ZFAgency insertAgency(ZFAgency agency, ZFAddress address, String[] services, String modelPath) {
		
		//插入地址,type=1
		address.setType(1);
		addressMapper.insertSelective(address);
		String service="";
		
		for (int i = 0; i < services.length; i++) {
			service+=services[i]+",";
		}
		agency.setAddress(address.getAddressid());
		if(address.getCity().equals(address.getDistrict())){
			agency.setCity(address.getProvince());//直辖市
		}else{
			agency.setCity(address.getCity());
		}
		agency.setService(service);//
		String[] path = modelPath.split(",");
		agency.setHead(path[0]);
		agency.setStatus(0);
		agency.setIdpicture(path[1]+";"+path[2]);
		agency.setCompanypicture(path[3]);
		passwordHelper.encryptPasswordZFAgency(agency);
		agencyMapper.insertSelective(agency);
		return agency;
	}

	@Override
	public ZFAgency isExist(int id) {
		
		return agencyMapper.findById(id);
	}

	@Override
	public List<ZFAgency> findBranch(ZFAgency agency) {
		//先判断这个中介机构是不是总部 在sql中判断agency的parentid是否为null
		List<ZFAgency> agencies = agencyMapper.findByParentId(agency);
		return agencies;
	}

	@Override
	public List<Map<String,Object>> findFromNavigate(String province, String city, String[] services,
			String[] agencyType) {
		
//		省、市、服务、机构类型可为空
//		
		return agencyMapper.findWithKindsNeeds(province, city, services, agencyType);
	}

	@Override
	public List<String> findAllServicesByAgencyId(int agencyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllWorksByAgencyId(int agencyId) {
		return agencyMapper.findAllWorksByAgencyId(agencyId);
	}
	
}
