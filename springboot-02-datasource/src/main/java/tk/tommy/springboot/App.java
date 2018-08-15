package tk.tommy.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import tk.tommy.springboot.init.CommandLineRunner1;
import tk.tommy.springboot.init.CommandLineRunner2;

@SpringBootApplication
public class App {

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(App.class);
//		springApplication.addListeners();

		ConfigurableApplicationContext run = springApplication.run(args);




		CommandLineRunner1 bean1 = run.getBean(CommandLineRunner1.class);
		CommandLineRunner2 bean2 = run.getBean(CommandLineRunner2.class);

		System.out.println("***App 开始侦测************************");
		System.out.println(bean1.getDataSource().hashCode());
		System.out.println(bean2.getDataSource().hashCode());

	}

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

}
