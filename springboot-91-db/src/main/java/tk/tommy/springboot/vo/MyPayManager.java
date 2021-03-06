package tk.tommy.springboot.vo;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
public class MyPayManager {

	private static Map<String, MyPay> dynamicCustContainers = Collections
		.synchronizedMap(new LinkedHashMap<>());

	public static JdbcTemplate getJdbcTemplateByCustName(String custName) {
		return dynamicCustContainers.get(custName).getJdbcTemplate();
	}

	public static Map<String, JdbcTemplate> getAll() {
		LinkedHashMap<String, JdbcTemplate> returnMap = new LinkedHashMap<>();
		Iterator<Map.Entry<String, MyPay>> iterator = dynamicCustContainers.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, MyPay> next = iterator.next();
			String key = next.getKey();
			MyPay value = next.getValue();
			returnMap.put(key, value.getJdbcTemplate());
		}

		return returnMap;

	}

	public static void addOne(MyPay dynamicCustContainer) {
		dynamicCustContainers.put(dynamicCustContainer.getCustName(), dynamicCustContainer);
	}
}
