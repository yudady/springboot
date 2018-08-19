package tk.tommy.springboot.aop.service;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class MyPayAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 环绕通知，可以在目标方法调用前后，自定义执行内容。可以修改目标方法的返回值
	 *
	 * @param pjp
	 */
	@Around("execution(* tk.tommy.springboot.service.mypay.**.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = null;
		try {
			retVal = pjp.proceed();
			logger.info(pjp.getSignature() + " " + Arrays.deepToString(pjp.getArgs()) + " return " + retVal);
		} catch (Throwable e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
		return retVal;
	}

	public void findRequestData(ProceedingJoinPoint pjp) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		String methodName = method.getName(); // 获取被拦截的方法名

		logger.debug("requset method name is: " + methodName);
		logger.debug("request URL is: " + request.getRequestURL().toString());
		logger.debug("request http method: " + request.getMethod());
		logger.debug("request arguments are: " + Arrays.toString(pjp.getArgs()));
	}
}