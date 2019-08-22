package com.zhangzhenjiang.cms.bean;

import java.io.Serializable;
/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 类功能描述
 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
 * <br>Date:2019年7月22日
 */
public class Category implements Serializable{
    /**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月22日
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Channel channel;

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
		this.name = name;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Category(Integer id, String name, Channel channel) {
		super();
		this.id = id;
		this.name = name;
		this.channel = channel;
	}

	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", channel=" + channel + "]";
	}
    
    
}