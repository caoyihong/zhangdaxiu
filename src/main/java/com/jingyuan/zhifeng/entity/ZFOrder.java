package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZFOrder implements Serializable{
	
	private Integer id;

	private Integer userid;
	
	private Integer agencyemployeeid;
	
	private Integer status;
	
	private String type;
	//律师服务评价 1不满意 2满意 3很满意
	private String servicerank;
	//服务时间评价 1慢 2快 3非常快	
	private String timerank;
	
	private String judge;
	
	private Date createtime;
	
	private Date updatetime;
	
	private Byte enabled;
	
	private Date finishtime;
	
	private Date accepttime;
	
	private ZFAgencyemployee agencye;
	
	private ZFUser normalUser;

	private BigDecimal price;
	
	private Integer needid;
	
	private Integer ordertype;
	
	private ZFNeed need;
	
	private Integer rank;
	
	@Override
	public String toString() {
		return "ZFOrder [id=" + id + ", userid=" + userid
				+ ", agencyemployeeid=" + agencyemployeeid + ", status="
				+ status + ", type=" + type + ", servicerank=" + servicerank
				+ ", timerank=" + timerank + ", judge=" + judge
				+ ", createtime=" + createtime + ", updatetime=" + updatetime
				+ ", enabled=" + enabled + ", finishtime=" + finishtime
				+ ", accepttime=" + accepttime + ", agencye=" + agencye.toString()
				+ ", normalUser=" + normalUser.toString() +", price=" + price + "]";
	}

	public ZFAgencyemployee getAgencye() {
		return agencye;
	}

	public void setAgencye(ZFAgencyemployee agencye) {
		this.agencye = agencye;
	}

	/**
	 * @return the normalUser
	 */
	public ZFUser getNormalUser() {
		return normalUser;
	}

	/**
	 * @param normalUser the normalUser to set
	 */
	public void setNormalUser(ZFUser normalUser) {
		this.normalUser = normalUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getAgencyemployeeid() {
		return agencyemployeeid;
	}

	public void setAgencyemployeeid(Integer agencyemployeeid) {
		this.agencyemployeeid = agencyemployeeid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServicerank() {
		return servicerank;
	}

	public void setServicerank(String servicerank) {
		this.servicerank = servicerank;
	}

	public String getTimerank() {
		return timerank;
	}

	public void setTimerank(String timerank) {
		this.timerank = timerank;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
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

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public Date getAccepttime() {
		return accepttime;
	}

	public void setAccepttime(Date accepttime) {
		this.accepttime = accepttime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNeedid() {
		return needid;
	}

	public void setNeedid(Integer needid) {
		this.needid = needid;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public ZFNeed getNeed() {
		return need;
	}

	public void setNeed(ZFNeed need) {
		this.need = need;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}
