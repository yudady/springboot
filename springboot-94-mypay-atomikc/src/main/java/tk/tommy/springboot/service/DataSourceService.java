package tk.tommy.springboot.service;

import java.util.Map;

import javax.naming.NameNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tk.tommy.springboot.utils.MyPayUtil;

@Service
public class DataSourceService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyPayUtil myPayUtil;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${oracle.sid}")
	private String sid;

	public void destroyDatasource(String custNum) throws NameNotFoundException {
		myPayUtil.destroyDatasource(custNum);
	}

	public Map<String, Object> createDatasource(String custNum) {
		Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM py_cust where id = " + custNum);
		myPayUtil.createDatasourceJdbcTemplate(map);
		return map;
	}
}
