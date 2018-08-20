package tk.tommy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * POST : http://localhost:8080//actuator/shutdown
 */

@SpringBootApplication @EnableAsync // 开启异步 	@Async
@EnableSwagger2 // http://localhost:8080/swagger-ui.html
public class App {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(App.class, args);

	}

}
