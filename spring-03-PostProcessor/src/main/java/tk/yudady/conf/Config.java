package tk.yudady.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.yudady.bean.MyBeanFactoryPostProcessor;
import tk.yudady.bean.MyBeanPostProcessor;
import tk.yudady.bean.MyEnvironmentPostProcessor;
import tk.yudady.bean.MyInstantiationAwareBeanPostProcessorAdapter;
import tk.yudady.vo.User;

@Configuration
public class Config {

	@Bean
	public MyBeanFactoryPostProcessor myBeanFactoryPostProcessor() {
		return new MyBeanFactoryPostProcessor();
	}

	@Bean
	public MyBeanPostProcessor myBeanPostProcessor() {
		return new MyBeanPostProcessor();
	}

	@Bean
	public MyInstantiationAwareBeanPostProcessorAdapter myInstantiationAwareBeanPostProcessorAdapter() {
		return new MyInstantiationAwareBeanPostProcessorAdapter();
	}

	@Bean
	public MyEnvironmentPostProcessor myEnvironmentPostProcessor() {
		return new MyEnvironmentPostProcessor();
	}

	@Bean
	public User user() {
		return new User();
	}

}
