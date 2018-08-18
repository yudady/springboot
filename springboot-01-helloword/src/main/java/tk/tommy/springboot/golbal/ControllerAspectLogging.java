package tk.tommy.springboot.golbal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspectLogging {

	final static Logger log = LoggerFactory.getLogger(ControllerAspectLogging.class);

	@Pointcut("execution(* tk.tommy.springboot.controller..*.*(..))")
	public void performance() {
	}

	@Before("performance()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		log.info("doBefore");
	}

	@Around("performance()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

		// 记录起始时间
		long begin = System.currentTimeMillis();
		Object result = "";
		/** 执行目标方法 */
		try {
			result = joinPoint.proceed();
		} catch (Exception e) {
			log.error("日志记录发生错误, errorMessage: {}", e.getMessage());
		} finally {
			/** 记录操作时间 */
			long took = (System.currentTimeMillis() - begin) / 1000;
			if (took >= 10) {
				log.error("Service 执行时间为: {}秒", took);
			} else if (took >= 5) {
				log.warn("Service 执行时间为: {}秒", took);
			} else if (took >= 2) {
				log.info("Service执行时间为: {}秒", took);
			}
		}
		return result;
	}

	@After("performance()")
	public void logAfter(JoinPoint joinPoint) {

		log.info("logAfter() is running!");
		log.info("hijacked : " + joinPoint.getSignature().getName());
		log.info("******");

	}

	@AfterReturning(returning = "result", pointcut = "performance()")
	public void doAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
		// 处理完请求，返回内容
		log.info("doAfterReturning");
	}

	@AfterThrowing(pointcut = "performance()", throwing = "error")
	public void afterThrowing(JoinPoint joinPoint, Throwable error) throws Throwable {
		log.info("doAfterThrowing");
	}

}