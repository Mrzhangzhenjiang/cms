package com.zhangzhenjiang.test;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.ArticleService;
import com.zhangzhenjiang.cms.service.UserService;

@ContextConfiguration(locations="classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
	@Resource
	private UserService userService;
	@Resource
	private ArticleService articleService;
	@Resource
	private RedisTemplate<String,User> redisTemplate;
	@Test
	public void commonFirends() {
		//用户1有2.3.4.5的好友  用户5有2.3的好友   找出共同好友
		User user1 = userService.getByUsername("user1");
		User user2 = userService.getByUsername("user2");
		User user3 = userService.getByUsername("user3");
		User user4 = userService.getByUsername("user4");
		User user5 = userService.getByUsername("user5");
		redisTemplate.opsForSet().add(user1.getUsername(), user2,user3,user4,user5);
		redisTemplate.opsForSet().add(user5.getUsername(), user2,user3);
		Set<User> commonFirends = redisTemplate.opsForSet().intersect(user1.getUsername(), user5.getUsername());
		Iterator<User> iterator = commonFirends.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
