package tk.tommy.springboot.init.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tk.tommy.springboot.vo.DynamicCustContainer;

@Configuration
public class Config {

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

//	@Bean
//	@Scope("prototype")
//	public DynamicCustContainer dynamicCustContainer() {
//		return new DynamicCustContainer();
//	}

}