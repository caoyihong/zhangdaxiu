package com.jingyuan.zhifeng.web.controller.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jingyuan.zhifeng.web.controller.BaseController;

@Controller
@RequestMapping("users")
public class AgencyControlController extends BaseController{

	/**
	 * 个人中心模块(中介机构)--机构信息设置页面
	 * @return
	 */
	@RequestMapping(value="/agency/account",method=RequestMethod.GET)
	public String agencyAccountSet(HttpServletRequest request){
		return "";
	}
	
	/**
	 * 个人中心模块(中介机构)--录入交易表单
	 * @return
	 */
	@RequestMapping(value="/agency/addOrder",method=RequestMethod.GET)
	public String addOrder(HttpServletRequest request){
		return "";
	}
	
	/**
	 * 个人中心模块(中介机构)--录入交易表单
	 * @return
	 */
	@RequestMapping(value="/agency/addOrder",method=RequestMethod.POST)
	public String addOrderPOST(HttpServletRequest request){
		return "";
	}
}
