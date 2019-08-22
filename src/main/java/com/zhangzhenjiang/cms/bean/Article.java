package com.zhangzhenjiang.cms.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 类功能描述
 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
 * <br>Date:2019年7月22日
 */
@Document(indexName="zhangzhenjiang_cms",type="zhangzhenjiang_cms_article",shards=1,replicas=1)
public class Article implements Serializable{
    /**
	 * <br>Description:TODO 变量描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月22日
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	@Field(type=FieldType.Text)
    private String title;
	@Field(index=false)
    private String picture;

    private Integer channelId;

    private Integer categoryId;

    private Integer userId;

    private Integer hits;

    private Integer hot;

    private Integer status;

    private Integer deleted;

    private Date created;

    private Date updated;

    private String content;
    
    private String keywords;
    
    private String original;

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public Article(Integer id, String title, String picture, Integer channelId, Integer categoryId, Integer userId,
			Integer hits, Integer hot, Integer status, Integer deleted, Date created, Date updated, String content,
			String keywords, String original) {
		super();
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.channelId = channelId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.content = content;
		this.keywords = keywords;
		this.original = original;
	}

	public Article() {
		super();
	}

	public Article(String title, String picture, Integer channelId, Integer categoryId, Integer userId, Integer hits,
			Integer hot, Integer status, Integer deleted, Date created, Date updated, String content, String keywords,
			String original) {
		super();
		this.title = title;
		this.picture = picture;
		this.channelId = channelId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.content = content;
		this.keywords = keywords;
		this.original = original;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", picture=" + picture + ", channelId=" + channelId
				+ ", categoryId=" + categoryId + ", userId=" + userId + ", hits=" + hits + ", hot=" + hot + ", status="
				+ status + ", deleted=" + deleted + ", created=" + created + ", updated=" + updated + ", content="
				+ content + ", keywords=" + keywords + ", original=" + original + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}