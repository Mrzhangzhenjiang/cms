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
import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.HistoryService;
import com.zhangzhenjiang.cms.utils.PageUtil;

@Controller
public class HistoryController {
	@Resource
	private HistoryService historyService;
	@RequestMapping("/gethistorylist")
	public String getHistoryList(HttpSession session,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="4")int pageSize) {
		User user = (User) session.getAttribute("user");
		PageHelper.startPage(page, pageSize);
		List<Map> historylist = historyService.getHistoryListByUid(user.getId());
		PageInfo info=new PageInfo(historylist);
		String pageInfo = PageUtil.page(page, info.getPages(), "/gethistorylist", pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("historylist", historylist);
		return "/my/user/history";
	}
	@RequestMapping("/ishistory")
	@ResponseBody
	public boolean isHistory(Integer uid,Integer aid) {
		return historyService.isHistory(uid, aid)!=null;
	}
	@RequestMapping("/inserthistory")
	public void insertHistory(Integer uid,Integer aid) {
		historyService.insertHistory(uid, aid);
	}
	@RequestMapping("/updatehtime")
	public void updateHtime(Integer uid,Integer aid) {
		historyService.updateHtime(uid, aid);
	}
}
