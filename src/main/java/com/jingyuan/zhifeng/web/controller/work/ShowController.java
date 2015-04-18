package com.jingyuan.zhifeng.web.controller.work;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFApply;
import com.jingyuan.zhifeng.entity.ZFMessage;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.repository.ZFMessageMapper;
import com.jingyuan.zhifeng.service.ZFApplyService;
import com.jingyuan.zhifeng.service.ZFNeedService;
import com.jingyuan.zhifeng.service.ZFOrderService;
import com.jingyuan.zhifeng.service.ZFUserService;
import com.jingyuan.zhifeng.web.controller.BaseController;

@Controller
public class ShowController extends BaseController {
	
	@Autowired
	private ZFNeedService needService;
	@Autowired
	private ZFOrderService orderService;
	@Autowired
	private ZFApplyService applyService;
	@Autowired
	private ZFMessageMapper messageMapper;
	@Autowired
	private ZFUserService userService;
	
	/**
	 * 知识产权交易列表展示
	 * @return
	 */
	@RequestMapping("/ipdeal/deallist")
	public String deallist()
	{
		return "showcase/dealList";
	}
	
	/**
	 * 单个知识产权交易展示
	 * @return
	 */
	@RequestMapping("/ipdeal/{ipId}")
	public String showIpDeal()
	{
		return "showcase/showDeal";
	}
	
	/**
	 * 具体难题展示
	 * @return
	 */
	@RequestMapping("/common/problem/{problemId}")
	public String showProblem()
	{
		return "showcase/showProblem";
	}
	
	/**
	 * 具体需求展示
	 * @return
	 */
	@RequestMapping("/common/task/{taskId}")
	public String showTask()
	{
		return "showcase/showTask";
	}
	
//	改造成具体留言展示
	/**
	 * 展示需求订单详情
	 * @return
	 * @author wangxu
	 */
	@RequestMapping("/common/showorder/{needId}")
	public String showOrder(@PathVariable int needId,HttpServletRequest request)
	{
		request.setAttribute("formToken", this.resetToken(request));
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		ZFNeed need = needService.findNeedByNeedId(needId);
		Integer needUserId = need.getUserid();
		Integer userId = 0;	
		Integer type = 0;
		boolean applied = false;
		if (user != null) {
			//判断这个用户是不是发布这个需求的人
			userId = user.getId();
			type = user.getType();
		}
		//查看这个需求是否已经被接单
		ZFOrder order = orderService.findOrderByNeedId(needId);
		//申请信息列表
		List<ZFApply> applies = applyService.findApplyByNeed(needId);
		
		//查找对应的需求
		if (order == null) {
			//未被接单
			//查找对应的申请
			if (userId.equals(needUserId)&& user.getType() != 2) {
				//说明是登陆的用户发的
				request.setAttribute("flag", true);
			}else {
				request.setAttribute("flag", false);
			}
			
			if (type == 2) {
				request.setAttribute("identity", true);
				//是律师是判断是否已经发送过请求
				ZFApply apply = applyService.findApplyByEmployeeId(needId, user.getId());
				if (apply != null) {
					applied = true;
					request.setAttribute("apply", apply);
				}
				
			}else {
				request.setAttribute("identity", false);
			}
			
			request.setAttribute("need", need);
			request.setAttribute("applies", applies);
			request.setAttribute("applied", applied);
			return "showcase/showOrder";
		}else {
			//已经被接单，交流信息列表
			List<ZFMessage> messages = messageMapper.findMessageByNeedId(needId);
			if (userId == need.getUserid() && (type == 0 || type == 1)) {
				//登陆用户是发布这个需求的人
				request.setAttribute("identity", 0);
			}else if (userId == order.getAgencyemployeeid() && type == 2) {
				//登陆用户是接单这个需求的人
				request.setAttribute("identity", 2);
			}else {
				//登陆用户只是查看这个需求的人
				request.setAttribute("identity", -1);
			}
			ZFApply apply = applyService.findApplyByEmployeeId(need.getId(), order.getAgencyemployeeid());
			
			request.setAttribute("applies", applies);
			request.setAttribute("order", order);
			request.setAttribute("need", need);
			request.setAttribute("messages", messages);
			request.setAttribute("apply", apply);
			if (userId != 0) {
				Object user2 = userService.selectByUserId(user.getType() == 2 ? 1:0, user.getId());
				request.setAttribute("user", user2);
			}
			return "showcase/showOrderDetail";
		}
		
	}
	
//	改造成留言板
	/**
	 * 展示需求订单列表
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @author wangxu
	 */
	@RequestMapping("/common/orderlist")
	public String orderList(@RequestParam(required = false) String province,@RequestParam(required = false) String city,@RequestParam(required = false) String services,HttpServletRequest request) throws UnsupportedEncodingException
	{
		if (province == null) {
			province = "";
		}
		if (city == null) {
			city = "";
		}
		
		if (services == null) {
			services = "";
		}
		
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		Page<Map<String, Object>> needs = needService.findNeedByCityAndType(province, city, services);
		PageInfo<Map<String, Object>> needInfo = new PageInfo<Map<String,Object>>(needs);
		
		request.setAttribute("needInfo", needInfo);
		return "showcase/orderList";
	}
}
