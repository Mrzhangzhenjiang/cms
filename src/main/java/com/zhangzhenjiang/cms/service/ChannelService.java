package com.zhangzhenjiang.cms.service;

import java.util.List;
import java.util.Map;

import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.bean.ChannelVo;

public interface ChannelService {
	/**
	 * 
	 * <br>Description:TODO 查询栏目列表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @return
	 */
	List<Channel> getListChannel(ChannelVo vo);
	
	List<Category> getCategoryList(CategoryVo vo);
}
