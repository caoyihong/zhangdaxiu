package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;

public class ZFProblem implements Serializable{
	private Integer id;
	
	private String type;
	
	private Integer userid;
	
	private String title;
	
	private int address;
	
	private String email;
	
	private String contacts;
	
	private String phone;
	
	private String fax;
	
	private String postcode;
	
	private String detail;
	
	private Date finishtime;
	
	private Date endtime;
	
	private String economicindicators;
	
	private String resultform;
	
	private String resultowner;
	
	private String fundoffered;
	
	private String keywords;
	
	private String remark;
	
	private Date createtime;
	
	private Date updatetime;
	
	private Byte enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getEconomicindicators() {
		return economicindicators;
	}

	public void setEconomicindicators(String economicindicators) {
		this.economicindicators = economicindicators;
	}

	public String getResultform() {
		return resultform;
	}

	public void setResultform(String resultform) {
		this.resultform = resultform;
	}

	public String getResultowner() {
		return resultowner;
	}

	public void setResultowner(String resultowner) {
		this.resultowner = resultowner;
	}

	public String getFundoffered() {
		return fundoffered;
	}

	public void setFundoffered(String fundoffered) {
		this.fundoffered = fundoffered;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public ZFProblem() {
	}
	
}
