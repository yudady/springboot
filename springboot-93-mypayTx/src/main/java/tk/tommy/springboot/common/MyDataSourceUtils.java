package tk.tommy.springboot.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
@Component
public class MyDataSourceUtils {

	@Autowired
	DataSource dataSource;

	@Autowired
	ApplicationContext applicationContext;

	public Connection getRdConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	public void closeRdConnection(Connection connection) throws SQLException {
		DataSourceUtils.releaseConnection(connection, dataSource);
	}

	public Connection getMyPayConnection(String custName) {

		MyPay bean = applicationContext.getBean(custName, MyPay.class);

		return DataSourceUtils.getConnection(bean.getHikariDataSource());
	}

	public void closeMyPayConnection(String custName, Connection connection) throws SQLException {
		MyPay bean = applicationContext.getBean(custName, MyPay.class);
		DataSourceUtils.releaseConnection(connection, bean.getHikariDataSource());
	}
}
