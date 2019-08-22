package com.zhangzhenjiang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.Slide;
import com.zhangzhenjiang.cms.mapper.SlideMapper;
import com.zhangzhenjiang.cms.service.SlideService;
@Service
public class SlideServiceImp implements SlideService{
	@Resource
	private SlideMapper slideMapper;
	@Override
	public List<Slide> getSlideList(String title) {
		return slideMapper.getSlideList(title);
	}
	@Override
	public boolean insertSlide(Slide slide) {
		return slideMapper.insertSlide(slide)>0;
	}
	@Override
	public boolean delSlideByIds(String ids) {
		return slideMapper.delSlideByIds(ids)>0;
	}

}
