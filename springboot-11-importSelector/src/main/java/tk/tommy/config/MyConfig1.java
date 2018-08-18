package tk.tommy.config;

import org.springframework.context.annotation.Bean;

import tk.tommy.springboot.vo.AppBean;
public class MyConfig1 {

	@Bean
	AppBean appBean() {
		return new AppBean("from config 1");
	}
}