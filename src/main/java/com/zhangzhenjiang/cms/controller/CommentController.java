package com.zhangzhenjiang.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhenjiang.cms.bean.Comment;
import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.CommentService;
import com.zhangzhenjiang.cms.utils.PageUtil;

@Controller
public class CommentController {
	@Resource
	private CommentService commentService;
	@RequestMapping("/addcomment")
	@ResponseBody
	public boolean addComment(int aid,String ccomment,HttpSession session) {
		Comment comment=new Comment();
		comment.setCcontent(ccomment);
		User user = (User) session.getAttribute("user");
		comment.setAid(aid);
		comment.setUid(user.getId());
		return commentService.addComment(comment);
	}
	@RequestMapping("/getcommentlist")
	public String getCommentByUid(HttpSession session,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="4")int pageSize){
		User user = (User) session.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		List<Comment> commentlist = commentService.getCommentListByAid(null, user.getId());
		PageInfo info=new PageInfo(commentlist);
		String pageInfo = PageUtil.page(page, info.getPages(), "/getcommentlist", pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("commentlist", commentlist);
		return "/my/user/comment";
	}
	@RequestMapping("/delcomment")
	@ResponseBody
	public boolean delComment(@RequestParam(defaultValue="")Integer cid) {
		return commentService.delComment(cid);
	}
}
