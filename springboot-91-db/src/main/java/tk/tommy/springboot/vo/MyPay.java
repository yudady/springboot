package tk.tommy.springboot.vo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Properties;
public class MyPay {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String custName;
	private Properties configFile;
	private HikariDataSource hikariDataSource;
	private JdbcTemplate jdbcTemplate;

	public MyPay(String custName, String ip, String username, String password){
		try {
			this.custName = custName;
			configFile = new Properties();
			configFile.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
			configFile.setProperty("jdbcUrl", "jdbc:oracle:thin:@" + ip + ":1521:mypay");
			configFile.setProperty("username", username);
			configFile.setProperty("password", password);
			configFile.setProperty("connectionTimeout", "1000");
			configFile.setProperty("connectionTestQuery", "select sysdate from dual");
			HikariConfig config = new HikariConfig(configFile);
			config.setConnectionTimeout(10 * 1000);
			config.setIdleTimeout(30 * 1000);
			config.setMaxLifetime(60 * 1000);
			config.setMinimumIdle(3);
			config.setMaximumPoolSize(10);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "1000");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");



			hikariDataSource = new HikariDataSource(config);
			jdbcTemplate = new JdbcTemplate(hikariDataSource);
			MyPayManager.addOne(this);
		} catch (Throwable e) {
			logger.error(custName + " : 资料库设定失败！！！");
			throw new RuntimeException(e);
		}
	}
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Properties getConfigFile() {
		return configFile;
	}

	public void setConfigFile(Properties configFile) {
		this.configFile = configFile;
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
