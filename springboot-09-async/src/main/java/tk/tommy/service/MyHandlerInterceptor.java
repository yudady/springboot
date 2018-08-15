package tk.tommy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异步调用可以使用AsyncHandlerInterceptor进行拦截
 */
// @Component
public class MyHandlerInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MyHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		logger.info("同步Interceptor.preHandle");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {


		logger.info("同步Interceptor.postHandle");
		// HandlerMethod handlerMethod = (HandlerMethod) handler;
		logger.info(Thread.currentThread().getName() + "服务调用完成，返回结果给客户端");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) throws Exception {

		logger.info("同步Interceptor.afterCompletion");

		if (null != ex) {
			System.out.println("发生异常:" + ex.getMessage());
		}
	}

}
