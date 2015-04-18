package com.jingyuan.zhifeng.web.controller.account;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingyuan.zhifeng.core.utils.CompressPicDemo;
import com.jingyuan.zhifeng.core.utils.FileUtil;
import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.core.utils.ImgTool;
import com.jingyuan.zhifeng.core.utils.UserRealm.ShiroUser;
import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFApply;
import com.jingyuan.zhifeng.entity.ZFNeed;
import com.jingyuan.zhifeng.entity.ZFOrder;
import com.jingyuan.zhifeng.entity.ZFStore;
import com.jingyuan.zhifeng.entity.ZFUser;
import com.jingyuan.zhifeng.service.ZFAddressService;
import com.jingyuan.zhifeng.service.ZFAgencyEmployeeService;
import com.jingyuan.zhifeng.service.ZFAgencyService;
import com.jingyuan.zhifeng.service.ZFApplyService;
import com.jingyuan.zhifeng.service.ZFNeedService;
import com.jingyuan.zhifeng.service.ZFOrderService;
import com.jingyuan.zhifeng.service.ZFProblemService;
import com.jingyuan.zhifeng.service.ZFStoreService;
import com.jingyuan.zhifeng.service.ZFUserService;
import com.jingyuan.zhifeng.web.controller.BaseController;


@Controller
@RequestMapping("users")
public class PersonController extends BaseController{
	@Autowired
	ZFProblemService problemService;
	@Autowired
	ZFNeedService needService;
	@Autowired
	ZFUserService userService;
	@Autowired
	ZFAddressService addressService;
	@Autowired
	ZFOrderService orderService;
	@Autowired
	ZFApplyService applyService;
	@Autowired
	ZFStoreService storeService;
	@Autowired
	ZFAgencyEmployeeService agencyEmployeeService;
	@Autowired
	ZFAgencyService agencyService;
	
	/**
	 * 个人中心模块(中介机构从业人员)--账号设置页面
	 * @return
	 */
	@RequestMapping(value="/agencye/account",method=RequestMethod.GET)
	public String agencyeAccountSet(HttpServletRequest request){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFAgencyemployee employee = (ZFAgencyemployee) userService.selectByUserId(1, userId);
		request.setAttribute("employee", employee);
		request.setAttribute("column", "personInfo");
		return "account/agencyUsers/accountDetail";
	}
	
	/**
	 * 个人中心模块(中介机构从业人员)--个人信息
	 * @return
	 */
	@RequestMapping(value="/agencye/personInfo",method=RequestMethod.POST)
	public String agencyePersonInfo(HttpServletRequest request, ZFAgencyemployee employee){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		ZFAgencyemployee agencyEmployee = (ZFAgencyemployee) userService.selectByUserId(1, user.getId());
		
		if(employee.getEmail()!=null){
			agencyEmployee.setEmail(employee.getEmail().trim());
			userService.updateByPrimaryKeySelective(1, agencyEmployee);//后期页面要修改
		}
		request.setAttribute("employee", agencyEmployee);
		request.setAttribute("column", "personInfo");
		return "account/agencyUsers/accountDetail";
	}
	
	/**
	 * 个人中心模块(中介机构从业人员)--修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/agencye/changePassword",method=RequestMethod.POST)
	public String agencyeChangePassword(String oldPass,String newpass){
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		ZFAgencyemployee employee = (ZFAgencyemployee) userService.selectByUserId(1, user.getId());
		//限制一个星期内不能重复修改密码
		Date now = new Date();
		if((now.getTime()-employee.getPasswdtime().getTime())/ (1000 * 60 * 60 * 24) < 7){
			return "frequent";
		}else{
			//旧密码的比较
			String password = new SimpleHash("md5",oldPass,ByteSource.Util.bytes(employee.getName() + employee.getSalt()),2).toHex();
			if (employee.getPassword().equals(password)) {
				password = new SimpleHash("md5",newpass,ByteSource.Util.bytes(employee.getName() + employee.getSalt()),2).toHex();
				employee.setPassword(password);
				employee.setPasswdtime(now);
				userService.updateByPrimaryKeySelective(1, employee);
				return "success";
			}else {
				return "error";
			}
		}
	}
	
	/**
	 * 个人中心模块(中介机构从业人员)--头像设置
	 * @return
	 */
	@RequestMapping(value="/agencye/head",method=RequestMethod.POST)
	public String agencyHead(@RequestParam MultipartFile file, HttpServletRequest request, String imgx1, 
			String imgy1, String imgx2, String imgy2, String imgRealW, String imgRealH) throws Exception{
		String modelpath = dealUserhead(file, request, imgx1, imgy1, imgx2, imgy2, imgRealW, imgRealH);
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFAgencyemployee employee = (ZFAgencyemployee) userService.selectByUserId(1, userId);
		employee.setHead(modelpath);
		userService.updateByPrimaryKeySelective(1, employee);
		request.setAttribute("employee", employee);
		request.setAttribute("column", "head");
		return "account/agencyUsers/accountDetail";
	}
	/**
	 * 个人中心模块(中介机构从业人员)--身份认证
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/agencye/security",method=RequestMethod.POST)
	public String agencySecurity(@RequestParam MultipartFile[] file, HttpServletRequest request) throws Exception{

		String realname = request.getParameter("realname");
		String idcard = request.getParameter("idcard");
		String modelPath = FileUtil.MultiUpload(file, request, 2);
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFAgencyemployee employee = (ZFAgencyemployee) userService.selectByUserId(1, userId);
		employee.setRealname(realname);
		employee.setIdcard(idcard);
		employee.setIdpicture(modelPath);
		employee.setStatus(1);
		userService.updateByPrimaryKeySelective(1, employee);
		request.setAttribute("employee", employee);
		request.setAttribute("column", "security");
		return "account/agencyUsers/accountDetail";
	}
	/**
	 * 个人中心页面(中介机构从业人员)
	 * @return
	 */
	@RequestMapping(value="/agencye/{userid}",method=RequestMethod.GET)
	public String agencyePersonC()
	{
		return "account/agencyUsers/usersInfo";
	}
	
	/**
	 * 个人中心页面(中介机构从业人员)--法务列表页面
	 * @return
	 */
	@RequestMapping(value="/agencye/lows",method=RequestMethod.GET)
	public String agencyeLowList()
	{
		return "account/agencyUsers/lowsList";
	}
	
	/**
	 * 个人中心页面(中介机构从业人员)--服务列表页面
	 * @return
	 */
	@RequestMapping(value="/agencye/services",method=RequestMethod.GET)
	public String agencyeServiceList()
	{
		return "account/agencyUsers/servicesList";
	}
	
	/**
	 * 展示服务详情详情：三种状态-申请中、被接受、被拒绝(客户已经接受其他申请者)
	 * @param needId
	 * @return
	 */
	@RequestMapping("/agencye/services/{serviceId}")
	public String showService(@PathVariable int serviceId)
	{
		return "account/agencyUsers/showService";
	}
	
	
	/**
	 * 个人中心页面(中介机构从业人员)--收藏页面
	 * @return
	 * @author teli
	 */
	@RequestMapping(value="/agencye/stores",method=RequestMethod.GET)
	public String agencyeStoreList(HttpServletRequest request){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFAgencyemployee employee = (ZFAgencyemployee) userService.selectByUserId(1, userId);
		
		request.setAttribute("employee", employee);
		return "account/agencyUsers/storesList";
	}
	/**
	 * 中介机构从业人员收藏页面检索
	 * @param request
	 * @param type
	 * @param name
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 * @author teli
	 */
	@ResponseBody
	@RequestMapping(value="/agencye/AjaxagencyEmployeeStores",method=RequestMethod.GET)
	public PageInfo<HashMap<String,Object>> searchAgencyeStoreList(HttpServletRequest request,
			String name, String agencyname, String province, String city, String district){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer usertype = user.getType();
		Integer userId = user.getId();
		if(usertype==2){//登陆用户为中介机构用户
			List<HashMap<String, Object>> agencyEmployee1 = new ArrayList<HashMap<String,Object>>();
			
			PageHelper.startPage(request.getParameter("currentPage")==null?1:Integer.valueOf(request.getParameter("currentPage")), 
					request.getParameter("pageSize")==null?2:Integer.valueOf(request.getParameter("pageSize")));
			agencyEmployee1 = storeService.searchResultMap(userId, ZFStore.AGENCYE_E, name, agencyname, province, city, district);
			PageInfo<HashMap<String,Object>> agencyEmployee = new PageInfo<HashMap<String,Object>>(agencyEmployee1);
			System.out.println("agencyEmployee: "+agencyEmployee.getPageNum()+"bb"+agencyEmployee.getPages());
			
			return agencyEmployee;
		}else{//登陆用户为普通用户
			List<HashMap<String, Object>> agencyEmployee1 = new ArrayList<HashMap<String,Object>>();
			
			PageHelper.startPage(request.getParameter("currentPage")==null?1:Integer.valueOf(request.getParameter("currentPage")), 
					request.getParameter("pageSize")==null?2:Integer.valueOf(request.getParameter("pageSize")));
			agencyEmployee1 = storeService.searchResultMap(userId, ZFStore.PERSON_E, name, agencyname, province, city, district);
			PageInfo<HashMap<String,Object>> agencyEmployee = new PageInfo<HashMap<String,Object>>(agencyEmployee1);
			System.out.println("agencyEmployee: "+agencyEmployee.getPageNum()+"bb"+agencyEmployee.getPages());
			
			return agencyEmployee;
		}
	}
	/**
	 * 中介机构搜索
	 * @param request
	 * @param name
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/agencye/AjaxagencyStores",method=RequestMethod.GET)
	public PageInfo<HashMap<String,Object>> searchAgencyStoreList(HttpServletRequest request, 
			String name, String province, String city, String district){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer usertype = user.getType();
		Integer userId = user.getId();
		if(usertype==2){//登陆用户为中介机构用户
			List<HashMap<String,Object>> agency1 = new ArrayList<HashMap<String,Object>>();
			PageHelper.startPage(request.getParameter("currentPage")==null?1:Integer.valueOf(request.getParameter("currentPage")), 
					request.getParameter("pageSize")==null?2:Integer.valueOf(request.getParameter("pageSize")));
			String agencyname = null;//由于中介人搜索跟中介机构搜索公用一个service，多余的参数
			agency1 = storeService.searchResultMap(userId, ZFStore.AGENCYE_A, name, agencyname, province, city, district);
			PageInfo<HashMap<String,Object>> agency = new PageInfo<HashMap<String,Object>>(agency1);
			System.out.println("agency: "+agency.getPageNum() + "aa" + agency.getPages());
			return agency;
		}else{//登陆用户为普通用户
			List<HashMap<String,Object>> agency1 = new ArrayList<HashMap<String,Object>>();
			PageHelper.startPage(request.getParameter("currentPage")==null?1:Integer.valueOf(request.getParameter("currentPage")), 
					request.getParameter("pageSize")==null?2:Integer.valueOf(request.getParameter("pageSize")));
			String agencyname = null;//由于中介人搜索跟中介机构搜索公用一个service，多余的参数
			agency1 = storeService.searchResultMap(userId, ZFStore.PERSON_A, name, agencyname, province, city, district);
			PageInfo<HashMap<String,Object>> agency = new PageInfo<HashMap<String,Object>>(agency1);
			System.out.println("agency: "+agency.getPageNum() + "aa" + agency.getPages());
			return agency;
		}
	}
	
	/**
	 * 个人中心页面(个人和企业用户)--账号设置页面
	 * @return
	 */
	@RequestMapping(value="/normal/account",method=RequestMethod.GET)
	public String accountSet(HttpServletRequest request)
	{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		if(user != null && user.getType() == 2)
		{
			return "redirect:/users/agencye/account";
		}
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		
		//存放发布需求等数目和关注领域
		List<String> info = userService.userAccountInfo(user2);
		
		request.setAttribute("user", user2);
		request.setAttribute("info", info);
		request.setAttribute("column", "personInfo");
		return "account/normalUser/accountDetail";
	}
	
	/**
	 * 个人中心页面(个人和企业用户) -- 个人信息
	 * @return
	 */
	@RequestMapping(value="/normal/personInfo",method=RequestMethod.POST)
	public String personInfo(HttpServletRequest request,ZFUser zfUser,ZFAddress address){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		if(!("请选择".equals(address.getProvince()) && StringUtils.isEmpty(address.getDistrict()))){
			if (user2.getAddress() != null) {//更新数据
				ZFAddress uaddress = user2.getZfAddress();
				if(address.getProvince()!=null){
					uaddress.setProvince(address.getProvince());
				}
				if(address.getCity()!=null){
					uaddress.setCity(address.getCity());
				}
				if(address.getDistrict()!=null){
					uaddress.setDistrict(address.getDistrict());
				}
				if(address.getDetail()!=null){
					uaddress.setDetail(address.getDetail());
				}
				addressService.updateByPrimaryKeySelective(uaddress);
			}else {//插入新数据
				address.setType(0);//0关联user表
				address.setUserid(userId);
				Integer addressId = addressService.insertAddress(address);
				user2.setAddress(addressId);
				user2.setZfAddress(address);
			}
		}
		
		if(zfUser.getEmail()!=null) {
			user2.setEmail(zfUser.getEmail().trim());
		}
		if(zfUser.getPostcode()!=null){
			user2.setPostcode(zfUser.getPostcode());
		}
		if(zfUser.getFax()!=null){
			user2.setFax(zfUser.getFax());
		}
		userService.updateByPrimaryKeySelective(0, user2);
		//存放发布需求等数目和关注领域
		List<String> info = userService.userAccountInfo(user2);
		request.setAttribute("info", info);
		request.setAttribute("user", user2);
		request.setAttribute("column", "personInfo");
		return "account/normalUser/accountDetail";
	}
	
	/**
	 * 个人中心页面(个人和企业用户) -- 修改密码
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value="/normal/changePassword",method=RequestMethod.POST)
	public String changePassword(String oldPass,String newpass)
	{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		//限制一个星期内不能重复修改密码
		Date now = new Date();
		if((now.getTime()-user2.getPasswdtime().getTime())/ (1000 * 60 * 60 * 24) < 7){
			return "frequent";
		}else{
			//旧密码的比较
			String password = new SimpleHash("md5",oldPass,ByteSource.Util.bytes(user2.getName() + user2.getSalt()),2).toHex();
			if (user2.getPassword().equals(password)) {
				password = new SimpleHash("md5",newpass,ByteSource.Util.bytes(user2.getName() + user2.getSalt()),2).toHex();
				user2.setPassword(password);
				user2.setPasswdtime(now);
				userService.updateByPrimaryKeySelective(0, user2);
				return "success";
			}else {
				return "error";
			}
		}
	}
	
	@RequestMapping(value="/normal/security",method=RequestMethod.POST)
	public String security(@RequestParam MultipartFile[] file, HttpServletRequest request) throws Exception{

		String realname = request.getParameter("realname");
		String idcard = request.getParameter("idcard");
		String modelPath = FileUtil.MultiUpload(file, request, 1);
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		user2.setRealname(realname);
		user2.setNumber(idcard);
		user2.setPicture(modelPath);
		user2.setStatus(1);
		userService.updateByPrimaryKeySelective(0, user2);
		//存放发布需求等数目和关注领域
		List<String> info = userService.userAccountInfo(user2);
		request.setAttribute("info", info);
		request.setAttribute("user", user2);
		request.setAttribute("column", "security");
		return "account/normalUser/accountDetail";
	}

	/**
	 * 个人中心页面(个人和企业用户) -- 头像设置
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="/normal/head",method=RequestMethod.POST)
	public String head(@RequestParam MultipartFile file, HttpServletRequest request, String imgx1, 
			String imgy1, String imgx2, String imgy2, String imgRealW, String imgRealH) throws Exception{
		String modelpath = dealUserhead(file, request, imgx1, imgy1, imgx2, imgy2, imgRealW, imgRealH);
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		user2.setHead(modelpath);
		userService.updateByPrimaryKeySelective(0, user2);
		//存放发布需求等数目和关注领域
		List<String> info = userService.userAccountInfo(user2);
		request.setAttribute("info", info);
		request.setAttribute("user", user2);
		request.setAttribute("column", "head");
		return "account/normalUser/accountDetail";
	}
	//预处理头像再上传
	private String dealUserhead(MultipartFile myfile, HttpServletRequest request, String imgx1, 
			String imgy1, String imgx2, String imgy2, String imgRealW, String imgRealH){
		String outputPath = "images/user_head/";
		//上传文件的路径
		String path = null;
		//存入数据库文件的路径
		String modelpath = null;
		path = GlobalStatic.uploadpath + outputPath;
		modelpath = "/"+outputPath;
		logger.info("path: {}",path);
		//获取上传文件原名
		String originalFilename = null;
		originalFilename = myfile.getOriginalFilename();
		/*File ys_file = new File("c:/zhifeng/temp");
		if(!ys_file.exists()){
			ys_file.mkdirs();
		}*/
		//先上传，再处理
		File userhead_File = new File("c:/zhifeng/images/user_head");
		if(!userhead_File.exists()){
			userhead_File.mkdirs();
		}
		File targetFile = new File("c:/zhifeng/temp",originalFilename);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		//上传文件重命名
		String storeName = FileUtil.rename(originalFilename);
		try{
			myfile.transferTo(targetFile);
			//---------------------
			//先将图片压缩处理
			CompressPicDemo mypic = new CompressPicDemo();
			//mypic.compressPic(logalFile, "c:\\zhifeng\\temp", file.getName(), imgRealW, imgRealH, false);
			//String 可能为小数，需预处理
			Integer width;
			Integer height;
			if(imgRealW.contains(".")){
				width = Integer.parseInt(imgRealW.substring(0, imgRealW.indexOf(".")))+1;//防止边界截取超过原始大小
			}else{
				width = Integer.parseInt(imgRealW);
			}
			if(imgRealH.contains(".")){
				height = Integer.parseInt(imgRealH.substring(0, imgRealH.indexOf(".")))+1;
			}else{
				height = Integer.parseInt(imgRealH);
			}
			
			/*File userhead_file = new File("c:/zhifeng/user_head");
			if(!userhead_file.exists()){
				userhead_file.mkdirs();
			}*/
			mypic.compressPic(targetFile, "c:\\zhifeng\\temp\\", "ys_"+targetFile.getName(), width, height, false);
			//再截取图片
			ImgTool imgTool  = new ImgTool();
			imgTool.cut("C:\\zhifeng\\temp\\"+"ys_"+targetFile.getName(), Integer.parseInt(imgx1), Integer.parseInt(imgy1), 
					Integer.parseInt(imgx2)-Integer.parseInt(imgx1), Integer.parseInt(imgy2)-Integer.parseInt(imgy1));
			imgTool.save("jpg", storeName, path, Integer.parseInt(imgx2)-Integer.parseInt(imgx1), 
					Integer.parseInt(imgy2)-Integer.parseInt(imgy1));
			
			//--------------------
		}catch(IOException e){
			System.out.println("文件上传失败");
			e.printStackTrace();
		}
		modelpath += storeName;//存入数据库文件后面拼接文件名
		logger.info("modelpath: {}",modelpath);
		return modelpath;
	}

	/**
	 * 个人中心页面(个人和企业用户)--需求列表页面
	 * @param title	标题
	 * @param starttime	发布时间的开始时间
	 * @param endtime	发布时间的截止时间
	 * @param type	需求类型
	 * @param employeename	律师名字
	 * @param request
	 * @return
	 * @throws ParseException
	 * @author wangxu
	 */
	@RequestMapping("/normal/needs")
	public String needList(String title,String starttime,String endtime,String type,String employeename,String status,HttpServletRequest request) throws ParseException
	{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		
		//存放发布需求等数目和关注领域
		List<String> info = userService.userAccountInfo(user2);
		
		List<String> typelist = needService.needTypeList(userId);

		request.setAttribute("info", info);
		request.setAttribute("user", user2);
		request.setAttribute("typelist", typelist);
		request.setAttribute("ulid", "appointPaginator");
		request.setAttribute("function", "allNeeds");
		
		
		return "account/normalUser/needsList";
	}
	
	/**
	 * 所有需求列表的分页
	 * @param title
	 * @param starttime
	 * @param endtime
	 * @param type
	 * @param employeename
	 * @param status
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/normal/allNeeds",method= RequestMethod.GET)
	public PageInfo<Map<String, Object>> allNeeds(String title,String starttime,String endtime,String type,String employeename,String status,HttpServletRequest request) throws ParseException{
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		Page<Map<String, Object>> needs = needService.findNeedByUserId(userId,title,starttime,endtime,type,employeename,status);
		PageInfo<Map<String, Object>> needInfo = new PageInfo<Map<String,Object>>(needs);
		return needInfo;
	}
	
	/**
	 * 等待回复的需求
	 * @param title
	 * @param starttime
	 * @param endtime
	 * @param type
	 * @param employeename
	 * @param status
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value="/normal/waitNeeds",method= RequestMethod.GET)
	public PageInfo<Map<String, Object>> waitNeeds(String title,String starttime,String endtime,String type,String employeename,String status,HttpServletRequest request) throws ParseException{
		// TODO Auto-generated method stub
		//待回复先暂时分两种，一种别人发送了请求没有接受的，一种别人发送了新的回复我没有回复的
		
		return null;
	}
	
	
	/**
	 * 回收站的需求列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/normal/deleteNeeds",method= RequestMethod.GET)
	public PageInfo<Map<String, Object>> deleteNeeds(HttpServletRequest request){
		
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		//回收站的
		PageHelper.startPage(getPageIndex(request), getPageSize(request));
		Page<Map<String, Object>> deleteNeeds = needService.deleteNeeds(userId);
		PageInfo<Map<String, Object>> deleteneedInfo = new PageInfo<Map<String,Object>>(deleteNeeds);
		
		return deleteneedInfo;
	}
	
	
	/**
	 * 展示需求详情
	 * @param needId
	 * @return
	 */
	@RequestMapping("/normal/needs/{needId}")
	public String showNeed(@PathVariable int needId)
	{
		return "account/normalUser/showNeed";
	}
	
	/**
	 * 删除需求：论理删除
	 * @param needid
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping(value="/normal/deleteNeed",method=RequestMethod.POST)
	public String deleteNeed(String needIDs){
		needService.deleteNeed(needIDs);
		
		return "success";
	}
	
	/**
	 * 回收站恢复需求
	 * @param needIDs
	 * @return
	 * @author wangxu
	 */
	@ResponseBody
	@RequestMapping("/normal/recoverNeed")
	public String recoverNeed(String needIDs){
		needService.recoverNeed(needIDs);
		return "success";
	}
	
	
	/**
	 * 个人中心页面(个人和企业用户)--项目列表页面
	 * @return
	 */
	@RequestMapping(value="/normal/projects",method=RequestMethod.GET)
	public String projectList()
	{
		return "account/normalUser/projectsList";
	}
	
	/**
	 * 个人中心页面(个人和企业用户)--难题列表页面
	 * @return
	 */
	@RequestMapping(value="/normal/problems",method=RequestMethod.GET)
	public String problemList()
	{
		return "account/normalUser/problemsList";
	}
	
	/**
	 * 个人中心页面(个人和企业用户)--收藏的中介机构列表页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/normal/stores",method=RequestMethod.GET)
	public String storeList(HttpServletRequest request) throws Exception{
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		request.setAttribute("user", user2);
		return "account/normalUser/storesList";
	}
	/**
	 * 普通用户收藏页面检索
	 * @param request
	 * @param type
	 * @param name
	 * @param province
	 * @param city
	 * @param district
	 * @return
	 * @author teli
	 */
	@RequestMapping(value="/normal/stores",method=RequestMethod.POST)
	public String searchStoreList(HttpServletRequest request, Integer type, String name, String province, String city, String district){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();//登录用户的信息
		Integer userId = user.getId();
		ZFUser user2 = (ZFUser) userService.selectByUserId(0, userId);
		List<HashMap<String,Object>> agency = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> agencyEmployee = new ArrayList<HashMap<String,Object>>();
		if("请选择".equals(province)){
			province=null;
		}
		if("请选择".equals(city)){
			city=null;
		}
		if("请选择".equals(district)){
			district=null;
		}
		String agencyname = null;
		if(type==0){//中介机构检索
			agency = storeService.searchResultMap(userId, ZFStore.PERSON_A, name, agencyname, province, city, district);
			agencyEmployee = storeService.selectResultMap(userId, ZFStore.PERSON_E);
			request.setAttribute("column", 1);
		}
		if(type==1){//中介机构从业人员检索
			agencyEmployee = storeService.searchResultMap(userId, ZFStore.PERSON_E, name, agencyname, province, city, district);
			agency = storeService.selectResultMap(userId, ZFStore.PERSON_A);
			request.setAttribute("column", 0);
		}
		request.setAttribute("location", 0);//搜索返回时，页面不用定位到最上角
		request.setAttribute("user", user2);
		request.setAttribute("agencyEmployee", agencyEmployee);
		request.setAttribute("agency", agency);
		return "account/normalUser/storesList";
	}
	
	/**
	 * 个人中心页面(个人和企业用户)--意见反馈
	 * @return
	 */
	@RequestMapping(value="/normal/feedback",method=RequestMethod.GET)
	public String feedback()
	{
		return "account/normalUser/feedback";
	}
}
