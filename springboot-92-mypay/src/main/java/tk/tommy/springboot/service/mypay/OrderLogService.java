package tk.tommy.springboot.service.mypay;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tk.tommy.springboot.vo.MyPayManager;

@Service
public class OrderLogService {

	public String getPyMyPayOrderLog(String custName) {
		String sysdate = null;
		Connection connection = null;
		try {
			JdbcTemplate jdbcTemplate = MyPayManager.getJdbcTemplateByCustName(custName);
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			sysdate = jdbcTemplate.queryForObject(
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

		return sysdate;
	}

	;
}
