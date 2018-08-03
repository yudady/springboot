package com.tommy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@SpringBootApplication
@MapperScan(basePackages = {"com.tommy.mybatis.dao"})
public class App extends SpringBootServletInitializer {

	private static ConfigurableApplicationContext context;

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) {
		context = SpringApplication.run(App.class, args);

	}

	@Bean
	public AtomikosDataSourceBean dataSource() {
		return new AtomikosDataSourceBean();
	}

}
