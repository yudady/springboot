package tk.tommy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppSpringboot {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppSpringboot.class);

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(App.class,
//			MyEnvironmentPostProcessor.class);
//		LOGGER.warn("anno.getEnvironment() => {}", anno.getEnvironment().getProperty("n"));
//		anno.close();
		ConfigurableApplicationContext run = SpringApplication.run(AppSpringboot.class, args);
		LOGGER.warn("anno.getEnvironment() => {}", run.getEnvironment().getProperty("n"));
	}
}
