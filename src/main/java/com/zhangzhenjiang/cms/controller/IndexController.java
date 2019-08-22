package com.zhangzhenjiang.cms.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.bean.Comment;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.ChannelService;
import com.zhangzhenjiang.cms.service.CommentService;
import com.zhangzhenjiang.cms.service.SubjectService;
import com.zhangzhenjiang.cms.utils.PageUtil;


@Controller
public class IndexController {
	@Resource
	private ChannelService channelservice;
	@Resource
	private SubjectService subjectservice;
	@Resource
	private ArticleService articleservice;
	@Resource
	private CommentService commentservice;
	@RequestMapping(value= {"/","index",""})
	public String toIndex(Model model,@RequestParam(defaultValue="")String titleorcontent,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="2")int pageSize) {
		//查询出所有栏目
		List<Channel> list = channelservice.getListChannel(null);
		model.addAttribute("list", list);
		//查询热点文章
		PageHelper.startPage(page, pageSize);
		List<Map> hotList = articleservice.selectHotList(titleorcontent);
		//热门文章分页
		PageInfo info=new PageInfo(hotList);
		String pageInfo = PageUtil.page(page, info.getPages(), "/index?titleorcontent="+titleorcontent, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("titleorcontent", titleorcontent);
		model.addAttribute("hotList", hotList);
		//查询专题
		List<Map> subjectList = subjectservice.selectSubjectList();
		model.addAttribute("subjectList", subjectList);
		//查询最新文章
		List<Article> newsarticle = articleservice.getNewsArticle();
		model.addAttribute("newsarticle", newsarticle);
		return "/index/index";
	}
	//返回某个栏目下所有的分类
	@RequestMapping(value= {"getCategoryList"})
	public String toCategoryList(Integer cid,Model model) {
		CategoryVo vo=new CategoryVo();
		vo.setCid(cid);
		//查询所有的分类
		List<Category> list = channelservice.getCategoryList(vo);
		model.addAttribute("list", list);
		model.addAttribute("cid", cid);
		//查询该栏目下的所有文章
		List<Map> list2 = articleservice.selectListByCId(cid);
		model.addAttribute("articleList", list2);
		return  "/index/category";
	}
	
	//查看单个文章明细
	@RequestMapping("getArticleDetail")
	public String getDetail(Integer id,Model model,HttpServletRequest request) {
		Map map= articleservice.get(id);
		model.addAttribute("article", map);
		List<Comment> commentlist = commentservice.getCommentListByAid(id,null);
		model.addAttribute("commentlist", commentlist);
		//浏
		Article article = articleservice.selectArticleByAid(id);
		List<Article> list = (List<Article>) request.getSession().getAttribute("historys");
		if(list!=null) {
			//判断是否已经有该浏览的文章
			if (list.contains(article)) {
				list.remove(article);
			}
		}else {
			list = new LinkedList<>();
		}
		//判断操作
		if(list.size()>=3) {
			list.remove(2);
		}
		list.add(0, article);
		request.getSession().setAttribute("historys", list);
		return "/index/article/detail";
	}
	//查询专题的分类
	@RequestMapping("/selectSubjectList")
	public String selectSubjectList(Model model) {
	 	List<Map> list = subjectservice.selectSubjectList();
	 	model.addAttribute("subList", list);
	 	return "/index/subject";
	}
	//通过专题id查询某个专题下的所有文章
	@RequestMapping("/selectArticleListBySid")
	public String selectArticleListBySid(Model model,Integer sid) {
	 	List<Map> list = subjectservice.selectArtilceListBySid(sid);
	 	model.addAttribute("articleList", list);
	 	return "/index/subject";
	}
	//删除最新文章以及更新redis中最新文章
	@RequestMapping("/delnew")
	@ResponseBody
	public boolean delnew(int nid) {
		return articleservice.delnew(nid);
	}
}
