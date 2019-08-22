package com.zhangzhenjiang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengzhenjiang.common.utils.StringUtil;
/**
 * 
 * @ClassName: AdminController 
 * @Description: admin 后台控制
 * @author: charles
 * @date: 2019年6月18日 下午3:17:14
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	//后台首页
	@RequestMapping(value= {"index","/",""})
	public String toIndex() {
		return "/admin/index";
		
	}
}
