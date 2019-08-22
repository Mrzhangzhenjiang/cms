package com.zhangzhenjiang.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhenjiang.cms.bean.Slide;
import com.zhangzhenjiang.cms.service.SlideService;
import com.zhangzhenjiang.cms.utils.PageUtil;

@Controller
public class SlideController {
	@Resource
	private SlideService slideService;
	@RequestMapping("/getslidelist")
	public String getSlideList(@RequestParam(defaultValue="")String title,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="5")int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Slide> slidelist = slideService.getSlideList(title);
		PageInfo info=new PageInfo(slidelist);
		String pageInfo = PageUtil.page(page, info.getPages(), "/getslidelist?title="+title, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("slidelist", slidelist);
		return "/admin/slide/list";
	}
	@RequestMapping("/insertslide")
	@ResponseBody
	public boolean insertSlide(MultipartFile file,Slide slide) throws IllegalStateException, IOException {
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
			slide.setPicture(name);
		}
		return slideService.insertSlide(slide);
	}
}
