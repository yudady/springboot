package tk.yudady;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import tk.yudady.publish.ListenerService;

/**
 * Spring的事件为Bean和Bean之间的消息传递提供支持。当一个对象处理完某种任务后，通知另外的对象进行某些处理，常用的场景有进行某些操作后发送通知，消息、邮件等情况。
 * Spring提供5种标准的事件监听：
 *
 * 上下文更新事件（ContextRefreshedEvent）：该事件会在ApplicationContext被初始化或者更新时发布。也可以在调用ConfigurableApplicationContext接口中的refresh()方法时被触发。
 * 上下文开始事件（ContextStartedEvent）：当容器ConfigurableApplicationContext的Start()方法开始/重新开始容器时触发该事件。
 * 上下文停止事件（ContextStoppedEvent）：当容ConfigurableApplicationContext的Stop()方法停止容器时触发该事件。
 * 上下文关闭事件（ContextClosedEvent）：当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁。
 * 请求处理事件（RequestHandledEvent）：在Web应用中，当一个http请求（request）结束触发该事件。
 * 不过也有些实际场景并用不上框架提供的标准事件，这个时候我们就需要自定义事件监听 Spring的事件遵循的流程：
 *
 * 自定义事件，继承ApplicationEvent（org.springframework.context.ApplicationEvent）
 * 定义监听事件，实现ApplicationListener（org.springframework.context.ApplicationListener）
 * 使用容器触发事件
 */
@SpringBootApplication
@EnableAsync
public class PublishEventListenerApp implements CommandLineRunner {

	@Autowired
	ListenerService ListenerService;

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext run = SpringApplication.run(PublishEventListenerApp.class, args);

		AsyncTaskExecutor executor = run.getBean(AsyncTaskExecutor.class);
		TimeUnit.SECONDS.sleep(10);
		run.close();


	}

	@Override
	public void run(String... args) throws Exception {
		ListenerService.publish(" PUBLISH MSG ~~~~  ");

	}

	// 自定义线程池，当配置多个executor时，被@Async("id")指定使用；也被作为线程名的前缀
	@Bean
	public AsyncTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		// 线程池名字
		executor.setThreadNamePrefix("async-Pool-");
		// 最大线程数
		executor.setMaxPoolSize(50);
		// 最小线程数
		executor.setCorePoolSize(3);

		// 使用预定义的异常处理类
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		// 自定义拒绝策略
		/*
		 * executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
		 * 
		 * @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor
		 * executor) { //........ } });
		 */
		return executor;
	}
}
