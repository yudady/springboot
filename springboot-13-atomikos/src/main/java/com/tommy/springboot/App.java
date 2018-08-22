package com.tommy.springboot;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class App {

	@Autowired
	DataSource dataSourceA;

	@Autowired
	DataSource dataSourceB;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(App.class, args);

	}
}
