package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;

public class ZFMessage implements Serializable {
    private Integer id;

    private Integer needid;

    private Integer userid;

    private Integer employeeid;

    private String content;

    private Integer type;

    private Integer isread;

    private Date createtime;

    private Date updatetime;

    private Byte enabled;
    
    private ZFUser user;
    
    private ZFAgencyemployee agencyemployee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNeedid() {
        return needid;
    }

    public void setNeedid(Integer needid) {
        this.needid = needid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
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

	@Override
	public String toString() {
		return "ZFMessage [id=" + id + ", needid=" + needid + ", userid="
				+ userid + ", employeeid=" + employeeid + ", content="
				+ content + ", type=" + type + ", isread=" + isread
				+ ", createtime=" + createtime + ", updatetime=" + updatetime
				+ ", enabled=" + enabled + "]";
	}

	public ZFUser getUser() {
		return user;
	}

	public void setUser(ZFUser user) {
		this.user = user;
	}

	public ZFAgencyemployee getAgencyemployee() {
		return agencyemployee;
	}

	public void setAgencyemployee(ZFAgencyemployee agencyemployee) {
		this.agencyemployee = agencyemployee;
	}
}