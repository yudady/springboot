package tk.tommy.springboot.service.mypay;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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


	public void test01(String custName){
		String sql = "SELECT count(*) FROM PY_SYSTEM_CONFIG";

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		//事务状态类，通过PlatformTransactionManager的getTransaction方法根据事务定义获取；获取事务状态后，Spring根据传播行为来决定如何开启事务
		JdbcTemplate jdbcTemplate = myPayService.getJdbcTemplateByCustName(custName);
		PlatformTransactionManager txManager = myPayService.getPlatformTransactionManager(custName);
		TransactionStatus status = txManager.getTransaction(def);


		int i = jdbcTemplate.queryForObject(sql,Integer.class);
		System.out.println("表中记录总数："+i);
		try {
			jdbcTemplate.update(sql, "1");
			txManager.commit(status);  //提交status中绑定的事务
		} catch (RuntimeException e) {
			txManager.rollback(status);  //回滚
		}
		i = jdbcTemplate.queryForObject(sql,Integer.class);
		System.out.println("表中记录总数："+i);
	}
}
