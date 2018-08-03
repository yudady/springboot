package com.tommy.springboot.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("mypaycenterDataSource")
	private DataSource mypaycenterDataSource;

	@Autowired
	@Qualifier("rdDataSource")
	private DataSource rdDataSource;

	@RequestMapping(value = "/")
	public @ResponseBody String index() throws SQLException {
		logger.debug(mypaycenterDataSource.getConnection().toString());
		logger.debug(rdDataSource.getConnection().toString());
		return "123123";
	}

}
