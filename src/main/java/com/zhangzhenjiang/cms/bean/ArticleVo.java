/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleVo.java 
 * @Prject: chengongjun_cms
 * @Package: com.chengongjun.chengongjun_cms.domain 
 * @Description: TODO
 * @author: chj   
 * @date: 2019年7月22日 下午3:22:49 
 * @version: V1.0   
 */
package com.zhangzhenjiang.cms.bean;

/** 
 * @ClassName: ArticleVo 
 * @Description: TODO
 * @author: chj
 * @date: 2019年7月22日 下午3:22:49  
 */
public class ArticleVo extends Article {
	/**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月30日
	 */
	private static final long serialVersionUID = 1L;
	private String sort;
	private String desc;
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
