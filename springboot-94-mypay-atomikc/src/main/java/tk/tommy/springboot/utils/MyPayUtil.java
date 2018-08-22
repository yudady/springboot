package tk.tommy.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyPayUtil {

	@Autowired
	ApplicationContext applicationContext;

	public JdbcTemplate getJdbcTemplate(String custNum) {
		return applicationContext.getBean("jdbcTemplate" + custNum, JdbcTemplate.class);
	}
}
