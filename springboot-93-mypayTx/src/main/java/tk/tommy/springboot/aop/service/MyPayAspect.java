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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tk.tommy.springboot.init.MyPayService;

@Aspect
@Component
public class MyPayAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyPayService myPayService;

	/**
	 * 环绕通知，可以在目标方法调用前后，自定义执行内容。可以修改目标方法的返回值
	 *
	 * @param pjp
	 */
	// @Around("execution(* tk.tommy.springboot.service.mypay.**.*(..))")
	@Around("@annotation(tk.tommy.springboot.init.config.MyPayTransaction)")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = null;
		PlatformTransactionManager txManager = null;
		TransactionStatus status = null;
		try {

			boolean openTransaction = false;
			if (pjp.getArgs().length > 0) {
				Object args1 = pjp.getArgs()[0];
				if (args1 instanceof String) {
					openTransaction = true;
				}
			}

			if (openTransaction) {
				String custName = (String) pjp.getArgs()[0];

				System.out.println("custName =》 " + custName);

				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
				// 事务状态类，通过PlatformTransactionManager的getTransaction方法根据事务定义获取；获取事务状态后，Spring根据传播行为来决定如何开启事务

				txManager = myPayService.getPlatformTransactionManager(custName);
				status = txManager.getTransaction(def);
			}

			retVal = pjp.proceed();

			if (openTransaction) {
				txManager.commit(status); // 提交status中绑定的事务
			}

			logger.info(pjp.getSignature() + " " + Arrays.deepToString(pjp.getArgs()) + " return " + retVal);
		} catch (Throwable e) {
			txManager.rollback(status); // 回滚
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