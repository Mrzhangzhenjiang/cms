package com.zhangzhenjiang.cms.redis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

public class RedisCache implements Cache{
	//获取打印日志
	private static final Logger LOG=Logger.getLogger(RedisCache.class);
	//注入redis模板
	private RedisTemplate<String, Serializable> redisTemplate=(RedisTemplate<String, Serializable>)SpringContextHolder.getBean("redisTemplate");
	//读写锁
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	//定义缓存id
	private String id;
	//序列化
	private JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
	
	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		LOG.info("Redis Cache id " + id);
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	//如果value不为空，序列化以后存入到redis缓存  时间设置为两天
	@Override
	public void putObject(Object key, Object value) {
		System.out.println(redisTemplate);
		if (value != null) {
			redisTemplate.opsForValue().set(key.toString(), jdkSerializer.serialize(value), 2, TimeUnit.DAYS);
		}
	}

	@Override
	public Object getObject(Object key) {
		try {
			if (key != null) {
				Object obj = redisTemplate.opsForValue().get(key.toString());
				return jdkSerializer.deserialize((byte[]) obj);
			}
		} catch (Exception e) {
			LOG.error("redis ");
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		try {
			if (key != null) {
				redisTemplate.expire(key.toString(), 1, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			LOG.error("redis ");
		}
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getSize() {
		Long size = redisTemplate.execute(new RedisCallback<Long>(){  
            @Override  
            public Long doInRedis(RedisConnection connection)  
                    throws DataAccessException {
                return connection.dbSize();
            }  
        });  
        return size.intValue();  
	}
	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

}
