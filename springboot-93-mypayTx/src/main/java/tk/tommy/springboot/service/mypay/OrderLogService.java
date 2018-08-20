package tk.tommy.springboot.service.mypay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tk.tommy.springboot.init.MyPayService;
import tk.tommy.springboot.init.config.MyPayTransaction;

@Service
public class OrderLogService {

	@Autowired
	MyPayService myPayService;

	public String getPyMyPayOrderLog(String custName) {
		JdbcTemplate jdbcTemplate = myPayService.getJdbcTemplateByCustName(custName);

		String auth = jdbcTemplate.queryForObject(
			"select value from PY_SYSTEM_CONFIG where type ='SYSTEM' and key = 'MYPAY_AUTHORIZATION_CODE'",
			String.class);
		return custName + " - " + auth;
	}

	@MyPayTransaction
	public void testTransactionFail(String custName) {

		String sql = "SELECT count(*) FROM PY_USER";
		String sql2 = "update PY_USER set descr = 'tommy' where id =  481";
		String sql3 = "SELECT * FROM PY_USER where id = 481 ";
		String sql4 = "insert into PY_USER vvv ";
		JdbcTemplate jdbcTemplate = myPayService.getJdbcTemplateByCustName(custName);

		jdbcTemplate.update(sql);
		jdbcTemplate.update(sql2);
		jdbcTemplate.update(sql3);
		jdbcTemplate.update(sql4);

	}

}
