package com.jingyuan.zhifeng.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 新闻表
 * @author caoyihong
 *
 * 2015年4月16日
 */
public class ZFNews implements Serializable{

	private Integer id;
	
	private String category;
	
	private String title;
	
	private String content;
	
	private String picture;
	
	private Integer visitors;
	
	private Date createtime;
	
	private Date updatetime;
	
	private Integer enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getVisitors() {
		return visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ZFNews() {
	}

	@Override
	public String toString() {
		return "ZFNews [id=" + id + ", category=" + category + ", title="
				+ title + ", content=" + content + ", picture=" + picture
				+ ", visitors=" + visitors + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", enabled=" + enabled + "]";
	}
}
