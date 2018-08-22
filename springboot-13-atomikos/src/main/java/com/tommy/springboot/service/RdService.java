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

	@Transactional(rollbackFor = Exception.class)
	public void test() {

		jdbcTemplateA.execute("SELECT sysdate FROM dual");
		int update = jdbcTemplateB.update("UPDATE PY_USER SET DESCR = '1' WHERE id = 1");
		System.out.println(" zv center " + update);
		JdbcTemplate jdbcTemplateC = applicationContext.getBean("jdbcTemplateC", JdbcTemplate.class);

		jdbcTemplateC.execute("UPDATE PY_USER SET DESCR = '1' WHERE id = 481");

		System.out.println(jdbcTemplateA.hashCode());
		System.out.println(jdbcTemplateB.hashCode());
		System.out.println(jdbcTemplateC.hashCode());
	}

}
