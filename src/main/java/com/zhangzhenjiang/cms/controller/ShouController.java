package com.zhangzhenjiang.cms.controller;

import java.util.List;
import java.util.Map;

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
import com.zhangzhenjiang.cms.bean.Shou;
import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.ShouService;
import com.zhangzhenjiang.cms.utils.PageUtil;

@Controller
public class ShouController {
	@Resource
	private ShouService shouService;
	@RequestMapping("getshoulist")
	public String getShouList(HttpSession session,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="4")int pageSize) {
		User user = (User) session.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		List<Map> shoulist = shouService.getShouListByUid(user.getId());
		PageInfo info=new PageInfo(shoulist);
		String pageInfo = PageUtil.page(page, info.getPages(), "/getshoulist", pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("shoulist", shoulist);
		return "/my/user/shou";
	}
	@RequestMapping("isshou")
	@ResponseBody
	public boolean isShou(Integer uid,Integer aid) {
		return shouService.isShou(uid, aid)!=null;
	}
	@RequestMapping("insertshou")
	@ResponseBody
	public boolean insertShou(Integer uid,Integer aid) {
		return shouService.insertShou(uid, aid);
	}
	@RequestMapping("delshou")
	@ResponseBody
	public boolean delShou(Integer uid,Integer aid) {
		return shouService.delShou(uid, aid);
	}
}
