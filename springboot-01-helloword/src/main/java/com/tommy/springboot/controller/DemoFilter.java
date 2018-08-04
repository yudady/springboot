package com.tommy.springboot.controller;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
public class DemoFilter implements Filter{
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("==>DemoFilter启动");
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("==>DemoFilter拦截请求");
		chain.doFilter(request, response);
		
	}
 
	@Override
	public void destroy() {
		
	}
}