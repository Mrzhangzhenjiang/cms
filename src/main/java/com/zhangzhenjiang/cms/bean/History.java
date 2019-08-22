package com.zhangzhenjiang.cms.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class History implements Serializable{
	/**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月31日
	 */
	private static final long serialVersionUID = 1L;
	private int hid;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date htime;
	private int uid;
	private int aid;
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public Date getHtime() {
		return htime;
	}
	public void setHtime(Date htime) {
		this.htime = htime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public History(int hid, Date htime, int uid, int aid) {
		super();
		this.hid = hid;
		this.htime = htime;
		this.uid = uid;
		this.aid = aid;
	}
	public History() {
		super();
	}
	@Override
	public String toString() {
		return "History [hid=" + hid + ", htime=" + htime + ", uid=" + uid + ", aid=" + aid + "]";
	}
	
}
