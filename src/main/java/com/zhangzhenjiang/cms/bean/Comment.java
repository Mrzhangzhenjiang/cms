package com.zhangzhenjiang.cms.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Comment implements Serializable{
	/**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月28日
	 */
	private static final long serialVersionUID = 1L;
	private int cid;
	private String ccontent;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date ctime;
	private int aid;
	private int uid;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
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
	public Comment(int cid, String ccontent, Date ctime, int aid, int uid) {
		super();
		this.cid = cid;
		this.ccontent = ccontent;
		this.ctime = ctime;
		this.aid = aid;
		this.uid = uid;
	}
	public Comment() {
		super();
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", ccontent=" + ccontent + ", ctime=" + ctime + ", aid=" + aid + ", uid=" + uid
				+ "]";
	}
	
}
