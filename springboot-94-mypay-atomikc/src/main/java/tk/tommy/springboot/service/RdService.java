package tk.tommy.springboot.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RdService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> findCusts() {

		List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM py_cust");

		maps.forEach(System.out::println);

		return maps;
	}


	public List<Map<String, Object>> rdUser() {
		return jdbcTemplate.queryForList("select * from py_user");
	}

}
