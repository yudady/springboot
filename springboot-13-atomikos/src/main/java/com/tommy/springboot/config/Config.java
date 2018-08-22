package com.tommy.springboot.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
@Configuration
public class Config {

	@Bean
	@Primary
	public DataSource dataSourceA() throws SQLException {

		System.out.println("dataSource init");
		Properties properties = new Properties();
		properties.put("user","mypaycenter");
		properties.put("password","myPay4Zv");
		properties.put("URL","jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
//		properties.put("jdbcUrl","jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
		OracleXADataSource oracleXADataSource = new OracleXADataSource();
		oracleXADataSource.setConnectionProperties(properties);



		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(oracleXADataSource);
		xaDataSource.setUniqueResourceName("dataSourceA");
		xaDataSource.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
//		oracleXADataSource.setURL("jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
		xaDataSource.setXaProperties(properties);
		xaDataSource.setMinPoolSize(10);
		xaDataSource.setPoolSize(10);
		xaDataSource.setMaxPoolSize(30);
		xaDataSource.setBorrowConnectionTimeout(60);
		xaDataSource.setReapTimeout(20);
		xaDataSource.setMaxIdleTime(60);
		xaDataSource.setMaintenanceInterval(60);
		return xaDataSource;

	}

	@Bean
	@Primary
	public JdbcTemplate jdbcTemplateA() throws SQLException {
		return new JdbcTemplate(dataSourceA());
	}

	@Bean
	public DataSource dataSourceB() throws SQLException {
		System.out.println("dataSource init");
		Properties properties = new Properties();
		properties.put("user","mypaycenter");
		properties.put("password","myPay4Zv");
		properties.put("URL","jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
//		properties.put("jdbcUrl","jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
		OracleXADataSource oracleXADataSource = new OracleXADataSource();
		oracleXADataSource.setConnectionProperties(properties);
//		oracleXADataSource.setURL("jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(oracleXADataSource);
		xaDataSource.setUniqueResourceName("dataSourceB");
		xaDataSource.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
		xaDataSource.setXaProperties(properties);
		xaDataSource.setMinPoolSize(10);
		xaDataSource.setPoolSize(10);
		xaDataSource.setMaxPoolSize(30);
		xaDataSource.setBorrowConnectionTimeout(60);
		xaDataSource.setReapTimeout(20);
		xaDataSource.setMaxIdleTime(60);
		xaDataSource.setMaintenanceInterval(60);
		return xaDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplateB() throws SQLException {
		return new JdbcTemplate(dataSourceB());
	}

	@Bean(destroyMethod = "close", initMethod = "init")
	public UserTransactionManager atomikosUserTransaction() {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		return userTransactionManager;
	}

	@Bean
	public JtaTransactionManager transactionManager() {
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		jtaTransactionManager.setUserTransaction(atomikosUserTransaction());
		return jtaTransactionManager;
	}
}
