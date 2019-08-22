package com.zhangzhenjiang.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.bean.ChannelVo;
import com.zhangzhenjiang.cms.mapper.ChannelMapper;
import com.zhangzhenjiang.cms.service.ChannelService;
@Service
public class ChannelServiceImp implements ChannelService{
	@Resource
	private ChannelMapper channelmapper;
	@Override
	public List<Channel> getListChannel(ChannelVo vo) {
		return channelmapper.getListChannel(vo);
	}
	@Override
	public List<Category> getCategoryList(CategoryVo vo) {
		return channelmapper.getCategoryList(vo);
	}
}
