package com.zhangzhenjiang.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.mapper.UserMapper;
import com.zhangzhenjiang.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public List<Map> list(String username) {
		return userMapper.list(username);
	}

	@Override
	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

}
