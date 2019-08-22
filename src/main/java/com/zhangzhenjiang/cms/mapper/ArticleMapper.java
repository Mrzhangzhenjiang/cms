package com.zhangzhenjiang.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Article;

public interface ArticleMapper {
	boolean insert(Article article);
	List<Map> listByUserId(Integer userId);
	Map get(Integer id);
	List<Map> selectList(@Param("status")String status);
	int update(Article article);
	List<Map> selectHotList(@Param("titleorcontent")String titleorcontent);
	List<Map> selectListByCId(Integer cid);
	List<Map> selectListByCatId(Integer catid);
	List<Map> selectListByCIdCatId(@Param("cid")String cid ,@Param("catId")String catId);
	Article selectArticleByAid(Integer aid);
	int incrHits(@Param("aid")Integer aid);
	List<Article> getNewsArticle();
	int delnew(@Param("nid")int nid);
}
