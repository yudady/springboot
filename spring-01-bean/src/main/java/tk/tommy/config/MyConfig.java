package tk.tommy.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import tk.tommy.bean.*;
@Configuration
public class MyConfig {

	@Bean
	public MyBean myBean() {
		return new MyBean();
	}

	@Bean
	@Scope("prototype")
	public MyBean2 myBean2() {
		return new MyBean2();
	}

	@Bean(name = "myBeanFactory", initMethod = "init", destroyMethod = "destroy")
	public MyBeanFactory myBeanFactory() {
		return new MyBeanFactory();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PrototypeBean prototypeBean(String name, int age) {
		return new PrototypeBean(name, age);
	}

	@Bean
	public SingletonBean singletonBean() {
		return new SingletonBean();
	}

}
