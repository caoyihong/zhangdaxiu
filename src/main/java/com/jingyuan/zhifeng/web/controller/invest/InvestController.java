package com.jingyuan.zhifeng.web.controller.invest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyuan.zhifeng.entity.ZFInvest;
import com.jingyuan.zhifeng.service.ZFInvestService;
import com.jingyuan.zhifeng.web.controller.BaseController;

@Controller
public class InvestController extends BaseController{

	@Autowired
	ZFInvestService investService;
	
	/**
	 * 创业ip列表展示
	 * @return
	 */
	@RequestMapping(value = "/common/iplist")
	public String ipList()
	{
		return "invest/ipList";
	}
	
	/**
	 * 投资人列表展示
	 * @return
	 */
	@RequestMapping(value = "/common/investerlist")
	public String investerlist()
	{
		
		return "invest/investerList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/common/investerPage")
	public PageInfo<ZFInvest> investerPage(HttpServletRequest request,String stage,String field ){
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		List<ZFInvest> invests = investService.findInvestByStageAndField(stage, field);
		PageInfo<ZFInvest> investInfo = new PageInfo<ZFInvest>(invests);
		return investInfo;
	}
	
}
