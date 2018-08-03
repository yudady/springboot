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
import org.springframework.context.annotation.Primary;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import oracle.jdbc.xa.client.OracleXADataSource;

@Configuration
public class MypayCenterDataSourceConfig {

	@Value("${datasource.mypaycenter.driver-class-name}")
	private String driver;

	@Value("${datasource.mypaycenter.jdbc-url}")
	private String url;

	@Value("${datasource.mypaycenter.username}")
	private String username;

	@Value("${datasource.mypaycenter.password}")
	private String password;

	// 配置数据源
	@Bean(name = "mypaycenterDataSource")
	public DataSource mypaycenterDataSource(@Qualifier("dataSource") AtomikosDataSourceBean xaDataSource)
		throws SQLException {

		Properties properties = new Properties();
		properties.setProperty("max-pool-size", "25");
		properties.setProperty("min-pool-size", "1");

		OracleXADataSource dataSource = new OracleXADataSource();
		dataSource.setConnectionProperties(properties);
		dataSource.setURL(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);

		xaDataSource.setXaDataSource(dataSource);
		xaDataSource.setUniqueResourceName("mypaycenterDataSource");

		return xaDataSource;
	}

	@Bean(name = "mypaycenterSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("mypaycenterDataSource") DataSource dataSource)
		throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// bean.setMapperLocations(new
		// PathMatchingResourcePatternResolver().getResources("classpath:com/test/springboot/dao2/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "mypaycenterSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
		@Qualifier("mypaycenterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}