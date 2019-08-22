package com.zhangzhenjiang.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangzhenjiang.cms.bean.User;
import com.zhangzhenjiang.cms.service.UserService;
import com.zhangzhenjiang.cms.utils.Md5Util;
import com.zhangzhenjiang.cms.utils.PageUtil;
import com.zhengzhenjiang.common.utils.StringUtil;
@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	private UserService userService;
	@RequestMapping("list")
	public String list(Model model,@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="3")Integer pageSize,@RequestParam(defaultValue="")String username) {
		PageHelper.startPage(page, pageSize);
		List<Map> list = userService.list(username);
		PageInfo<Map> info=new PageInfo<>(list);
		String pageInfo = PageUtil.page(page, info.getPages(), "/user/list?username="+username, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("username", username);
		model.addAttribute("list", list);
		return "admin/user/list";
	}
	//修改权限
	@RequestMapping("update")
	@ResponseBody
	public boolean update(User user) {
		return userService.update(user)>0;	
	}
	//去注册
	@RequestMapping("toReg")
	public String toReg() {
		return "/passport/reg";
	}
	//去登录
	@RequestMapping("toLogin")
	public String toLoing() {
		return "/passport/login";
	}
	//验证用户名唯一
	@RequestMapping("checkUser")
	@ResponseBody
	public boolean checkUser(String username) {
		User user = userService.getByUsername(username);
		return user != null;
	}
	//注册
    @ResponseBody
	@RequestMapping("reg")
	public boolean reg(User user) {
    	//对密码进行加密
    	user.setPassword(Md5Util.md5Encoding(user.getPassword()));
		return userService.insert(user)>0;
	}
    //登陆
    @ResponseBody
	@RequestMapping("login")
	public Map<String,Object> login(User user,String code,HttpSession session) {
    	Map<String,Object> map=new HashMap<String, Object>();
    	String sessioncode = (String) session.getAttribute("code");
    	if(code.equalsIgnoreCase(sessioncode)) {
    		//根据用户名查询用户
        	User user2 = userService.getByUsername(user.getUsername());
        	if(null!=user2) {
        		//把当前登录的用户密码加密后和实际密码比较
        	   	if(Md5Util.md5Encoding(user.getPassword()).equals(user2.getPassword())) {
        	   		//把用户存入session
        	   		user2.setPassword(user.getPassword());
        	   		session.setAttribute("user", user2);
        	   		map.put("code", 10001);
        	   		map.put("msg", "用户名与密码以及验证码正确，登陆成功");
        	   	}else {
        	   		map.put("code", 10002);
        	   		map.put("msg", "用户名正确，密码不正确，登陆失败");
        	   	}
        	}else {
        		map.put("code", 10003);
        		map.put("msg", "用户名不正确，登陆失败");
        	}
    	}else {
    		map.put("code", 10004);
    		map.put("msg", "验证码不正确");
    	}
    	return map;
	}
    //注销
   	@RequestMapping("logout")
   	public String logout(HttpSession session) {
   		session.invalidate();
   		return "/passport/login";	
   	}
   	//个人设置界面
   	@RequestMapping("toUpdateUser")
   	public String toUpdateUser() {
   		return "/my/user/update";
   	}
   	//验证手机号是否合法
   	@ResponseBody
	@RequestMapping("validatePhone")
	public boolean validatePhone(String src) {
		 return StringUtil.isMobile(src);
	}
	@ResponseBody
	@RequestMapping("validateEmail")
	public boolean validateEmail(String src) {
		 return StringUtil.isEmail(src);	
	}
	//验证昵称是否为空
	@ResponseBody
	@RequestMapping("validateNickname")
	public boolean validateNickname(String src) {
		 return StringUtil.hasText(src);	
	}
	//验证密码是否为空
	@ResponseBody
	@RequestMapping("validatePassword")
	public boolean validatePassword(String src) {
		 return StringUtil.hasText(src);	
	}
	//实现修改
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user, Model model,HttpSession session) {
		user.setPassword(Md5Util.md5Encoding(user.getPassword()));
		//如果更新成功.则更新用户的session 信息
		 if(userService.update(user)>0) {
			 session.removeAttribute("user");
			 user.setPassword(user.getPassword());
			 session.setAttribute("user", user);
             return true;
		 }else {
			 return false;
		 }
	}
}
