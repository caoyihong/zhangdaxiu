package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZFNeed implements Serializable{
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

    private BigDecimal price;

    private String resultform;

    private Date finishtime;

    private Date endtime;

    private String ownership;

    private String fund;

    private String keyword;

    private String remark;

    private Date createtime;

    private Date updatetime;

    private Byte enabled;
    
    private String target;
    
    private ZFAddress zfAddress;
    
    private ZFUser user;
    
    @Override
	public String toString() {
		return "ZFNeed [id=" + id + ", type=" + type + ", userid=" + userid
				+ ", title=" + title + ", address=" + address + ", email="
				+ email + ", contacts=" + contacts + ", phone=" + phone
				+ ", fax=" + fax + ", postcode=" + postcode + ", detail="
				+ detail + ", price=" + price + ", resultform=" + resultform
				+ ", finishtime=" + finishtime + ", endtime=" + endtime
				+ ", ownership=" + ownership + ", fund=" + fund + ", keyword="
				+ keyword + ", remark=" + remark + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", enabled=" + enabled
				+ ", target=" + target + "]";
	}

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

	public void setAddress(int address) {
		this.address = address;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getResultform() {
        return resultform;
    }

    public void setResultform(String resultform) {
        this.resultform = resultform == null ? null : resultform.trim();
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

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund == null ? null : fund.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ZFAddress getZfAddress() {
		return zfAddress;
	}

	public void setZfAddress(ZFAddress zfAddress) {
		this.zfAddress = zfAddress;
	}

	public ZFUser getUser() {
		return user;
	}

	public void setUser(ZFUser user) {
		this.user = user;
	}
	
}