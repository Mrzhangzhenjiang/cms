package com.zhangzhenjiang.cms.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 类功能描述
 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
 * <br>Date:2019年7月22日
 */
public class Channel implements Serializable{
    /**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月22日
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String description;

    private String icon;

    private List<Category> categories;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Channel(Integer id, String name, String description, String icon, List<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.categories = categories;
	}

	public Channel() {
		super();
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon
				+ ", categories=" + categories + "]";
	}
    
}