package tk.tommy.springboot.init;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
public class CommandLineRunner2 implements CommandLineRunner {

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
		System.out.println("***CommandLineRunner  @Order(4)");
		System.out.println("***CommandLineRunner  @Order(4)");
		System.out.println("***CommandLineRunner  @Order(4)");
	}
}
