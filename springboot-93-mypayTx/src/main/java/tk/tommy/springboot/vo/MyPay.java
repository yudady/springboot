package tk.tommy.springboot.vo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 处理connection
 * 
 */
public class MyPay {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	ThreadLocal<Connection> ThreadLocalConnection = new ThreadLocal<>();

	private String custName;
	private String ip;
	private String username;
	private String password;
	private HikariDataSource hikariDataSource;
	private JdbcTemplate jdbcTemplate;
	private Properties properties;
	private HikariConfig hikariConfig;
	private PlatformTransactionManager platformTransactionManager;

	public void initMethod() {

		properties = new Properties();
		properties.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
		properties.setProperty("jdbcUrl", "jdbc:oracle:thin:@" + ip + ":1521:zvo11g01");
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

		platformTransactionManager = new DataSourceTransactionManager(hikariDataSource);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		jdbcTemplate = new JdbcTemplate(hikariDataSource);

		logger.debug(this.custName + " " + ToStringBuilder.reflectionToString(this));
	}

	public Connection getConnection(boolean autoCommit) throws SQLException {
		Connection connection = DataSourceUtils.getConnection(hikariDataSource);
		connection.setAutoCommit(autoCommit);
		ThreadLocalConnection.set(connection);
		return connection;
	}

	public void destoryMethod() throws InterruptedException {

		while (!hikariDataSource.isClosed()) {
			TimeUnit.SECONDS.sleep(1);
			hikariDataSource.close();
		}

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

	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}

}
