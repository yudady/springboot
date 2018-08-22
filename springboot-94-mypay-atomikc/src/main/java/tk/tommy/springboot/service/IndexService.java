package tk.tommy.springboot.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.tommy.springboot.utils.MyPayUtil;

@Service
public class IndexService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyPayUtil myPayUtil;

	@Autowired
	JdbcTemplate jdbcTemplate;


	@Transactional
	public void test01(String custNum) {
//		logger.debug("[LOG] custNum	= 【" + custNum + "】");
//		String sql = "select * from py_user";
//		List<Map<String, Object>> maps1 = jdbcTemplate.queryForList(sql);
//		logger.debug("[LOG] maps1	= 【" + maps1 + "】");
//		JdbcTemplate jt = myPayUtil.getJdbcTemplate(custNum);
//		List<Map<String, Object>> d1 = jt.queryForList(sql);
//		logger.debug("[LOG] d1	= 【" + d1 + "】");

	}
	@Transactional
	public Object systemNews(String custNum) {
		JdbcTemplate jt = myPayUtil.getJdbcTemplate(custNum);
		String sql = "select * from PY_SYSTEM_NEWS";
		return jt.queryForList(sql);

	}
}
