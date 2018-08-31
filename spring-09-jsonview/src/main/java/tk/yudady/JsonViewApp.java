package tk.yudady;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class JsonViewApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonViewApp.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JsonViewApp.class, args);

	}
}
