package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ZFAgency implements Serializable{
    private Integer id;

    private String name;

    private Integer status;

    private String phone;

    private String password;

    private String postcode;

    private String fax;

    private String email;

    private String realname;

    private String idcard;

    private String idpicture;

    private String companypicture;

    private String companynum;

    private String city;

    private Integer address;

    private String companyweb;

    private String service;

    private String head;

    private Date createtime;

    private Date updatetime;

    private Byte enabled;

    private Integer parentid;
    
    private String introduce;
    
    private String teamintroduce;

    private String teampurpose;

    private String agencytype;
    
    private String reservation;
    
    private List<ZFAgencyemployee> agencyes;
    
    private String salt;
    
    private Integer orderCount;
    
    private ZFAddress zfAddress;
    
    private String selfintroduce;
    
    public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "ZFAgency [id=" + id + ", name=" + name + ", status=" + status
				+ ", phone=" + phone + ", password=" + password + ", postcode="
				+ postcode + ", fax=" + fax + ", email=" + email
				+ ", realname=" + realname + ", idcard=" + idcard
				+ ", idpicture=" + idpicture + ", companyname=" + 
				companypicture + ", companynum="
				+ companynum + ", city=" + city + ", address=" + address
				+ ", companyweb=" + companyweb + ", service=" + service
				+ ", head=" + head + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", enabled=" + enabled
				+ ", parentid=" + parentid + ", introduce=" + introduce
				+ ", teamintroduce=" + teamintroduce + ", teampurpose="
				+ teampurpose + ", agencytype=" + agencytype + ", reservation="
				+ reservation + ", salt=" + salt
				+ ", orderCount=" + orderCount + ", selfintroduce=" + selfintroduce + "]";
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getIdpicture() {
        return idpicture;
    }

    public void setIdpicture(String idpicture) {
        this.idpicture = idpicture == null ? null : idpicture.trim();
    }

    public String getCompanypicture() {
        return companypicture;
    }

    public void setCompanypicture(String companypicture) {
        this.companypicture = companypicture == null ? null : companypicture.trim();
    }

    public String getCompanynum() {
		return companynum;
	}

	public void setCompanynum(String companynum) {
		this.companynum = companynum;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getCompanyweb() {
        return companyweb;
    }

    public void setCompanyweb(String companyweb) {
        this.companyweb = companyweb == null ? null : companyweb.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
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

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getTeamintroduce() {
		return teamintroduce;
	}

	public void setTeamintroduce(String teamintroduce) {
		this.teamintroduce = teamintroduce;
	}

	public String getTeampurpose() {
		return teampurpose;
	}

	public void setTeampurpose(String teampurpose) {
		this.teampurpose = teampurpose;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public List<ZFAgencyemployee> getAgencyes() {
		return agencyes;
	}

	public void setAgencyes(List<ZFAgencyemployee> agencyes) {
		this.agencyes = agencyes;
	}
    
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public ZFAddress getZfAddress() {
		return zfAddress;
	}

	public void setZfAddress(ZFAddress zfAddress) {
		this.zfAddress = zfAddress;
	}

	public String getAgencytype() {
		return agencytype;
	}

	public void setAgencytype(String agencytype) {
		this.agencytype = agencytype;
	}

	public String getSelfintroduce() {
		return selfintroduce;
	}

	public void setSelfintroduce(String selfintroduce) {
		this.selfintroduce = selfintroduce;
	}
	/**
	 * 获取新盐
	 * @return
	 */
	public String getCredentialsSalt()
	{
		return name + salt;
	}
}