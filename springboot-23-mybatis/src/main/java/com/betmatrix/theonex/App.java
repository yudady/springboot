package com.betmatrix.theonex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.betmatrix.theonex.dao.br.dao")//将项目中对应的mapper类的路径加进来就可以了
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
