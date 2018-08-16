package tk.tommy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		// TODO 这边没作用 FIXME
		// TODO 这边没作用 FIXME
		// TODO 这边没作用 FIXME
		application.listeners(new BeforeStartEvent(), new AfterEvent());
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	// Boot 上下文启动之前执行的
	class BeforeStartEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

		private Logger logger = LoggerFactory.getLogger(BeforeStartEvent.class);

		@Override
		public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
			System.out.println("++++++++++BeforeStartEvent+++++++++");
		}
	}

	// Boot 上下文启动完毕，可以对外提供请求服务的时候立即执行的
	class AfterEvent implements ApplicationListener<ApplicationReadyEvent> {

		private Logger logger = LoggerFactory.getLogger(AfterEvent.class);

		@Override
		public void onApplicationEvent(ApplicationReadyEvent event) {
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
			System.out.println("==========AfterEvent==========");
		}
	}
}
