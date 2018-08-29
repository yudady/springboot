package tk.tommy.springboot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UserApp implements CommandLineRunner {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	JdbcTemplate jdbcTemplateOracle;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	JdbcTemplate jdbcTemplateMysql;

	public static void main(String[] args) {
		SpringApplication.run(UserApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Map<String, Object>> maps = jdbcTemplateOracle.queryForList("select * from PY_USER");

		for (Map<String, Object> m : maps) {
			try {

				String sql = "INSERT INTO" + "   `OH_USER` NAMES ( `NAME`, `PWD`) VALUES " + " ('" + m.get("NAME") + "',   '" + m.get("PWD") + "'  ) ";

				System.out.println(sql);
				System.out.println(sql);
				System.out.println(sql);
				System.out.println(sql);
				jdbcTemplateMysql.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
