package com.zhangzhenjiang.cms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangzhenjiang.cms.bean.Subject;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.SubjectService;
@RequestMapping("subject")
/**
 * 
 * <br>Title:TODO 类标题
 * <br>Description:TODO 类功能描述
 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
 * <br>Date:2019年7月3日
 */
@Controller
public class SubjectController {
	@Resource
	private SubjectService subjectservice;
	@Resource
	private ArticleService articleservice;
	//查询所有专题
	@RequestMapping("list")
	public String selectSubjectList(Model model) {
		 List<Map> list = subjectservice.selectSubjectList();
		 model.addAttribute("subList", list);
		 return "/admin/subject/list";
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param model
	 * @param cid
	 * @param catId
	 * @param sid
	 * @return
	 */
	@RequestMapping("toAddArticle")
	public String toAddArticle(Model model,@RequestParam(defaultValue="")String cid,@RequestParam(defaultValue="" )String catId,String sid) {
		List<Map> list = articleservice.selectListByCIdCatId(cid, catId);
		//回显专题信息
		Subject subject = subjectservice.selectSubjectBySid(Integer.valueOf(sid));
		model.addAttribute("subject", subject);
		model.addAttribute("list", list);
		model.addAttribute("cid", cid);
		model.addAttribute("catId", catId);
		//专题ID
		model.addAttribute("sid", sid);
		return "/admin/subject/articleList";
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param sid
	 * @return
	 */
	//查询出已经属于该专题的文章,用来回显
	@ResponseBody
	@RequestMapping("selectArtilceListBySid")
	public List<Map> selectArtilceListBySid(Integer sid) {
		return subjectservice.selectArtilceListBySid(sid);
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param ids
	 * @param sid
	 * @return
	 */
	//向专题中添加文章
	@ResponseBody
	@RequestMapping("add")
	public boolean add(@RequestParam("ids[]")Integer ids[],Integer sid) {
	  return subjectservice.insert2(ids, sid);
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param model
	 * @param sid
	 * @return
	 */
	//向专题表中添加专题
	@RequestMapping("toAddSubject")
	public String toAddSubject(Model model,Integer sid) {
		return "/admin/subject/addsubject";
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param subject
	 * @return
	 */
	//创建专题
	@RequestMapping("addSubject")
	@ResponseBody
	public boolean addSubject(Subject subject) {
		return subjectservice.addSubject(subject)>0;
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param sid
	 * @param model
	 * @return
	 */
	//跳转修改专题界面
	@RequestMapping("toUpdateSubject")
	public String toUpdateSubject(Integer sid,Model model) {
		//用于回显
		Subject subject = subjectservice.selectSubjectBySid(sid);
		model.addAttribute("subject", subject);
		return "/admin/subject/updatesubject";
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param subject
	 * @return
	 */
	//修改
	@RequestMapping("updateSubject")
	@ResponseBody
	public boolean updateSubject(Subject subject) {
		return subjectservice.updateSubject(subject)>0;
	}
	/**
	 * 
	 * <br>Description:TODO 方法功能描述
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月3日
	 * @param aid
	 * @param sid
	 * @return
	 */
	//文本框添加文章
	@RequestMapping("addArticle")
	@ResponseBody
	public boolean addArticle(Integer aid,Integer sid) {
		return subjectservice.addArticle(aid, sid);
	}
}
