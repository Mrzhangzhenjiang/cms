package com.zhangzhenjiang.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Slide;

public interface SlideMapper {
	List<Slide> getSlideList(@Param("title")String title);
	int insertSlide(Slide slide);
	int delSlideByIds(@Param("ids")String ids);
}
