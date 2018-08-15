package tk.tommy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import tk.tommy.bean.MyBean;
import tk.tommy.bean.MyBean2;
import tk.tommy.bean.MyBeanFactory;
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

	@Bean
	public MyBeanFactory myBeanFactory() {
		return new MyBeanFactory();
	}

}
