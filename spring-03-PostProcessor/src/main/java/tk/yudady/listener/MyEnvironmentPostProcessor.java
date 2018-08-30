package tk.yudady.listener;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

	public MyEnvironmentPostProcessor() {
		System.out.println("MyEnvironmentPostProcessor.constructor");
	}

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		Properties source = new Properties();
		source.setProperty("k","V");
		PropertySource propertySource = new PropertiesPropertySource("dynamic", source);
		environment.getPropertySources().addFirst(propertySource);
		System.out.println("MyEnvironmentPostProcessor.postProcessEnvironment");
	}
}