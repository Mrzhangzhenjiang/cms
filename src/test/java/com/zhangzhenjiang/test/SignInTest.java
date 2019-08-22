package com.zhangzhenjiang.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.UserService;

@ContextConfiguration(locations="classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//用户签到业务
public class SignInTest {
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	@Resource
	private UserService userService;
	//用户签到
	public boolean doSign(int uid, LocalDate date) {
	    int offset = date.getDayOfMonth() - 1;
	    return redisTemplate.opsForValue().setBit(buildSignKey(uid, date), offset, true);
	}
	//检查用户某天是否签到
	public boolean checkSign(int uid, LocalDate date) {
        int offset = date.getDayOfMonth() - 1;
        return redisTemplate.opsForValue().getBit(buildSignKey(uid, date), offset);
    }
	//获取用户的签到次数
	/*public long getSignCount(int uid, LocalDate date) {
		return redisTemplate.opsForValue()
        return jedis.bitcount(buildSignKey(uid, date));
    }*/
	//格式化时间
	private static String formatDate(LocalDate date) {
        return formatDate(date, "yyyyMM");
    }
	//格式化时间
	private static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
	//产生用户签到的key
	private static String buildSignKey(int uid,LocalDate date) {
		return String.format("u:sign:%d:%s", uid, formatDate(date));
	}
	@Test
	public void test() {
		User userOne = userService.getByUsername("user1");
		LocalDate today = LocalDate.now();
		LocalDate yesterday = LocalDate.of(today.getYear(), today.getMonth(), today.getDayOfMonth()-1);
		LocalDate tomorrow = LocalDate.of(today.getYear(), today.getMonth(), today.getDayOfMonth()+1);
		//用户签到
		boolean signed  = doSign(userOne.getId(),today);
		if (signed) {
            System.out.println("您已签到：" + formatDate(today, "yyyy-MM-dd"));
        } else {
            System.out.println("签到完成：" + formatDate(today, "yyyy-MM-dd"));
        }
		//检查用户是否签到
		boolean signOrNot = checkSign(userOne.getId(),tomorrow);
        if (signOrNot) {
            System.out.println("您已签到：" + formatDate(tomorrow, "yyyy-MM-dd"));
        } else {
            System.out.println("尚未签到：" + formatDate(tomorrow, "yyyy-MM-dd"));
        }
	}
}
