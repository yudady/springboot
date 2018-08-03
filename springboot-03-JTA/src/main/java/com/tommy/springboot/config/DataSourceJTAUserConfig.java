package com.tommy.springboot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@MapperScan(basePackages = "com.tommy.springboot.mapper.user", sqlSessionTemplateRef = "jtaUserSqlSessionTemplate")
public class DataSourceJTAUserConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.jta-user")
	@Primary
	@FlywayDataSource
	public DataSource dataSourceJTAUser() {
		return new AtomikosDataSourceBean();
	}

	@Bean
	@Primary
	public SqlSessionFactory jtaUserSqlSessionFactory(@Qualifier("dataSourceJTAUser") DataSource dataSource)
		throws Exception {

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
			new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		bean.setTypeAliasesPackage("com.tommy.springboot.mapper.user");
		return bean.getObject();
	}

	@Bean
	@Primary
	public SqlSessionTemplate jtaUserSqlSessionTemplate(
		@Qualifier("jtaUserSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}