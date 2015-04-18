package com.jingyuan.zhifeng.web.controller.agency;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.ReturnDatas;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.entity.ZFSkillc;
import com.jingyuan.zhifeng.entity.ZFStore;
import com.jingyuan.zhifeng.service.ZFAgencyEmployeeService;
import com.jingyuan.zhifeng.service.ZFAgencyService;
import com.jingyuan.zhifeng.service.ZFOrderService;
import com.jingyuan.zhifeng.service.ZFSkillcService;
import com.jingyuan.zhifeng.service.ZFStoreService;
import com.jingyuan.zhifeng.web.controller.BaseController;

@Controller
public class AgencyController extends BaseController {

	@Autowired
	private ZFAgencyService agencyService;

	@Autowired
	private ZFAgencyEmployeeService agencyEmployeeService;

	@Autowired
	private ZFOrderService orderService;

	@Autowired
	private ZFSkillcService skillcService;
	
	@Autowired
	private ZFStoreService storeService;
	
	/**
	 * 三级导航菜单：首页/知识产权服务-法务/具体导航-诉讼、培训等 中介机构总个数(根据前面导航层次查询)
	 * 检索条件：省市、服务类型(根据导航层次变换)、机构类型 参数：province(省，可为空，如果为空查看全部)
	 * city(市，可为空，如果为空查看省) services(单选，可为空即全部)agencyType(机构类型，可为空,如果为空则)
	 * 根据上面的检索条件查询出符合条件的中介机构列表，有分页 以上内容通用于ipservice和iplowservice导航以及其子导航
	 */

	/**
	 * 知识产权服务检索查询
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/ipservice")
	public String serviceListAll(
			@RequestParam(required = false) String province,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String[] services,
			@RequestParam(required = false) String[] agencyType,
			HttpServletRequest request) throws UnsupportedEncodingException {
		// 页面检索条件：服务类型只包括ip服务部分，法务的不进行展示，机构类型没有律师事务所

		// 服务类型list,机构类型list,省市List，查询的中介机构list
		List<String> agencyTypes = new ArrayList<>();
		agencyTypes.add("知识产权评估公司");
		agencyTypes.add("知识产权代理公司");
		agencyTypes.add("知识产权培训公司");
		List<String> ipServices = new ArrayList<>();
		ipServices.add("知识产权代理");
		ipServices.add("知识产权培训");
		ipServices.add("知识产权评估");
		ipServices.add("知识产权申请咨询");
		ipServices.add("知识产权鉴定");
		Map<String, Object> maps = new HashMap<>();
		maps.put("ipServices", ipServices);
		maps.put("agencyTypes", agencyTypes);
		ReturnDatas datas = ReturnDatas.getSuccessReturnDatas();
		// 返回中介机构列表
		if (services == null || services.length == 0) {
			services = new String[] { "知识产权代理", "知识产权培训", "知识产权评估", "知识产权申请咨询",
					"知识产权鉴定" };
		} 
		if (agencyType == null || agencyType.length == 0) {
			agencyType = new String[] { "知识产权评估公司", "知识产权代理公司", "知识产权培训公司" };
		} 
		
		datas.setData(findAgencies(province, city, services, agencyType,request));
		datas.setMap(maps);
		datas.setMsg("ip");
		request.setAttribute(GlobalStatic.RETURNDATAS, datas);
		return "agency/agencyList";
	}

	private PageInfo<Map<String, Object>> findAgencies(String province,
			String city, String[] services, String[] agencyType,HttpServletRequest request) {
		PageHelper.startPage(getPageIndex(request),getPageSize(request));
		String provinceT = null;
		String cityT = null;
		String[] servicesT = null;
		String[] agencyTypeT = null;
		if (StringUtils.isNotEmpty(province)) {
			provinceT = province;
		}
		if (StringUtils.isNotEmpty(city)) {
			cityT = city;
		}
		if (services != null && services.length > 0) {
			servicesT = services;
		}
		if (agencyType != null && agencyType.length > 0) {
			agencyTypeT = agencyType;
		}
		List<Map<String, Object>> datas = agencyService.findFromNavigate(
				provinceT, cityT, servicesT, agencyTypeT);
		if (datas.size() == 1) {
			if (datas.get(0).get("detail") == null) {
				return null;
			}
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(datas);
		return pageInfo;
	}

	/**
	 * 展示中介机构首页
	 * @return
	 * @author wangxu
	 */
	@RequestMapping("/agency/{agencyId}")
	public String showAgency(@PathVariable int agencyId,
			HttpServletRequest request) {
		// 更加agencyId查找对应的中介机构
		ZFAgency agency = agencyService.isExist(agencyId);
		// 查找分部
		List<ZFAgency> agencies = agencyService.findBranch(agency);

		List<Map<String, String>> employeeInfo = agencyEmployeeService
				.employeeInfo(agencyId, 3);
		// 查找成功的法务订单
		List<ZFOrder> orders = orderService.findSuccessOrder(agencyId, 10, 2);
		// 查找预约的法务订单
		List<ZFOrder> neworders = orderService.findSuccessOrder(agencyId, 2, null);

		//判断登陆用户是否已收藏该中介机构
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		Integer userType = user.getType();//0,1表示普通用户；2表示中介机构从业人员
		ZFStore store = null;
		if(userType==0 || userType==1){
			store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.PERSON_A, userId, agencyId);
		}else if(userType==2){
			store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.AGENCYE_A, userId, agencyId);
		}
		if(store!=null){
			request.setAttribute("isStore", 1);//已收藏
		}else{
			request.setAttribute("isStore", 0);//未收藏
		}
		request.setAttribute("agency", agency);
		request.setAttribute("agencies", agencies);
		request.setAttribute("employeeInfo", employeeInfo);
		request.setAttribute("orders", orders);
		request.setAttribute("neworders", neworders);

		return "agency/agencyshow";
	}

//	做成ajax，附属在中介机构展示页面
	/**
	 * 展示中介机构--法务经验
	 * @return
	 * @author wangxu
	 */
	@RequestMapping("/agency/{agencyId}/lowslist")
	public String lowslistAgency(@PathVariable int agencyId,
			HttpServletRequest request) {

		ZFAgency agency = agencyService.isExist(agencyId);
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		List<ZFOrder> orders = orderService.findSuccessOrder(agencyId, 0, 2);
		PageInfo<ZFOrder> orderInfo = new PageInfo<ZFOrder>(orders);
		String orderTimeRank = orderService.orderTimeRank(orders);
		String orderServiceRank = orderService.orderServiceRank(orders);
		
		// 查找预约的法务订单
		List<ZFOrder> neworders = orderService.findSuccessOrder(agencyId, 2, null);
		// 查找分部
		List<ZFAgency> agencies = agencyService.findBranch(agency);
		request.setAttribute("agencies", agencies);
		request.setAttribute("neworders", neworders);

		request.setAttribute("agency", agency);
		request.setAttribute("orders", orderInfo);
		request.setAttribute("orderTimeRank", orderTimeRank);
		request.setAttribute("orderServiceRank", orderServiceRank);
		return "agency/agencyLowslist";
	}

//	改造成ajax，附属在中介机构展现页面下面
	/**
	 * 展示中介机构--法务预约
	 * 
	 * @param agencyId
	 *            中介机构Id
	 * @param request
	 * @param services
	 *            服务类型
	 * @param agencye
	 *            从业人员类型
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/agency/{agencyId}/neworders")
	public String newordersAgency(@PathVariable int agencyId,
			HttpServletRequest request,
			@RequestParam(required = false) String[] services,
			@RequestParam(required = false) String[] agencyType,
			@RequestParam(required = false) String workType) throws UnsupportedEncodingException {
		Map<String, Object> allDatas = new HashMap<>();
		// 如果未选择services和agencye则检索全部，如果选择两张，则两个数据传入sql文进行检索

		// 检索所有服务项目ipServices和所有职称identities
		// 检索符合条件的工作人员
		List<String> ipServices = new ArrayList<>();
		List<String> identities = new ArrayList<>();
		// 检索分部
		// 检索两条最新的订单
		// 更加agencyId查找对应的中介机构
		ZFAgency agency = agencyService.isExist(agencyId);
		ipServices = Arrays.asList(agency.getService().split(","));
		identities = agencyService.findAllWorksByAgencyId(agencyId);
		// 查找分部
		List<ZFAgency> agencies = agencyService.findBranch(agency);
		// 查找预约的法务订单
		List<ZFOrder> neworders = orderService.findSuccessOrder(agencyId, 2, null);
		// 查找符合检索条件的的专家
		if (services == null || services.length == 0) {
			services = null;
		}
		if (agencyType == null || agencyType.length == 0) {
			agencyType = null;
		}
		Integer ageRank = null;
		if(StringUtils.isNotEmpty(workType))
		{
			ageRank = Integer.valueOf(workType);
		}
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		Page<Map<String, String>> list = agencyEmployeeService
				.findAgencyeWithNeeds(agencyId, services, agencyType, ageRank);
		PageInfo<Map<String, String>> employeeInfo = new PageInfo<Map<String,String>>(list);
		allDatas.put("agency", agency);
		allDatas.put("ipServices", ipServices);
		allDatas.put("identities", identities);
		allDatas.put("employeeInfo", employeeInfo);
		request.setAttribute("allDatas", allDatas);
		request.setAttribute("neworders", neworders);
		request.setAttribute("agencies", agencies);
		return "agency/agencyNeworders";
	}

	/**
	 * 展示中介机构工作人员
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author wangxu
	 */
	@RequestMapping("/agency/{agencyId}/agencye/{agencyeId}")
	public String showAgencye(@PathVariable int agencyId,
			@PathVariable int agencyeId, HttpServletRequest request,
			String orderType) throws UnsupportedEncodingException {
		ZFAgencyemployee agencyemployee = agencyEmployeeService.findByUserId(agencyeId);
		List<ZFSkillc> skillcs = skillcService.findEmployeeSkills(agencyemployee);
		
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		List<ZFOrder> orders = new ArrayList<ZFOrder>();
		if (orderType == null) {
			orders = orderService.findOrderByUserId(agencyemployee.getId(), 2, 0, orderType);
		} else {
			orders = orderService.findOrderByUserId(agencyemployee.getId(), 2, 0, orderType);
		}
		PageInfo<ZFOrder> orderInfo = new PageInfo<ZFOrder>(orders);
		List<Map<String, String>> agencyemployees = agencyEmployeeService.findOtherByUserId(agencyemployee.getAgencyid(),agencyemployee.getId(),4);
		int reserve = orderService.countOrderByUserIdAndStatus(agencyemployee.getId(), 0, 0);
		//查找预约的订单
		List<ZFOrder> neworders = orderService.findOrderByUserId(agencyemployee.getId(), 1, 2, null);
		
		//判断登陆用户是否已收藏该中介机构从业人员
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		Integer userType = user.getType();//0,1表示普通用户；2表示中介机构从业人员
		ZFStore store = null;
		if(userType==0 || userType==1){
			store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.PERSON_E, userId, agencyeId);
		}else if(userType==2){
			store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.AGENCYE_E, userId, agencyeId);
		}
		if(store!=null){
			request.setAttribute("isStore", 1);//已收藏
		}else{
			request.setAttribute("isStore", 0);//未收藏
		}
		request.setAttribute("agencyemployee", agencyemployee);
		request.setAttribute("skillcs", skillcs);
		request.setAttribute("orders", orderInfo);	
		request.setAttribute("agencyemployees", agencyemployees);
		request.setAttribute("reserve", reserve);
		request.setAttribute("neworders", neworders);

		return "agency/agencyeShow";
	}
}
