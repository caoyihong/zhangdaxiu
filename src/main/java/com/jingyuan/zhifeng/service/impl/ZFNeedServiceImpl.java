package com.jingyuan.zhifeng.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.entity.ZFStore;
import com.jingyuan.zhifeng.repository.ZFAddressMapper;
import com.jingyuan.zhifeng.repository.ZFApplyMapper;
import com.jingyuan.zhifeng.repository.ZFMessageMapper;
import com.jingyuan.zhifeng.repository.ZFNeedMapper;
import com.jingyuan.zhifeng.repository.ZFOrderMapper;
import com.jingyuan.zhifeng.repository.ZFStoreMapper;
import com.jingyuan.zhifeng.service.ZFNeedService;

@Service
public class ZFNeedServiceImpl implements ZFNeedService{

	@Autowired
	private ZFNeedMapper needMapper;

	@Autowired
	private ZFStoreMapper storeMapper;
	
	@Autowired
	ZFAddressMapper addressMapper;
	
	@Autowired
	ZFOrderMapper orderMapper;
	@Autowired
	ZFApplyMapper applyMapper;
	
	@Autowired
	ZFMessageMapper messageMapper;
	
	@Override
	public int insertNeed(ZFNeed need) {
		needMapper.insertSelective(need);
		return need.getId();
	}

	@Override
	public String storeAgency(int type, int userId, int agencyId) {
		
		ZFStore store = new ZFStore();
		store.setUserid(userId);
		store.setType(type);
		store.setAnencyid(agencyId);
		if(storeMapper.selectByUserIdAndAgencyIdAndType(type, userId, agencyId) == null)
		{
			storeMapper.insertSelective(store);
			return "success";
		}
		else
		{
			return "exist";
		}
	}

	@Override
	public void publicNeed(ZFNeed need, String province, String city, String district, ShiroUser user) {
		ZFAddress address = new ZFAddress();
		address.setProvince(province);
		address.setCity(city);
		address.setDistrict(district);
		
		addressMapper.insertSelective(address);
		need.setUserid(user.getId());
		need.setAddress(address.getAddressid());
		
		needMapper.insertSelective(need);
		
	}

	@Override
	public int countNeedByUserId(Integer userId) {
		return needMapper.countNeedByUserId(userId);
	}

	@Override
	public Page<Map<String, Object>> findNeedByUserId(Integer userId,String title,String starttime,String endtime,String type,String employeename,String status) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		int orderStatus = -1;
		if (title != null && title.equals("")) {
			title = null;
		}
		if (starttime != null && !starttime.equals("")) {
			start = sf.parse(starttime);
		}
		if (endtime != null && !endtime.equals("")) {
			//将截止时间+1，因为sql里面是写的小于
			end = sf.parse(endtime);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(end);
			calendar.add(calendar.DATE,1);
			end=calendar.getTime();
		}
		if (type != null && type.equals("不限")) {
			type = null;
		}
		if (status != null && !status.equals("-1")) {
			orderStatus = Integer.parseInt(status);
		}
		if (employeename != null && employeename.equals("")) {
			employeename = null;
		}
		List<Map<String, Object>> needs = needMapper.findNeedByUserId(userId,title,start,end,type,employeename,orderStatus);
		Page<Map<String, Object>> page = (Page<Map<String, Object>>)needs;
		Page<Map<String, Object>> pageMap = new Page<Map<String, Object>>();
		pageMap.addAll(needInfo(needs));
		pageMap.setPageNum(page.getPageNum());
		pageMap.setPageSize(page.getPageSize());
		pageMap.setPageSizeZero(page.getPageSizeZero());
		pageMap.setTotal(page.getTotal());
		return pageMap;
	}

	@Override
	public ZFNeed findNeedByNeedId(Integer needId) {
		ZFNeed need = needMapper.selectByPrimaryKey(needId);
		return need;
	}

	@Override
	public void deleteNeed(String needids) {
		String[] needId = needids.split(",");
		for (int i = 0; i < needId.length; i++) {
			ZFNeed need = needMapper.selectByPrimaryKey(Integer.parseInt(needId[i]));
			need.setEnabled((byte) 1);
			needMapper.updateByPrimaryKeySelective(need);
		}
	}

	@Override
	public List<Map<String, Object>> needInfo(List<Map<String, Object>> needs) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < needs.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			ZFNeed need = new ZFNeed();
			need.setId((Integer) needs.get(i).get("id"));
			need.setCreatetime((Date) needs.get(i).get("createtime"));
			need.setDetail((String) needs.get(i).get("detail"));
			need.setTitle((String) needs.get(i).get("title"));
			need.setPrice((BigDecimal) needs.get(i).get("price"));
			need.setType((String) needs.get(i).get("type"));
			map.put("need", need);
			if (needs.get(i).get("status") != null) {
				map.put("status", needs.get(i).get("status"));
				Map<String, String> employeemap = new HashMap<String, String>();
				employeemap.put("id", needs.get(i).get("employeeid")+"");
				employeemap.put("agencyid", needs.get(i).get("agencyid")+"");
				employeemap.put("name", needs.get(i).get("name")+"");
				map.put("employee", employeemap);
			}else {
				map.put("status", 0);
			}
			list.add(map);
		}
		
		return list;
	}


	@Override
	public List<String> needTypeList(Integer userId) {
		List<String> typelist = needMapper.findNeedTypesByUserId(userId);
		return typelist;
	}

	@Override
	public List<Map<String, Object>> findNeeds(String province, String city,
			String[] services) {
		
		if(StringUtils.isEmpty(province))
		{
			province = null;
		}
		if(StringUtils.isEmpty(city))
		{
			city = null;
		}
		if(services == null || services.length == 0)
		{
			services = null;
		}
		return needMapper.findNeeds(province,city,services);
	}

	@Override
	public Page<Map<String, Object>> deleteNeeds(Integer userId) {
		List<Map<String, Object>> needs = needMapper.deleteNeeds(userId);
		Page<Map<String, Object>> page = (Page<Map<String, Object>>)needs;
		Page<Map<String, Object>> pageMap = new Page<Map<String, Object>>();
		pageMap.addAll(needInfo(needs));
		pageMap.setPageNum(page.getPageNum());
		pageMap.setPageSize(page.getPageSize());
		pageMap.setPageSizeZero(page.getPageSizeZero());
		pageMap.setTotal(page.getTotal());
		
		return pageMap;
	}

	@Override
	public void recoverNeed(String needIds) {
		String[] needId = needIds.split(",");
		for (int i = 0; i < needId.length; i++) {
			ZFNeed need = needMapper.selectByPrimaryKey(Integer.parseInt(needId[i]));
			need.setEnabled((byte) 0);
			needMapper.updateByPrimaryKeySelective(need);
		}
	}

	@Override
	public Page<Map<String, Object>> findNeedByCityAndType(String province, String city,
			String services) {
		List<ZFNeed> list = needMapper.findNeedByCityAndType(province, city, services);
		Page<ZFNeed> page = (Page<ZFNeed>)list;
		Page<Map<String, Object>> pageMap = new Page<Map<String, Object>>();
		pageMap.addAll(needListInfo(list));
		pageMap.setPageNum(page.getPageNum());
		pageMap.setPageSize(page.getPageSize());
		pageMap.setPageSizeZero(page.getPageSizeZero());
		pageMap.setTotal(page.getTotal());
		return pageMap;
	}

	@Override
	public List<Map<String, Object>> needListInfo(List<ZFNeed> needs) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < needs.size(); i++) {
			ZFNeed need = needs.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("price", need.getPrice());
			map.put("title", need.getTitle());
			map.put("time", need.getCreatetime());
			ZFOrder order = orderMapper.findOrderByNeedId(need.getId());
			if (order == null) {
				map.put("status", -1);
			}else {
				map.put("status", order.getStatus());
			}
			
			int applyCount = applyMapper.countApplyByNeedId(need.getId());
			map.put("count", applyCount);
			map.put("id", need.getId());
			map.put("username", need.getUser().getName());
			list.add(map);
		}
		return list;
	}

	@Override
	public List<ZFNeed> waitNeeds(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
