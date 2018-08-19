package tk.tommy.springboot.init.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import tk.tommy.springboot.vo.MyPay;

@Configuration
public class Config {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Scope("prototype")
	@Lazy
	public MyPay myPay(String custName, String ip, String username, String password) {
		return new MyPay(custName, ip, username, password);
	}
	//
	// @Bean
	// @Scope("prototype")
	// public HikariConfig hikariConfig(Properties properties) {
	// return new HikariConfig(properties);
	// }
	//
	// @Bean
	// @Scope("prototype")
	// public HikariDataSource hikariDataSource(HikariConfig hikariConfig) {
	// return new HikariDataSource(hikariConfig);
	// }
	//
	// @Bean
	// @Scope("prototype")
	// public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
	// return new JdbcTemplate(hikariDataSource);
	// }

}
