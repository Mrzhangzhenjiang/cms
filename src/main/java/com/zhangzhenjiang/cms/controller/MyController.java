package com.zhangzhenjiang.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("my")
@Controller
public class MyController {
	@RequestMapping({"index","/",""})
	public String toIndex() {
		return "/my/index";
	}
}
