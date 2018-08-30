package tk.yudady.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptor() { // Controller攔截器

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
				System.out.println("執行controller之前 => " + request.getRequestURI());
				return true;
			}

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
				System.out.println("controller執行完，出exception不會執行");
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
				Object handler, Exception ex) throws Exception {
				if(handler instanceof HandlerMethod){
					HandlerMethod hd = (HandlerMethod)handler;


					System.out.println(hd.getShortLogMessage());
					System.out.println(handler.getClass().getName());

				}


				System.out.println(" => " + request.getRequestURI() + " controller返回.like => finally");
			}
		}).addPathPatterns("/**");
	}
}
