package com.tommy.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RdService {

	@Autowired
	@Qualifier("jdbcTemplateA")
	JdbcTemplate jdbcTemplateA;

	@Autowired
	@Qualifier("jdbcTemplateB")
	JdbcTemplate jdbcTemplateB;

	@Autowired
	ApplicationContext applicationContext;

	@Transactional
	public void test() {

		jdbcTemplateA.update("SELECT sysdate FROM dual");
		jdbcTemplateB.update("SELECT sysdate FROM dual");
		JdbcTemplate jdbcTemplateC = applicationContext.getBean("jdbcTemplateC", JdbcTemplate.class);

		jdbcTemplateC.execute("SELECT sysdate FROM dualfsdffs");


		System.out.println(jdbcTemplateA.hashCode());
		System.out.println(jdbcTemplateB.hashCode());
		System.out.println(jdbcTemplateC.hashCode());
	}

}
