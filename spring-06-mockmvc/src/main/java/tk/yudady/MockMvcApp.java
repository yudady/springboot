package tk.yudady;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class MockMvcApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockMvcApp.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MockMvcApp.class, args);

	}
}
