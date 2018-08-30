package tk.yudady.listener;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import tk.yudady.event.MyEvent;

/**
 * 测试用注解监听器
 */
@Component
public class MyAnnotationListener {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@EventListener
	@Order(1)
	public void listener1(MyEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		logger.info("注解事件监听-1：" + event.getMsg());
	}

	@EventListener
	@Order(2)
	public void listener2(MyEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		logger.info("注解事件监听-2：" + event.getMsg());
	}

	@EventListener
	@Order(3)
	public void listener3(MyEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		logger.info("注解事件监听-3：" + event.getMsg());
	}

	@EventListener
	@Async
	public void listenerAsync1(MyEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		logger.error("注解事件监听Async-1：" + event.getMsg());
	}

	@EventListener
	@Async
	public void listenerAsync2(MyEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		logger.error("注解事件监听Async-2：" + event.getMsg());
	}

	@EventListener
	@Async
	public void listenerAsync3(MyEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		logger.error("注解事件监听Async-3：" + event.getMsg());
	}
}