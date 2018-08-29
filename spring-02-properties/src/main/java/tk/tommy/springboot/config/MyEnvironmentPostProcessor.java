package tk.tommy.springboot.config;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

/**
 * org.springframework.boot.env.EnvironmentPostProcessor
 */
@Configuration
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

		Properties properties = new Properties();
		properties.setProperty("n", "v");

		PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("my", properties);
		environment.getPropertySources().addLast(propertiesPropertySource);
	}
}
