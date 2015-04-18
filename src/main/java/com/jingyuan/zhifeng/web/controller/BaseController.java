package com.jingyuan.zhifeng.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jingyuan.zhifeng.core.utils.GlobalStatic;


public class BaseController {
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	public String redirect = "redirect:";
	
	 /**
	  * 检查saved路径是否是ajax请求，如果是则直接跳转到首页
	  * 如果有则返回false
	  * @param successUrl
	  * @return
	  */
	protected boolean chekSavedUrl(String successUrl) {
		
		List<String> ajaxUrls = new ArrayList<String>();
		ajaxUrls.add("/categories");
		ajaxUrls.add("/logout");
		for(String url : ajaxUrls)
		{
			if(successUrl.indexOf(url) != -1)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获取当前页，如果为0则表示没有用到分页
	 * @param request
	 * @return
	 */
	protected int getPageIndex(HttpServletRequest request)
	{
		int index = 1;
		try
		{
			index = Integer.valueOf(request.getParameter("currentPage"));
		}
		catch(Exception e)
		{
			index = 1;
		}
		return index;
	}
	
	/**
	 * 获取每页显示条数pageSize
	 * @return
	 */
	protected int getPageSize(HttpServletRequest request) {
		int index = 10;
		try
		{
			index = Integer.valueOf(request.getParameter("pageSize"));
		}
		catch(Exception e)
		{
			index = 10;
		}
		return index;
	}
	
	/**
	 * 随机生成form表单令牌并存入session中
	 * 
	 * @param request
	 * @return
	 */
	public String generateToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			byte id[] = session.getId().getBytes();
			byte now[] = new Long(System.currentTimeMillis()).toString()
					.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			@SuppressWarnings("unchecked")
			List<String> tokenList = (List<String>) session.getAttribute(GlobalStatic.FORMTOKEN);
			if (tokenList == null) {
				tokenList = new ArrayList<String>();
			}
			if (tokenList.size() == 100) {
				tokenList.clear();
			}
			String token = convertToHexString(md.digest());
			tokenList.add(token);
			session.setAttribute(GlobalStatic.FORMTOKEN, tokenList);
			return token;
		} catch (IllegalStateException e) {
			return null;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

	}

	/**
	 * 判断form令牌是否与session一致，不一致则返回false
	 * 
	 * @param request
	 * @return
	 */
	public synchronized boolean isTokenValid(HttpServletRequest request,boolean reset) {
		
		HttpSession session = request.getSession();
		if(session == null)
		{
			return false;
		}
		String token = request.getParameter(GlobalStatic.FORMTOKEN);
		if (StringUtils.isNotEmpty(token)) {
			
			@SuppressWarnings("unchecked")
			List<String> saved = (List<String>) session.getAttribute(GlobalStatic.FORMTOKEN);
			if(saved == null || saved.size() == 0)
			{
				return false;
			}
			if(reset) this.resetToken(request);
			
			if (saved.contains(token)) {
				saved.remove(token);
				return true;
			}
		}
		return false;
	}

	/**
	 * 重置form令牌
	 * 
	 * @param request
	 * @return 
	 * @return
	 */
	public synchronized String resetToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null)
		{
			return generateToken(request);
		}
		return null;
	}

	private String convertToHexString(byte[] bytes) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			strBuffer.append(Integer.toHexString(0xff & bytes[i]));
		}
		return strBuffer.toString();
	}
}
