package com.tommy.springboot;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class App {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);

//		DataSource dataSourceA = run.getBean("dataSourceA", DataSource.class);
//		DataSource dataSourceB = run.getBean("dataSourceB", DataSource.class);
//
//		System.out.println(dataSourceA.getConnection().isClosed());
//		System.out.println(dataSourceA.getConnection().isClosed());
//		System.out.println(dataSourceA.getConnection().isClosed());
//		System.out.println(dataSourceB.getConnection().isClosed());
//		System.out.println(dataSourceB.getConnection().isClosed());
//		System.out.println(dataSourceB.getConnection().isClosed());
//		System.out.println(dataSourceB.getConnection().isClosed());
//		System.out.println(dataSourceB.getConnection().isClosed());
//		System.out.println(dataSourceB.getConnection().isClosed());

		String[] beanNamesForType = run.getBeanNamesForType(DataSource.class);
		System.out.println(run.getBean("dataSourceA").getClass().getName());
		System.out.println(run.getBean("dataSourceA").getClass().getName());
		System.out.println(run.getBean("dataSourceA").getClass().getName());
		System.out.println(run.getBean("dataSourceB").getClass().getName());
		System.out.println(run.getBean("dataSourceB").getClass().getName());
		System.out.println(run.getBean("dataSourceB").getClass().getName());

		System.out.println("--------------------------------");


		run.close();
	}
}
