package tk.tommy;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableAsync // 开启异步 	@Async
@EnableSwagger2 // http://localhost:8080/swagger-ui.html
public class App {

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	/**
	 * 线程池配置(异步线程池)
	 * 
	 * @return
	 */
	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("async-pool");
		executor.initialize();
		return executor;
	}

}