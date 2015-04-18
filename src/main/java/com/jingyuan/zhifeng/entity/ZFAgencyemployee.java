package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ZFAgencyemployee implements Serializable{
    private Integer id;

    private String name;

    private String phone;

    private String password;

    private String email;

    private String realname;

    private String idcard;

    private String idpicture;

    private Integer agencyid;

    private String skillid;

    private String city;

    private String head;

    private Integer status;

    private String introduce;

    private String position;

    private Date createtime;

    private Date updatetime;
    
    private Date passwdtime;//记录最近修改密码时间

    private Byte enabled;
    
    private String salt;
    
    private Integer worktime;
    
    private Integer identity;
    
    private ZFSkillc skill;

    private Integer weight;
    
    private String certificate;
    
    private String certificatepicture;
    
    private String workphone;
    
    private ZFAgency agency;
    
//  工作领域
    private List<String> services;
    
	public ZFSkillc getSkill() {
		return skill;
	}

	public void setSkill(ZFSkillc skill) {
		this.skill = skill;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "ZFAgencyemployee [id=" + id + ", name=" + name + ", phone="
				+ phone + ", password=" + password + ", email=" + email
				+ ", realname=" + realname + ", idcard=" + idcard
				+ ", idpicture=" + idpicture + ", agencyid=" + agencyid
				+ ", skillid=" + skillid + ", city=" + city + ", head=" + head
				+ ", status=" + status + ", introduce=" + introduce
				+ ", position=" + position + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", enabled=" + enabled
				+ ", salt=" + salt + ", worktime=" + worktime +", identity=" + identity 
				+ ", weight=" + weight  + "]";
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

    public Integer getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(Integer agencyid) {
        this.agencyid = agencyid;
    }

    public String getSkillid() {
        return skillid;
    }

    public void setSkillid(String skillid) {
        this.skillid = skillid == null ? null : skillid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
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
    
	/**
	 * 获取新盐
	 * @return
	 */
	public String getCredentialsSalt()
	{
		return name + salt;
	}

	public Integer getWorktime() {
		return worktime;
	}

	public void setWorktime(Integer worktime) {
		this.worktime = worktime;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getCertificatepicture() {
		return certificatepicture;
	}

	public void setCertificatepicture(String certificatepicture) {
		this.certificatepicture = certificatepicture;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public ZFAgency getAgency() {
		return agency;
	}

	public void setAgency(ZFAgency agency) {
		this.agency = agency;
	}

	public Date getPasswdtime() {
		return passwdtime;
	}

	public void setPasswdtime(Date passwdtime) {
		this.passwdtime = passwdtime;
	}

}