package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;

public class ZFAddress implements Serializable{
    private Integer addressid;

    private String province;

    private String city;

    private String district;

    private String community;

    private String detail;

    private Integer userid;

    private Integer type;

    private Date createtime;

    private Date updatetime;

    private Byte enabled;

    
    @Override
	public String toString() {
		return "ZFAddress [addressid=" + addressid + ", province=" + province
				+ ", city=" + city + ", district=" + district + ", community="
				+ community + ", detail=" + detail + ", userid=" + userid
				+ ", type=" + type + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", enabled=" + enabled + "]";
	}

	public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community == null ? null : community.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}