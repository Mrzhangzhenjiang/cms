package com.zhangzhenjiang.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.bean.Category;
import com.zhangzhenjiang.cms.bean.CategoryVo;
import com.zhangzhenjiang.cms.bean.Channel;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.ChannelService;
import com.zhangzhenjiang.cms.utils.CmsFileUtils;
import com.zhangzhenjiang.cms.utils.IKWord;

@ContextConfiguration(locations= {"classpath:spring-beans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MysqlImportAndKafkaTest {
	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	@Resource
	private ArticleService articleService;
	@Resource
	private ChannelService channelService;
	@Test
	public void ikTest() throws IOException {
		int count=0;
		Random random=new Random();
		List<Channel> listChannel = channelService.getListChannel(null);
		int userids[]= {1,14,15};
		File disFile=new File("D:\\getarticle");
		File[] listFiles = disFile.listFiles();
		for (File file : listFiles) {
			count++;
			// 获取文件名称
			String fileName = file.getName();
			// 截取文章的标题
			String title = fileName.substring(0, fileName.lastIndexOf("."));
			// 读取的文章内容
			String content = CmsFileUtils.readFile(file);
			// 统计content内容中出现的词，词是通过ik分词器处理的词，词可以自己扩展.
			List<Entry<String, Integer>> list = IKWord.order(IKWord.count(new HashMap<String, Integer>(), content));
			String keyword=null;
			if(list!=null && list.size()>3) {
				 keyword = list.get(0).getKey()+","+list.get(1).getKey()+","+list.get(2).getKey();
			}
			Article article=new Article();
			article.setTitle(title);
			article.setContent(content);
			article.setPicture(null);
			//随机获取栏目id
			Integer channelid = listChannel.get(random.nextInt(listChannel.size())).getId();
			article.setChannelId(channelid);
			CategoryVo categoryvo=new CategoryVo();
			categoryvo.setCid(channelid);
			List<Category> listcategory = channelService.getCategoryList(categoryvo);
			//随机获取分类id
			if(listcategory!=null&&listcategory.size()>0) {
				Integer categoryid = listcategory.get(random.nextInt(listcategory.size())).getId();
				article.setCategoryId(categoryid);
			}
			article.setUserId(userids[random.nextInt(userids.length)]);
			article.setHits(random.nextInt(10000));
			article.setHot(random.nextInt(2));
			article.setStatus(0);
			article.setDeleted(0);
			//不在此保存到数据库
			//articleService.insert(article, null);
			//把文章对象转换成json数据
			String json = objectMapper.writeValueAsString(article);
			kafkaTemplate.sendDefault(json);
			if(count==100) {
				break;
			}
		}
	}
}
