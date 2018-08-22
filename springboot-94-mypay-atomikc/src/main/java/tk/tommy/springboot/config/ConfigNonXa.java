package tk.tommy.springboot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
@Configuration
public class ConfigNonXa {

	@Bean("dataSource")
	@Primary
	@Profile("prod")
	public DataSource dataSourceA() throws SQLException {
		AtomikosNonXADataSourceBean nonXaDataSource = new AtomikosNonXADataSourceBean();
		nonXaDataSource.setUniqueResourceName("dataSourceA");
		nonXaDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		nonXaDataSource.setUrl("jdbc:oracle:thin:@35.189.177.41:1521:mypay");
		nonXaDataSource.setUser("mypaycenter");
		nonXaDataSource.setPassword("u3gfu206kf");
		nonXaDataSource.setMinPoolSize(10);
		nonXaDataSource.setPoolSize(10);
		nonXaDataSource.setMaxPoolSize(30);
		nonXaDataSource.setBorrowConnectionTimeout(60);
		nonXaDataSource.setReapTimeout(20);
		nonXaDataSource.setMaxIdleTime(60);
		nonXaDataSource.setMaintenanceInterval(60);
		return nonXaDataSource;

	}

	@Bean("jdbcTemplate")
	@Primary
	@Profile("prod")
	public JdbcTemplate jdbcTemplateA() throws SQLException {
		return new JdbcTemplate(dataSourceA());
	}

	@Bean("dataSource")
	@Primary
	@Profile("dev")
	public DataSource dataSourceB() throws SQLException {
		AtomikosNonXADataSourceBean nonXaDataSource = new AtomikosNonXADataSourceBean();
		nonXaDataSource.setUniqueResourceName("dataSourceB");
		nonXaDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		nonXaDataSource.setUrl("jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
		nonXaDataSource.setUser("mypaycenter");
		nonXaDataSource.setPassword("myPay4Zv");
		nonXaDataSource.setMinPoolSize(10);
		nonXaDataSource.setPoolSize(10);
		nonXaDataSource.setMaxPoolSize(30);
		nonXaDataSource.setBorrowConnectionTimeout(60);
		nonXaDataSource.setReapTimeout(20);
		nonXaDataSource.setMaxIdleTime(60);
		nonXaDataSource.setMaintenanceInterval(60);
		return nonXaDataSource;
	}

	@Bean("jdbcTemplate")
	@Primary
	@Profile("dev")
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
