package tk.yudady;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EhcacheApp {

	private static final Logger log = LoggerFactory.getLogger(EhcacheApp.class);

	public static void main(String[] args) {
		SpringApplication.run(EhcacheApp.class);
	}

}
