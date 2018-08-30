package tk.tommy.springboot.config;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tk.tommy.springboot.controller.DemoFilter;
import tk.tommy.springboot.controller.DemoListener;
import tk.tommy.springboot.controller.DemoServlet;
import tk.tommy.springboot.golbal.interceptor.OneInterceptor;
import tk.tommy.springboot.golbal.interceptor.SysUserLoginInterceptor;

@Configuration
public class ConfigWebMvc implements WebMvcConfigurer {

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
	 * 添加拦截器. ***controller***
	 * 
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sysUserLoginInterceptor());
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**").addPathPatterns("/two/**");
	}

	/**
	 * Filter
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean getDemoFilter() {
		DemoFilter demoFilter = new DemoFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(demoFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");// 拦截路径，可以添加多个
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}

	/**
	 * Filter
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean getDemoServlet() {
		DemoServlet demoServlet = new DemoServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(demoServlet);
		List<String> urlMappings = new ArrayList<String>();
		urlMappings.add("/demoservlet");//// 访问，可以添加多个
		registrationBean.setUrlMappings(urlMappings);
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}

	/**
	 * Filter
	 *
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<EventListener> getDemoListener() {
		ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new DemoListener());
		registrationBean.setOrder(100);
		return registrationBean;
	}
}