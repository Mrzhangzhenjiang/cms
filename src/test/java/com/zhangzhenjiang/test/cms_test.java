package com.zhangzhenjiang.test;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.ChannelService;
import com.zhangzhenjiang.cms.utils.CmsFileUtils;
@ContextConfiguration(locations="classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class cms_test {
	@Resource
	private ArticleService articleService;
	@Resource
	private ChannelService channelService;
	@Test
	public void getChannelList() {
		List<Channel> list = channelService.getListChannel(null);
		for (Channel channel : list) {
			System.out.println(channel);
		}
	}
	@Test
	public void getCategoryList() {
		List<Category> list = channelService.getCategoryList(null);
		for (Category category : list) {
			System.out.println(category);
		}
	}
}
