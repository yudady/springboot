package tk.tommy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SystemConfigApp implements CommandLineRunner {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	JdbcTemplate jdbcTemplateOracle;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	JdbcTemplate jdbcTemplateMysql;

	public static void main(String[] args) {
		SpringApplication.run(SystemConfigApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Map<String, Object>> maps = jdbcTemplateOracle.queryForList("select * from PY_SYSTEM_CONFIG");

		for (Map<String, Object> map : maps) {
			try {
				String sql = "INSERT INTO OH_SYSTEM_CONFIG (   `TYPE`,  `KEY`, `VALUE`,  `DESCR`, `SORT_VALUE`, `CREATE_DATE`,`UPDATE_DATE` )";

				sql += " VALUES ('" + map.get("TYPE") + "' ,'" + map.get("KEY") + "' ,'" + map.get("VALUE");
				sql += "' , '" + map.get("DESCR") + "'  , " + map.get("SORT_VALUE") + " , now() , now() )";

				System.out.println(sql);
				jdbcTemplateMysql.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
