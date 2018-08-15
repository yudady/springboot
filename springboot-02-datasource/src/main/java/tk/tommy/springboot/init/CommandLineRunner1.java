package tk.tommy.springboot.init;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实际应用中，我们会有在项目服务启动的时候就去加载一些数据或做一些事情这样的需求。
 * 
 * 为了解决这样的问题，Spring Boot 为我们提供了一个方法，通过实现接口 CommandLineRunner 来实现。
 */
@Order(1)
@Component
public class CommandLineRunner1 implements CommandLineRunner {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void run(String... args) throws Exception {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver")
			.url("jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01").username("mypaycenter").password("myPay4Zv");
		dataSource = dataSourceBuilder.build();
		System.out.println(dataSource.getConnection().isClosed());
		System.out.println("hashCode : " + dataSource.hashCode());
		System.out.println("***CommandLineRunner  @Order(1)");
		System.out.println("***CommandLineRunner  @Order(1)");
		System.out.println("***CommandLineRunner  @Order(1)");
	}
}
