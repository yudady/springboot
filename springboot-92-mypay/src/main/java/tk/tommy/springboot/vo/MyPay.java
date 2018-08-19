package tk.tommy.springboot.vo;

import java.util.Properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class MyPay {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Autowired
	// ApplicationContext applicationContext;

	private String custName;
	private String ip;
	private String username;
	private String password;
	private HikariDataSource hikariDataSource;
	private JdbcTemplate jdbcTemplate;
	private Properties properties;
	private HikariConfig hikariConfig;

	// public MyPay(String custName, String ip, String username, String password) {
	// try {
	// this.custName = custName;
	// this.ip = ip;
	// this.username = username;
	// this.password = password;
	//
	//
	//
	//
	//// dynamicRegisterBean2SpringContainer.dynamicCreateBean(this.getClass(),
	// custName);
	// } catch (Throwable e) {
	// logger.error(custName + " : 资料库设定失败！！！");
	// throw new RuntimeException(e);
	// }
	// }

	public void initMethod() {

		properties = new Properties();
		properties.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
		properties.setProperty("jdbcUrl", "jdbc:oracle:thin:@" + ip + ":1521:mypay");
		properties.setProperty("username", username);
		properties.setProperty("password", password);
		properties.setProperty("connectionTimeout", "1000");
		properties.setProperty("connectionTestQuery", "select sysdate from dual");

		hikariConfig = new HikariConfig(properties);
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
		logger.debug(this.custName + " " + ToStringBuilder.reflectionToString(this));
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public HikariConfig getHikariConfig() {
		return hikariConfig;
	}

	public void setHikariConfig(HikariConfig hikariConfig) {
		this.hikariConfig = hikariConfig;
	}
}
