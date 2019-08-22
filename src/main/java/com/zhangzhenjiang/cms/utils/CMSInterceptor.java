package com.zhangzhenjiang.cms.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhangzhenjiang.cms.bean.User;

public class CMSInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取请求的uri
		String uri = request.getRequestURI();
		//获取当前的session
		User user = (User) request.getSession().getAttribute("user");
		// 如果用户已经登录.并且请求的是个人后台,则用户不是管理员)
		if (user != null && uri.contains("my") && !user.getUsername().equals("admin")) {
			return true;// 不拦截
		} else if (user != null && uri.contains("admin") && user.getUsername().equals("admin")) {
			// 管理员登录
			return true;
		}else if(!uri.contains("my") && !uri.contains("admin")) {
			// 如果用户访问的URI 不包含MY 或admin ,就认为是访问的是前端页面.
			return true;
		}else {
			request.setAttribute("error", "请以合适的账户登录....");
			request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
			return false;// 非法用户
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
