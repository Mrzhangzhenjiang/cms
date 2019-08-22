package com.zhangzhenjiang.cms.kafka;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("spring-consumer.xml");
	}
}
