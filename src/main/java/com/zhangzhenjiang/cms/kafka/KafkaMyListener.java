package com.zhangzhenjiang.cms.kafka;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.utils.ESUtils;

public class KafkaMyListener implements MessageListener<String, String>{
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper();

	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		//获取json内容
		String value=data.value();
		try {
			Article article = objectMapper.readValue(value, Article.class);
			articleService.insert(article, null);
			if(article.getHot()==1) {
				ESUtils.saveObject(elasticsearchTemplate, article.getId()+"", article);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
}
