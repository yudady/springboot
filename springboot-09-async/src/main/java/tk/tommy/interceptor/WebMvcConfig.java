package tk.tommy.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import tk.tommy.service.MyAsyncHandlerInterceptor;
import tk.tommy.service.MyHandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/** 解决跨域问题 **/
	public void addCorsMappings(CorsRegistry registry) {
	};

	/** 添加拦截器 **/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {


		registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");

		registry.addInterceptor(new MyAsyncHandlerInterceptor()).addPathPatterns("/**");
		// TODO async addInterceptors

	}

	/** 这里配置视图解析器 **/
	public void configureViewResolvers(ViewResolverRegistry registry) {
	};

	/** 配置内容裁决的一些选项 **/
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	};

	/** 视图跳转控制器 **/
	public void addViewControllers(ViewControllerRegistry registry) {
	};

	/** 静态资源处理 **/
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	};

	/** 默认静态资源处理器 **/
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	};

}