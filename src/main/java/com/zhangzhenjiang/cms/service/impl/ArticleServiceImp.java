package com.zhangzhenjiang.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.mapper.ArticleMapper;
import com.zhangzhenjiang.cms.mapper.SubjectMapper;
import com.zhangzhenjiang.cms.service.ArticleService;
@Service
public class ArticleServiceImp implements ArticleService{
	@Resource
	private ArticleMapper articlemapper;
	@Resource
	private SubjectMapper subjectmapper;
	@Resource
	private RedisTemplate<String,Article> redisTemplate;
	@Override
	public boolean insert(Article article,Integer[] sids) {
		try {

			// 1向文章表保存
			articlemapper.insert(article);
			// 2向专题中间表保存
			if (null != sids) {
				for (Integer sid : sids) {
					subjectmapper.insert(article.getId(), sid);
				}
			}
			return true;
		} catch (Exception e) {
			// 抛出异常..用来回滚事务
			throw new RuntimeException("发布失败" + e.getMessage());
		}
	}
	@Override
	public List<Map> listByUserId(Integer userId) {
		return articlemapper.listByUserId(userId);
	}
	@Override
	public Map get(Integer id) {
		return articlemapper.get(id);
	}
	@Override
	public List<Map> selectList(String status) {
		return articlemapper.selectList(status);
	}
	@Override
	public int update(Article article) {
		return articlemapper.update(article);
	}
	@Override
	public List<Map> selectHotList(String titleorcontent) {
		return articlemapper.selectHotList(titleorcontent);
	}
	@Override
	public List<Map> selectListByCId(Integer cid) {
		return articlemapper.selectListByCId(cid);
	}
	@Override
	public List<Map> selectListByCatId(Integer catid) {
		return articlemapper.selectListByCatId(catid);
	}
	@Override
	public List<Map> selectListByCIdCatId(String cid, String catId) {
		return articlemapper.selectListByCIdCatId(cid, catId);
	}
	@Override
	public Article selectArticleByAid(Integer aid) {
		return articlemapper.selectArticleByAid(aid);
	}
	@Override
	public boolean incrHits(Integer aid) {
		return articlemapper.incrHits(aid)>0;
	}
	@Override
	public List<Article> getNewsArticle() {
		//定义返回结果
		List<Article> newsarticle=null;
		Boolean flag = redisTemplate.hasKey("newsarticle");
		if(flag) {
			newsarticle=redisTemplate.opsForList().range("newsarticle", 0, -1);
		}else {
			newsarticle=articlemapper.getNewsArticle();
			redisTemplate.opsForList().leftPushAll("newsarticle", newsarticle);
		}
		return newsarticle;
	}
	@Override
	public boolean delnew(int nid) {
		try {
			redisTemplate.delete("newsarticle");
			articlemapper.delnew(nid);
			List<Article> newsarticle = articlemapper.getNewsArticle();
			redisTemplate.opsForList().leftPushAll("newsarticle", newsarticle);
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}

}
