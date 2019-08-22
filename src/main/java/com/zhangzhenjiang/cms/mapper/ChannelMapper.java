package com.zhangzhenjiang.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.bean.ChannelVo;

public interface ChannelMapper {
	List<Channel> getListChannel(ChannelVo vo);
	List<Category> getCategoryList(CategoryVo vo);
}
