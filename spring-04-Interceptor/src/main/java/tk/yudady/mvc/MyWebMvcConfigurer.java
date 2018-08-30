package tk.yudady.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptor() { // Controller攔截器

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
				if (handler instanceof HandlerMethod) {
					HandlerMethod hd = (HandlerMethod) handler;

					System.out.println("preHandle當前執行那個類：" + hd.getBean().getClass().getName());
					System.out.println("preHandle當前執行那個方法：" + hd.getMethod());
					System.out.println("preHandle當前執行方法參數：" + hd.getMethodParameters());
					System.out.println(hd.getShortLogMessage());
					System.out.println(handler.getClass().getName());

				}

				System.out.println("執行controller之前 uri=> " + request.getRequestURI());
				return true;
			}

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
				if (handler instanceof HandlerMethod) {
					HandlerMethod hd = (HandlerMethod) handler;

					System.out.println("postHandle當前執行那個類：" + hd.getBean().getClass().getName());
					System.out.println("postHandle當前執行那個方法：" + hd.getMethod());
					System.out.println("postHandle當前執行方法參數：" + hd.getMethodParameters());
					System.out.println(hd.getShortLogMessage());
					System.out.println(handler.getClass().getName());

				}

				System.out.println("controller執行完，出exception不會執行");
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
				Object handler, Exception ex) throws Exception {
				if (handler instanceof HandlerMethod) {
					HandlerMethod hd = (HandlerMethod) handler;

					System.out.println("afterCompletion當前執行那個類：" + hd.getBean().getClass().getName());
					System.out.println("afterCompletion當前執行那個方法：" + hd.getMethod());
					System.out.println("afterCompletion當前執行方法參數：" + hd.getMethodParameters());
					System.out.println(hd.getShortLogMessage());
					System.out.println(handler.getClass().getName());
					System.out.println("如果有配 *** @ControllerAdvice *** 並且處理了exception，那這邊拿不到error" + ex);

				}

				System.out.println(" => " + request.getRequestURI() + " controller返回.like => finally");
			}
		}).addPathPatterns("/**");
	}
}
