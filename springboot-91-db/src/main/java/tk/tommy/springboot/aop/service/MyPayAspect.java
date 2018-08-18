package tk.tommy.springboot.aop.service;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		Object retVal = null;
		try {
			System.out.println("Around--目标方法调用之前执行");

			for (int i = 0; i < pjp.getArgs().length; i++) {
				System.out.println(pjp.getArgs()[i]);
			}
			retVal = pjp.proceed();

			System.out.println("Around--返回值 => " + retVal);
			System.out.println("Around--目标方法返回后调用");
		} catch (Throwable e) {
			System.out.println("Around--目标方法抛出异常后调用");
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

		logger.info("requset method name is: " + methodName);
		logger.info("request URL is: " + request.getRequestURL().toString());
		logger.info("request http method: " + request.getMethod());
		logger.info("request arguments are: " + Arrays.toString(pjp.getArgs()));
	}
}