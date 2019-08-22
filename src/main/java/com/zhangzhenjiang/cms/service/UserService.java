package com.zhangzhenjiang.cms.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.User;

public interface UserService {
	/**
	 * 
	 * @Title: list 
	 * @Description: 用户列表
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	List<Map> list(@Param("username") String username);

	/**
	 * 
	 * @Title: getByUsername 
	 * @Description: 根据用户名查询用户
	 * @param username
	 * @return
	 * @return: User
	 */
	User getByUsername(@Param("username")String username);
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 添加所用   也就是注册
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(User user);
	
	/**
	 *  
	 * @Title: update 
	 * @Description: 更新  修改个人信息时所用
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
}
