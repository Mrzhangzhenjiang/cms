package com.zhangzhenjiang.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.SubjectService;
import com.zhangzhenjiang.cms.utils.PageUtil;
@RequestMapping("article")
@Controller
public class ArticleController {
	@Resource
	private ArticleService articleservice;
	@Resource
	private SubjectService subjectservice;
	@RequestMapping("toPublish")
	public String toPublish(Model model) {
		//把所有专题查询
		List<Map> list = subjectservice.selectSubjectList();
		model.addAttribute("subList", list);
		return "/my/article/publish";
	}
	
	@RequestMapping("publish")
	@ResponseBody
	public boolean publish(MultipartFile file,Article article,HttpSession session,Integer[] sid) throws IllegalStateException, IOException {
		if (null != file && !file.isEmpty()) {
			String path = "d:/tools/photo/";// 文件上传的目标路径
			// 获取上传文件的原始名称 1.jpg
			String filename = file.getOriginalFilename();
			// 防止文件重名.
			String name = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
			File file2 = new File(path + name);
			// 把文件写入硬盘
			file.transferTo(file2);
			// 数据库存储文件的名称
			article.setPicture(name);
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		return articleservice.insert(article,sid);
	}
	
	@RequestMapping("listByUserId")
	public String publish(Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="4")int pageSize,HttpSession session) {
		PageHelper.startPage(page, pageSize);
		//从session获取当前登录的人ID
		User getUserId = (User) session.getAttribute("user");
		List<Map> list = articleservice.listByUserId(getUserId.getId());
		PageInfo info=new PageInfo(list);
		String pageInfo = PageUtil.page(page, info.getPages(), "/article/listByUserId", pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		return "/my/article/list";
	}
	
	//查看单个文章明细
	@RequestMapping("getDetail")
	public String getDetail(Integer id,Model model) {
		Map map= articleservice.get(id);
		model.addAttribute("article", map);
		return "/my/article/detail";
	}
	
	//文章审核列表
	@RequestMapping("checkList")
	public String checkList(Model model,@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="") String status) {
		PageHelper.startPage(page, 10);
		List<Map> list = articleservice.selectList(status);	
		PageInfo<Map> info = new PageInfo<>(list);
		String pageInfo = PageUtil.page(page, info.getPages(), "/article/checkList?status="+status, 10);
		model.addAttribute("status", status);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/article/checkList";
	}
	
	// 管理员查看单个文章明细
	@RequestMapping("get")
	public String get(Integer id, Model model) {
		Map map = articleservice.get(id);
		model.addAttribute("article", map);
		return "/admin/article/detail";
	}
	
	//审核文章
	@ResponseBody
	@RequestMapping("pass")
	public boolean pass(Article article) {
		return articleservice.update(article)>0;
	}
	
	//在首页查看文章明细
	@RequestMapping("/index/getDetail")
	public String getDetail2(Integer id,Model model) {
		Map map= articleservice.get(id);
		model.addAttribute("article", map);
		return "/index/article/detail";
	}
	
	//返回某个分类下下所有的文章
	@RequestMapping(value= {"selectListByCatId"})
	public String toCategoryList(Integer catid,Model model) {
		 //查询所有的分类
		List<Map> list = articleservice.selectListByCatId(catid);
		model.addAttribute("articleList", list);
		return  "/index/article/articleBycategory";
	}
	//查询单个文章明细用于回显
	@RequestMapping("toUpdate")
	public String toUpdate(Integer id, Model model) {
		Map map = articleservice.get(id);
		//查询专题列表用于回显
		List<Map> list = subjectservice.selectSubjectList();
		model.addAttribute("article", map);
		model.addAttribute("subList", list);
		return "/my/article/update";
	}
	//通过文章id查询该文章所在的专题用于选中修改页面的下拉框
	@RequestMapping("selectSubjectIdByAid")
	@ResponseBody
	public List<Map> selectSubjectIdByAid(Integer aid){
		List<Map> list = subjectservice.selectSubjectIdByAid(aid);
		return list;
	}
	//修改文章
	@ResponseBody
	@RequestMapping("update")
	public boolean update(MultipartFile file, Article article, HttpSession session, Integer[] sid)
					throws IllegalStateException, IOException {
		// 文件上传
		if (null != file && !file.isEmpty()) {
			String path = "d:/pic/";// 文件上传的目标路径
			// 获取上传文件的原始名称 1.jpg
			String filename = file.getOriginalFilename();
			// 防止文件重名.
			String name = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
			File file2 = new File(path + name);
			// 把文件写入硬盘
			file.transferTo(file2);
			// 数据库存储文件的名称
			article.setPicture(name);
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		return articleservice.update(article)>0;
	}
	@RequestMapping("/incrhits")
	public void incrHits(@RequestParam(defaultValue="")Integer aid) {
		articleservice.incrHits(aid);
	}
}
