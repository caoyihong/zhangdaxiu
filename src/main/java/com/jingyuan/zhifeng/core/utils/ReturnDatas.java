package com.jingyuan.zhifeng.core.utils;

import java.util.Map;

/**
 * 返回对象的封装
 * @author caomei
 *
 */
@SuppressWarnings("serial")
public class ReturnDatas {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String WARNING = "warning";
	private String statusCode="200";
	private String status;
	private String msg = "";
	private Object data;
    private Map map;
	private Page page;
	private Object queryBean;
	
	public ReturnDatas() {
		
	}
	
	public static ReturnDatas getSuccessReturnDatas() {
	return new ReturnDatas(ReturnDatas.SUCCESS);
	}
	public static ReturnDatas getErrorReturnDatas() {
		return new ReturnDatas(ReturnDatas.ERROR);
		}
	public static ReturnDatas getWarningReturnDatas() {
		return new ReturnDatas(ReturnDatas.WARNING);
		}
	
	
	public ReturnDatas(String status) {
		this.status = status;
	}
	
	public ReturnDatas(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public ReturnDatas(String status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object obj) {
		this.data = obj;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Object getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(Object queryBean) {
		this.queryBean = queryBean;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
