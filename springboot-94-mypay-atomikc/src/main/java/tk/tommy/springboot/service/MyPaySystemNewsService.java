package tk.tommy.springboot.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.tommy.springboot.utils.MyPayUtil;

import javax.naming.NameNotFoundException;

@Service
public class MyPaySystemNewsService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyPayUtil myPayUtil;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${oracle.sid}")
	private String sid;


	@Transactional
	public Object systemNews(String custNum) {
		JdbcTemplate jt = myPayUtil.getJdbcTemplate(custNum);
		return jt.queryForList("SELECT * FROM PY_SYSTEM_NEWS");

	}

}
