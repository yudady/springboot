package com.tommy.springboot.golbal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class SysUserLoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.debug("SysUserLoginInterceptor : preHandle()");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		Object o, ModelAndView modelAndView) throws Exception {
		logger.debug("SysUserLoginInterceptor : postHandle()");

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		logger.debug("SysUserLoginInterceptor : afterCompletion()");
	}

}