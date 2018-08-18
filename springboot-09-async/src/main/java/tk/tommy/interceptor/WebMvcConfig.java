package tk.tommy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import tk.tommy.service.MyAsyncHandlerInterceptor;
import tk.tommy.service.MyHandlerInterceptor;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

	private static final Logger Logger = LoggerFactory.getLogger(WebMvcConfig.class);

	/** 解决跨域问题 **/
	public void addCorsMappings(CorsRegistry registry) {
	};

	/** 添加拦截器 **/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO 不會用
		registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger**");

		registry.addInterceptor(new MyAsyncHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger**");
		// TODO async addInterceptors

	}

	/** 添加異步拦截器 **/
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO 不會用
		configurer.registerCallableInterceptors(new CallableProcessingInterceptor() {

		});

		configurer.setDefaultTimeout(10_000);

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