package com.jingyuan.zhifeng.web.controller.work;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.entity.ZFProblem;
import com.jingyuan.zhifeng.entity.ZFSkillc;
import com.jingyuan.zhifeng.entity.ZFStore;
import com.jingyuan.zhifeng.service.ZFAddressService;
import com.jingyuan.zhifeng.service.ZFAgencyEmployeeService;
import com.jingyuan.zhifeng.service.ZFAgencyService;
import com.jingyuan.zhifeng.service.ZFApplyService;
import com.jingyuan.zhifeng.service.ZFMessageService;
import com.jingyuan.zhifeng.service.ZFNeedService;
import com.jingyuan.zhifeng.service.ZFOrderService;
import com.jingyuan.zhifeng.service.ZFProblemService;
import com.jingyuan.zhifeng.service.ZFSkillcService;
import com.jingyuan.zhifeng.service.ZFStoreService;
import com.jingyuan.zhifeng.web.controller.BaseController;

@Controller
public class NeedController extends BaseController {
	@Autowired
	private ZFAgencyEmployeeService agencyEmployeeService;
	@Autowired
	private ZFSkillcService skillcService;
	@Autowired
	private ZFOrderService orderService;
	@Autowired
	private ZFAddressService addressService;
	@Autowired
	private ZFNeedService needService;
	@Autowired
	private ZFAgencyService agencyService;
	@Autowired
	private ZFProblemService problemService;
	@Autowired
	private ZFStoreService storeService;
	@Autowired
	private ZFApplyService applyService;
	@Autowired
	private ZFMessageService messageService;
	
//	改造成留言
	/**
	 * 申请预约(中介机构页面--申请法务或者服务预约)
	 * @return
	 * @author wangxu
	 */
	@RequestMapping(value = "/work/addOrder",method = RequestMethod.GET)
	public String addOrder(HttpServletRequest request, String employeeId)
	{
		ZFAgencyemployee agencyemployee = agencyEmployeeService.findByUserId(Integer.parseInt(employeeId));
		List<ZFSkillc> skillcs = skillcService.findEmployeeSkills(agencyemployee);
		int reserve = orderService.countOrderByUserIdAndStatus(agencyemployee.getId(), 0,0);
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		// 查找预约的法务订单
		List<ZFOrder> neworders = orderService.findSuccessOrder(agencyemployee.getAgencyid(), 2, null);
		// 查找分部
		List<ZFAgency> agencies = agencyService.findBranch(agencyService.isExist(agencyemployee.getAgencyid()));
		
		request.setAttribute("skillcs", skillcs);
		request.setAttribute("agencyemployee", agencyemployee);
		request.setAttribute("reserve", reserve);
		request.setAttribute("user", user);
		request.setAttribute("neworders", neworders);
		request.setAttribute("agencies", agencies);
		request.setAttribute("formToken", this.resetToken(request));
		return "work/addOrder";
	}
	
//	改造成留言
	/**
	 * 申请预约(中介机构页面--申请法务或者服务预约)
	 * @return
	 * @author wangxu
	 */
	@RequestMapping(value = "/work/addOrder",method = RequestMethod.POST)
	public String addOrderPost(ZFNeed need,String province,String city,String district,String agencyemployeeid, HttpServletRequest request)
	{
		if (isTokenValid(request, true)) {
			ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
			orderService.insertOrderAndNeed(need, province, city, district, agencyemployeeid, user);
		}
		return "redirect:/work/orderSuccess";
	}
	
	/**
	 * 申请成功页面跳转
	 * @return
	 * @author wangxu
	 */
	@RequestMapping(value = "/work/orderSuccess",method = RequestMethod.GET)
	public String orderSuccess()
	{
		return "/work/orderSuccess";
	}
	
	/**
	 * 收藏中介机构(中介机构页面和中介机构从业人员页面--加入收藏按钮)
	 * @param type	0-中介机构从业人员，1-中介机构
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/work/storeAgency",method = RequestMethod.POST)
	public String storeAgency(Integer id, Integer type){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		Integer userType = user.getType();//0,1表示普通用户；2表示中介机构从业人员
		ZFStore store = new ZFStore();
		store.setUserid(userId);
		store.setAnencyid(id);
		if(userType==0 || userType==1){//登陆用户为普通用户
			if(type==0){//0中介机构从业人员
				store.setType(ZFStore.PERSON_E);
			}
			if(type==1){//1中介机构
				store.setType(ZFStore.PERSON_A);
			}
		}
		if(userType==2){//登陆用户为中介机构从业人员
			if(type==0){//0中介机构从业人员
				store.setType(ZFStore.AGENCYE_E);
			}
			if(type==1){//1中介机构
				store.setType(ZFStore.AGENCYE_A);
			}
		}
		storeService.insertStore(store);//插入一条收藏记录
		return "success";
	}
	
	/**
	 * 取消收藏中介机构(中介机构页面和中介机构从业人员页面--取消收藏按钮)
	 * @param type	0-中介机构从业人员，1-中介机构
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/work/cancelstore",method = RequestMethod.POST)
	public String cancelStore(Integer id,Integer type){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		Integer userType = user.getType();//0,1表示普通用户；2表示中介机构从业人员
		if(userType==0 || userType==1){//登陆用户为普通用户
			if(type==0){//0中介机构从业人员
				ZFStore store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.PERSON_E, userId, id);
				store.setEnabled(1);
				storeService.updateStore(store);
			}
			if(type==1){//1中介机构
				ZFStore store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.PERSON_A, userId, id);
				store.setEnabled(1);
				storeService.updateStore(store);
			}
		}
		if(userType==2){//登陆用户为中介机构从业人员
			if(type==0){//0中介机构从业人员
				ZFStore store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.AGENCYE_E, userId, id);
				store.setEnabled(1);
				storeService.updateStore(store);
				
			}
			if(type==1){//1中介机构
				ZFStore store = storeService.selectByUserIdAndAgencyIdAndType(ZFStore.AGENCYE_A, userId, id);
				store.setEnabled(1);
				storeService.updateStore(store);
				
			}
		}
		return "success";
	}
	
	/**
	 * 完善我的申请
	 * @param applyid
	 * @param content
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value = "/work/updateApply",method = RequestMethod.POST)
	public String updateApply(String applyid,String content,String formToken,HttpServletRequest request){
		if (isTokenValid(request, true)) {
			applyService.updateApply(Integer.parseInt(applyid), content);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.format(new Date());
		}
		return "error";
	}
	
//	改造成留言回复
	/**
	 * 需求-回复(需求详情-正在服务中：回复)
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value = "/work/needreply",method = RequestMethod.POST)
	public String reply(String needIDs,String args,HttpServletRequest request)
	{
		if (isTokenValid(request, true)) {
			String needId = needIDs.split(",")[0];
			String content = args.split(",")[0];
			ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
			messageService.insertMsgByUserAndNeed(user, Integer.parseInt(needId), content);
		}
		return "success";
	}
	
	/**
	 * 发布难题
	 * @return
	 */
	@RequestMapping("/work/addproblem")
	public String addProblem()
	{
		return "work/addProblem";
	}
	
	/**
	 * 发布难题
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/work/addproblem",method = RequestMethod.POST)
	public String addProblemPost(ZFProblem problem, String sfinishtime,String sstoptime, HttpServletRequest request) throws ParseException{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String type = request.getParameter("kind04");
		problem.setType(type);
		problem.setEndtime(sf.parse(sstoptime));
		problem.setFinishtime(sf.parse(sfinishtime));
		problem.setUserid(userId);
		problemService.insertProblem(problem);
		return "redirect:/work/orderSuccess";
	}
	
	/**
	 * 难题列表展示
	 * @return
	 */
	@RequestMapping("/common/problemlist")
	public String problemlist()
	{
		return "work/problemList";
	}
	
	/**
	 * 专家库展示
	 * @return
	 */
	@RequestMapping("/common/prolist")
	public String prolist()
	{
		return "work/proList";
	}
	
	/**
	 * 发布项目
	 * @return
	 */
	@RequestMapping("/work/addproject")
	public String addProject()
	{
		return "work/addProject";
	}
	
	/**
	 * 发布项目
	 * @return
	 */
	@RequestMapping(value = "/work/addproject",method = RequestMethod.POST)
	public String addProjectPost()
	{
		return "work/addProject";
	}
	
	/**
	 * 项目列表展示
	 * @return
	 */
	@RequestMapping("/common/projectlist")
	public String projectlist()
	{
		return "work/projectList";
	}
	
	/**
	 * 首页检索
	 * @return
	 */
	@RequestMapping("/common/findthing")
	public String findThing()
	{
		return "work/findThing";
	}
}
