package tk.tommy.springboot.conf;

import org.springframework.context.annotation.Bean;

import tk.tommy.springboot.bean.MyBeanFactoryPostProcessor;
import tk.tommy.springboot.bean.MyBeanPostProcessor;
import tk.tommy.springboot.bean.MyInstantiationAwareBeanPostProcessorAdapter;
import tk.tommy.springboot.vo.User;
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
	public User user() {
		return new User();
	}

}
