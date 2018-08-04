package com.yuxia.blog.Interceptor;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yuxia.blog.entity.User;
/**
 * 拦截未登录用户
 * @author YuXia
 * @date 2018年7月18日
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger =LogManager.getLogger(UserInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		logger.debug("UserInterceptor preHandle");
		//获取seesion
		HttpSession session=request.getSession();
		//获取用户
		User user =(User) session.getAttribute("user");
		if(null==user){
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		return true;
	}
}
