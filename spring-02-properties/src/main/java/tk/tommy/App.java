package tk.tommy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import tk.tommy.springboot.config.MyEnvironmentPostProcessor;

@ComponentScan
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(App.class,
			MyEnvironmentPostProcessor.class);
		LOGGER.warn("anno.getEnvironment() => {}", anno.getEnvironment().getProperty("n"));
		anno.close();

	}
}
