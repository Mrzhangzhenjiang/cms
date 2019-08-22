package com.zhangzhenjiang.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.User;



/**
 * 
 * @ClassName: UserMapper 
 * @Description: 用户的信息的维护 
 * @author: charles
 * @date: 2019年6月18日 下午1:57:52
 */
public interface UserMapper {
	
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 列表
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
	 * @Description: 注册
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(User user);
	
	/**
	 *  
	 * @Title: update 
	 * @Description: 更新
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);

}
