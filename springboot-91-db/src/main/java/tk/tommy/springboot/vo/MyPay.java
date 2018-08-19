package tk.tommy.springboot.vo;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class MyPay implements ApplicationContextAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	private String custName;
	private HikariDataSource hikariDataSource;
	private JdbcTemplate jdbcTemplate;

	public MyPay(String custName, String ip, String username, String password) {
		try {
			this.custName = custName;
			Properties properties = new Properties();
			properties.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
			properties.setProperty("jdbcUrl", "jdbc:oracle:thin:@" + ip + ":1521:mypay");
			properties.setProperty("username", username);
			properties.setProperty("password", password);
			properties.setProperty("connectionTimeout", "1000");
			properties.setProperty("connectionTestQuery", "select sysdate from dual");

			HikariConfig hikariConfig = new HikariConfig(properties);
			hikariConfig.setConnectionTimeout(10 * 1000);
			hikariConfig.setIdleTimeout(30 * 1000);
			hikariConfig.setMaxLifetime(60 * 1000);
			hikariConfig.setMinimumIdle(3);
			hikariConfig.setMaximumPoolSize(10);
			hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
			hikariConfig.addDataSourceProperty("prepStmtCacheSize", "1000");
			hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

			hikariDataSource = new HikariDataSource(hikariConfig);

			hikariDataSource = new HikariDataSource(hikariConfig);

			jdbcTemplate = new JdbcTemplate(hikariDataSource);

			MyPayManager.addOne(this);
		} catch (Throwable e) {
			logger.error(custName + " : 资料库设定失败！！！");
			throw new RuntimeException(e);
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public HikariDataSource getHikariDataSource() {
		return hikariDataSource;
	}

	public void setHikariDataSource(HikariDataSource hikariDataSource) {
		this.hikariDataSource = hikariDataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
