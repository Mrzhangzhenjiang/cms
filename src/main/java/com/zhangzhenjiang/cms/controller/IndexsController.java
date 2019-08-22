/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: chengongjun_cms
 * @Package: com.chengongjun.chengongjun_cms.controller 
 * @Description: TODO
 * @author: chj   
 * @date: 2019年7月26日 上午9:57:58 
 * @version: V1.0   
 */
package com.zhangzhenjiang.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.bean.ArticleVo;
import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.ChannelService;
import com.zhangzhenjiang.cms.utils.ESArticleUtils;
import com.zhangzhenjiang.cms.utils.ESUtils;
import com.github.pagehelper.PageInfo;

@Controller
public class IndexsController {

	@Resource
	private ChannelService channelService;

	@Resource
	private ArticleService articleService;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;


	@RequestMapping(value = {"/indexs" })
	public String index(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize, ArticleVo vo,
			@RequestParam(defaultValue = "-1") Integer channelId,@RequestParam(defaultValue="-1")Integer categoryId) {
		initEs(request, pageNum, pageSize, vo, channelId, categoryId);
		return "/index/indexs";
	}
	private void initEs(HttpServletRequest request, Integer pageNum, Integer pageSize, ArticleVo vo,
			Integer channelId, Integer categoryId) {
		// 查询出所有的栏目信息
		List<Channel> channels = channelService.getListChannel(null);
		AggregatedPage<?> pageInfo=null;
		if(channelId==-1) {
			//查询出所有的热门文章
			pageInfo =ESArticleUtils.selectObjects(elasticsearchTemplate, Article.class, pageNum, pageSize, new String[] {"title","content"}, vo.getValue(),channelId,categoryId);
		}else {
			CategoryVo categoryvo=new CategoryVo();
			categoryvo.setCid(channelId);
			List<Category> categories = channelService.getCategoryList(categoryvo);
			request.setAttribute("categories", categories);
			//设置文章的栏目
			vo.setChannelId(channelId);
			//判断是否有分类
			if(categoryId!=-1) {
			    vo.setCategoryId(categoryId);
			}
			//查询出所有的当前栏目的中的文章
			pageInfo =ESArticleUtils.selectObjects(elasticsearchTemplate, Article.class, pageNum, pageSize, new String[] {"title","content"}, vo.getValue(),channelId,categoryId);
		}
		// 栏目存储到request中
		request.setAttribute("links", channels);
		request.setAttribute("channelId", channelId);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("pageInfo", pageInfo);
	}
}

