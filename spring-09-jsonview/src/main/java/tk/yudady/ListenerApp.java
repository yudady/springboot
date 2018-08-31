package tk.yudady;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import tk.yudady.mvc.TestListener;
@SpringBootApplication
public class ListenerApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListenerApp.class);

	@Bean
	public ServletListenerRegistrationBean<TestListener> testListenerRegistration() {
		ServletListenerRegistrationBean<TestListener> registration = new ServletListenerRegistrationBean<TestListener>(
			new TestListener());
		return registration;
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ListenerApp.class, args);

	}
}
