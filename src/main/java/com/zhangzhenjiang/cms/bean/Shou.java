package com.zhangzhenjiang.cms.bean;

import java.io.Serializable;
import java.util.Date;

public class Shou implements Serializable{
	/**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月30日
	 */
	private static final long serialVersionUID = 1L;
	private int sid;
	private Date stime;
	private int aid;
	private int uid;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Shou(int sid, Date stime, int aid, int uid) {
		super();
		this.sid = sid;
		this.stime = stime;
		this.aid = aid;
		this.uid = uid;
	}
	public Shou() {
		super();
	}
	@Override
	public String toString() {
		return "Shou [sid=" + sid + ", stime=" + stime + ", aid=" + aid + ", uid=" + uid + "]";
	}
	
}
