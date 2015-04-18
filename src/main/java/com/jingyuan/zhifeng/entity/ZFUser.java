package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;

public class ZFUser implements Serializable{
    private Integer id;

    private String name;

    private Integer type;

    private Integer status;

    private String password;

    private Integer address;

    private String email;

    private String postcode;

    private String phone;

    private String fax;

    private String realname;

    private String number;

    private String picture;

    private String head;

    private String introduction;

    private String industry;

    private String attention;

    private Date createtime;

    private Date updatetime;

    private Byte enabled;
    
    private String salt;
    
    private ZFAddress zfAddress;
    
    private String park;
    
    private String selfintroduce;

    private Date passwdtime;//记录最近修改密码时间
    
    public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getPasswdtime() {
		return passwdtime;
	}

	public void setPasswdtime(Date passwdtime) {
		this.passwdtime = passwdtime;
	}

	@Override
	public String toString() {
		return "ZFUser [id=" + id + ", name=" + name + ", type=" + type
				+ ", status=" + status + ", password=" + password
				+ ", address=" + address + ", email=" + email + ", postcode="
				+ postcode + ", phone=" + phone + ", fax=" + fax
				+ ", realname=" + realname + ", number=" + number
				+ ", picture=" + picture + ", head=" + head + ", introduction="
				+ introduction + ", industry=" + industry + ", attention="
				+ attention + ", createtime=" + createtime + ", updatetime="
				+ updatetime + ", enabled=" + enabled + ", salt=" + salt
				+ ", zfAddress=" + zfAddress + ", park=" + park
				+ ", selfintroduce=" + selfintroduce + ", getPark()="
				+ getPark() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getType()=" + getType() + ", getStatus()="
				+ getStatus() + ", getPassword()=" + getPassword()
				+ ", getAddress()=" + getAddress() + ", getEmail()="
				+ getEmail() + ", getPostcode()=" + getPostcode()
				+ ", getPhone()=" + getPhone() + ", getFax()=" + getFax()
				+ ", getRealname()=" + getRealname() + ", getNumber()="
				+ getNumber() + ", getPicture()=" + getPicture()
				+ ", getHead()=" + getHead() + ", getIntroduction()="
				+ getIntroduction() + ", getIndustry()=" + getIndustry()
				+ ", getAttention()=" + getAttention() + ", getCreatetime()="
				+ getCreatetime() + ", getUpdatetime()=" + getUpdatetime()
				+ ", getEnabled()=" + getEnabled() + ", getSalt()=" + getSalt()
				+ ", getCredentialsSalt()=" + getCredentialsSalt()
				+ ", getZfAddress()=" + getZfAddress()
				+ ", getSelfintroduce()=" + getSelfintroduce()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	/**
	 * 获取新盐
	 * @return
	 */
	public String getCredentialsSalt()
	{
		return name + salt;
	}

	public ZFAddress getZfAddress() {
		return zfAddress;
	}

	public void setZfAddress(ZFAddress zfAddress) {
		this.zfAddress = zfAddress;
	}

	public String getSelfintroduce() {
		return selfintroduce;
	}

	public void setSelfintroduce(String selfintroduce) {
		this.selfintroduce = selfintroduce;
	}
	
}