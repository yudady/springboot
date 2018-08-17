package tk.tommy.springboot.vo;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
public class DbManager {

	private static Map<String, DynamicCustContainer> dynamicCustContainers = Collections
		.synchronizedMap(new LinkedHashMap<>());

	public static JdbcTemplate getByCustName(String custName) {
		return dynamicCustContainers.get(custName).getJdbcTemplate();
	}

	public static Map<String, JdbcTemplate> getAll() {
		LinkedHashMap<String, JdbcTemplate> returnMap = new LinkedHashMap<>();
		Iterator<Map.Entry<String, DynamicCustContainer>> iterator = dynamicCustContainers.entrySet()
			.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, DynamicCustContainer> next = iterator.next();
			String key = next.getKey();
			DynamicCustContainer value = next.getValue();
			returnMap.put(key, value.getJdbcTemplate());
		}

		return returnMap;

	}

	public static void addOne(DynamicCustContainer dynamicCustContainer) {
		dynamicCustContainers.put(dynamicCustContainer.getCustName(), dynamicCustContainer);
	}
}
