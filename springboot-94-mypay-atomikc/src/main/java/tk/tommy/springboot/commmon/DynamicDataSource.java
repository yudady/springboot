package tk.tommy.springboot.commmon;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import tk.tommy.springboot.service.RdService;
import tk.tommy.springboot.utils.MyPayUtil;

@Component
public class DynamicDataSource implements CommandLineRunner {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${oracle.sid}")
	private String sid;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired RdService rdService;

	@Autowired
	MyPayUtil myPayUtil;

	@Override
	public void run(String... strings) throws Exception {

		List<Map<String, Object>> custs = rdService.findCusts();
		for (Map<String, Object> map : custs) {
			Object disable_date = map.get("DISABLE_DATE");
			if (Objects.nonNull(disable_date)) {
				continue;
			}

			if ("003".equalsIgnoreCase(map.get("NAME").toString())) {
				continue;
			}
			myPayUtil.createDatasourceJdbcTemplate(map);
		}

	}

}
