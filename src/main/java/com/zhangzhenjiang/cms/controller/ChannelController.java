package com.zhangzhenjiang.cms.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {
	@Resource
	private ChannelService channelservice;
	
	@RequestMapping("listChannle")
	@ResponseBody
	public Object getChannelList() {
		List<Channel> list = channelservice.getListChannel(null);
		return list;
	}
	
	@RequestMapping("listCategory")
	@ResponseBody
	public Object getCategoryList(@RequestParam()int cid) {
		CategoryVo vo=new CategoryVo();
		vo.setCid(cid);
		List<Category> list = channelservice.getCategoryList(vo);
		return list;
	}
}
