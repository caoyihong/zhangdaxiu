package com.jingyuan.zhifeng.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingyuan.zhifeng.core.utils.FileUtil;
import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.entity.ZFNews;
import com.jingyuan.zhifeng.service.ZFNewsService;
import com.jingyuan.zhifeng.service.ZFUserService;



@Controller
public class CommonController extends BaseController{
	
	@Autowired
	private ZFNewsService newsService;
	
	@Autowired
	private ZFUserService userService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model)
	{
//		新闻动态
//		最新发布的6个知识产权项目
//		最新发布的6个难题招标
//		最新发布的需求
//		平台案例展示(可收费)，知识产权交易、法务、服务各两个
//		法务咨询10个
		return "index";
	}
	
//  发送手机验证码，返回发送的动态验证码，暂时验证码没有有效期，比如：动态验证码5分钟失效
//	状态：1.验证码发送频率过大, 请过1~3小时再试!,2.验证码已发送, 请注意查收
	@RequestMapping("/common/phonecode")
	@ResponseBody
	public int sendPhoneCode(String phone,String type) {
		//当type不为空切type=1的时候，是个人注册，判断是否有用户用这个手机号码注册过	type =1
		//当type不为空切type=2的时候，是企业注册，判断是否有用户用这个手机号码注册过	type =2
		//当type不为空切type=3的时候，是中介机构从业人员注册，判断是否有用户用这个手机号码注册过 	type = 3
		Object user = userService.isExistPhone(phone,null,Integer.valueOf(type));
		
		//user != null说明手机已经注册过，直接返回
		if (user != null) {
			return 0;
		}
		
		/*
		CodePoll codepoll = MessageUtil.getCode(phone);

		// 第一次直接发送
		if (codepoll == null) {
			int code = MessageUtil.send(phone);
			long outtime = System.currentTimeMillis() + 5 * 60 * 1000;
			codepoll = new CodePoll(1, outtime, code);
			MessageUtil.setCode(phone, codepoll);
			return MessageUtil.getCode(phone).getCode();
		} else {
//			检验发送次数，不大于10就会发送，10次以上则不会发送
			String msg = MessageUtil.checkSetCode(phone);
			return  MessageUtil.getCode(phone).getCode();
		}
		*/
		return 1234;
	}
	
	
	public static void downImg(HttpServletResponse response, InputStream in) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/png;charset=utf-8");
		try {
			FileUtil.copy(in, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 图片显示 ${showImg }
	 * @param res
	 * @param url
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/showimg", method = RequestMethod.GET)
	public void showImg(HttpServletResponse res, String url) throws FileNotFoundException, UnsupportedEncodingException {
		url = new String(url.getBytes("iso8859-1"),"utf-8");
		String imgUrl = GlobalStatic.uploadpath + url;
		if (url != null && !url.isEmpty()&& !url.equals("null")) {
			File file = new File(imgUrl);
			if (file.exists()) {
				CommonController.downImg(res, new FileInputStream(imgUrl));
			}
		} else {
			CommonController.downImg(res, new FileInputStream(GlobalStatic.uploadpath+"user_image/user-pic.png"));
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "redirect:/";
	}
	
	@RequestMapping("/defined")
	public String defined()
	{
		return "common/defined";
	}
	/**
	 * 合作联系
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/cooperate")
	public String cooperate(HttpServletRequest request) {
		
		return "common/cooperate";
	}
	
	/**
	 * 客服中心
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/cservice")
	public String cservice(HttpServletRequest request) {
		
		return "common/cservice";
	}
	
	/**
	 * 网站建设
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/jianshe")
	public String jianshe(HttpServletRequest request) {
		
		return "common/jianshe";
	}

	/**
	 * 新闻中心，展示所有新闻列表
	 * @param request
	 * @return
	 * @author teli
	 */
	@RequestMapping("/common/newscenter")
	public String newsCenter(HttpServletRequest request) {
		List<ZFNews> news = newsService.findAll();
		request.setAttribute("lists", news);
		return "common/newsCenter";
	}
	/**
	 * 展示新闻详细信息
	 * @param request
	 * @return
	 * @author teli
	 */
	@RequestMapping("/common/newsdetail/{id}")
	public String newsDetail(@PathVariable int id, HttpServletRequest request){
		
		ZFNews news = newsService.findById(id);
		request.setAttribute("news", news);
		return "common/newsdetail";
	}
	/**
	 * 测试文本编辑
	 * @return
	 * @author teli
	 */
	@RequestMapping("/common/editnews")
	public String testEditNews(){
		
		return "common/editnews";
	}
	/**
	 * 测试文本编辑ajax提交
	 * @return
	 * @author teli
	 */
	@ResponseBody
	@RequestMapping(value="/common/submitnews",method=RequestMethod.POST)
	public String ajaxSubmit(HttpServletRequest request){
		
		String content = request.getParameter("content");
		System.out.println(content);
		return "success";
	}
	/**
	 * 测试文本编辑中图片上传部分
	 * @return
	 * @author teli
	 */
	
	@ResponseBody
	@RequestMapping("/common/impUpload")
	public String ImgUpload(@RequestParam MultipartFile fileData, String fileDataFileName, HttpServletRequest request){
		
		String modelpath = FileUtil.upload(fileData, request, "newsImg");
		String  msg = "{\"success\":\"" + true + "\",\"/showimg?url=\":\"" + modelpath + "\"}"; 
		return msg;
	}
	/**
	 * 关于我们
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/aboutus")
	public String aboutUs(HttpServletRequest request) {
		
		return "common/aboutUs";
	}
	
	/**
	 * 帮助中心
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/helpcenter")
	public String helpCenter(HttpServletRequest request)
	{
		return "common/helpCenter";
	}
	
	/**
	 * 帮助中心-常见问题列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/helpcenter/problems")
	public String problems(HttpServletRequest request)
	{
		return "common/problems";
	}
	
	/**
	 * 帮助中心-具体常见问题
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/helpcenter/problems/{problemId}")
	public String showProblem(HttpServletRequest request)
	{
		return "common/showProblem";
	}
	
	/**
	 * 专利下载
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/dowip")
	public String dowip(HttpServletRequest request)
	{
		return "common/dowip";
	}
	
	/**
	 * 案例展示列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/showcase")
	public String caseList(HttpServletRequest request)
	{
		return "common/caseList";
	}
	/**
	 * 案例展示-具体
	 * @param request
	 * @return
	 */
	@RequestMapping("/common/showcase/{caseId}")
	public String showCase(HttpServletRequest request)
	{
		return "common/showCase";
	}
}
