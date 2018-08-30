package tk.yudady;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class InterceptorApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorApp.class);

	public static void main(String[] args) {
		SpringApplication.run(InterceptorApp.class, args);

	}
}
