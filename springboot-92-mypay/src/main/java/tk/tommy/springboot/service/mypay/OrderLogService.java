package tk.tommy.springboot.service.mypay;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderLogService {

	@Autowired
	MyPayService myPayService;

	public String getPyMyPayOrderLog(String custName) {
		String authorizationCode = null;
		Connection connection = null;
		try {

			JdbcTemplate jdbcTemplate = myPayService.getJdbcTemplateByCustName(custName);
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			authorizationCode = jdbcTemplate.queryForObject(
				"select value from PY_SYSTEM_CONFIG where type ='SYSTEM' and key = 'MYPAY_AUTHORIZATION_CODE'",
				String.class);

			System.out.println("connection AutoCommit => " + connection.getAutoCommit());

			connection.rollback();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return authorizationCode;
	}

}
