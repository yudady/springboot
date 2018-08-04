package com.tommy.springboot.config;

import com.tommy.springboot.golbal.interceptor.OneInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tommy.springboot.golbal.interceptor.SysUserLoginInterceptor;

@Configuration
public class ConfigWebMvcInterceptors implements WebMvcConfigurer {

	/**
	 * 自己定义的拦截器类
	 * 
	 * @return
	 */
	@Bean
	SysUserLoginInterceptor sysUserLoginInterceptor() {
		return new SysUserLoginInterceptor();
	}

	/**
	 * 添加拦截器
	 * 
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sysUserLoginInterceptor());
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**").addPathPatterns("/two/**");
	}

}