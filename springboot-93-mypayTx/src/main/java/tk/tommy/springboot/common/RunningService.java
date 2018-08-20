package tk.tommy.springboot.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import tk.tommy.springboot.dao.rd.RdRepository;

@Service
@Order(1)
public class RunningService implements CommandLineRunner {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RdRepository rdRepository;

	@Autowired DynamicRegisterBean2SpringContainer dynamicRegisterBean2SpringContainer;

	@Override
	public void run(String... strings) throws Exception {

		List<Map<String, Object>> maps = rdRepository.queryForListPyCust();

		for (Map<String, Object> map : maps) {

			Object disable_date = map.get("DISABLE_DATE");
			if (Objects.nonNull(disable_date)) {
				continue;
			}

			if ("003".equalsIgnoreCase(map.get("NAME").toString())) {
				continue;
			}

			System.out.println(map);
			String ip = (map.get("API_ADDR").toString()).replace("http://", "").split("/")[0];
			if ("001".equalsIgnoreCase(map.get("NAME").toString())) {
				ip = "35.185.147.170";
			}

			String custName = map.get("NAME").toString().trim();
			String username = map.get("DB_USER").toString().trim();
			String password = map.get("DB_PWD").toString().trim();
			String ipp = map.get("DB_HOST").toString().trim();

			Map<String, Object> propertyValue = new HashMap<>();
			propertyValue.put("custName", custName);
			propertyValue.put("username", username);
			propertyValue.put("password", password);
			propertyValue.put("ip", "192.168.0.23");
			dynamicRegisterBean2SpringContainer.dynamicCreateBeanByValue(MyPay.class, propertyValue,
				custName);

		}

	}

}
