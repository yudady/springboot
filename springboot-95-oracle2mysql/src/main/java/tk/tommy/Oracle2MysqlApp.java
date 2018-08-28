package tk.tommy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Oracle2MysqlApp implements CommandLineRunner {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	JdbcTemplate jdbcTemplateOracle;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	JdbcTemplate jdbcTemplateMysql;

	public static void main(String[] args) {
		SpringApplication.run(Oracle2MysqlApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println(
			"oracle => " + jdbcTemplateOracle.queryForObject("SELECT sysdate FROM dual", String.class));
		System.out.println("mysql => " + jdbcTemplateMysql.queryForObject("SELECT now()", String.class));
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("***************************");
	}
}
