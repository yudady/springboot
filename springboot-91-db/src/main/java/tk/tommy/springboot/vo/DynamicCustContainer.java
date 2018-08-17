package tk.tommy.springboot.vo;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class DynamicCustContainer {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String custName;
	private Properties configFile;
	private HikariDataSource hikariDataSource;
	private JdbcTemplate jdbcTemplate;

	public DynamicCustContainer(String custName, String ip, String username, String password) {
		try {
			this.custName = custName;
			configFile = new Properties();
			configFile.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
			configFile.setProperty("jdbcUrl", "jdbc:oracle:thin:@" + ip + ":1521:mypay");
			configFile.setProperty("username", username);
			configFile.setProperty("password", password);
			configFile.setProperty("connectionTimeout", "1000");
			hikariDataSource = new HikariDataSource(new HikariConfig(configFile));
			jdbcTemplate = new JdbcTemplate(hikariDataSource);
			DbManager.addOne(this);
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
