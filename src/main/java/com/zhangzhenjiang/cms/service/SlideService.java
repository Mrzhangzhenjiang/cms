package com.zhangzhenjiang.cms.service;

import java.util.List;

import com.zhangzhenjiang.cms.bean.Slide;

public interface SlideService {
	List<Slide> getSlideList(String title);
	boolean insertSlide(Slide slide);
	boolean delSlideByIds(String ids);
}
