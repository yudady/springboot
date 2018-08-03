package com.tommy.springboot.datasource;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import oracle.jdbc.xa.client.OracleXADataSource;

@Configuration
public class RdDataSourceConfig {

	@Value("${datasource.rd.driver-class-name}")
	private String driver;

	@Value("${datasource.rd.jdbc-url}")
	private String url;

	@Value("${datasource.rd.username}")
	private String username;

	@Value("${datasource.rd.password}")
	private String password;

	// 配置数据源
	@Bean(name = "rdDataSource")
	public DataSource dataSource(@Qualifier("dataSource") AtomikosDataSourceBean xaDataSource) throws SQLException {

		Properties properties = new Properties();
		properties.setProperty("max-pool-size","25");
		properties.setProperty("min-pool-size","1");

		OracleXADataSource dataSource = new OracleXADataSource();
		dataSource.setConnectionProperties(properties);
		dataSource.setURL(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);

		xaDataSource.setXaDataSource(dataSource);
		xaDataSource.setUniqueResourceName("rdDataSource");

		return xaDataSource;
	}

	@Bean(name = "rdSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("rdDataSource") DataSource dataSource)
		throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// bean.setMapperLocations(new
		// PathMatchingResourcePatternResolver().getResources("classpath:com/test/springboot/dao2/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "rdSqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
		@Qualifier("rdSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}