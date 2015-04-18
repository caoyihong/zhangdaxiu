package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;

public class ZFStore implements Serializable{
	
//	0代表个人关注中介机构，1代表个人关注中介机构从业人员，2代表中介机构人员关注中介机构，3代表中介机构人员关注中介机构人员
	
	public static final int PERSON_A = 0;
	public static final int PERSON_E = 1;
	public static final int AGENCYE_A = 2;
	public static final int AGENCYE_E = 3;
	
	private int id;
	//0代表个人关注中介机构，1代表个人关注中介机构从业人员，
	//2代表中介机构人员关注中介机构，3代表中介机构人员关注中介机构人员
	private int type;
	
	private int userid;
	
	private int anencyid;

	private Date createtime;
	
	private Date updatetime;
	
	 private int enabled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAnencyid() {
		return anencyid;
	}

	public void setAnencyid(int anencyid) {
		this.anencyid = anencyid;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


}
