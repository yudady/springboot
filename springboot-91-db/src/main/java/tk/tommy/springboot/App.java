package tk.tommy.springboot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import tk.tommy.springboot.dao.PyCustRepository;
import tk.tommy.springboot.vo.DbManager;

@SpringBootApplication
public class App {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws IOException {

		LocalDateTime target = LocalDateTime.parse("2018-08-17 13:00:00", formatter);

		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);

		StringBuffer msgs = new StringBuffer();

		Map<String, JdbcTemplate> its = DbManager.getAll();
		Iterator<Map.Entry<String, JdbcTemplate>> iterator = its.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, JdbcTemplate> pair = iterator.next();
			String custName = pair.getKey();
			JdbcTemplate jdbcTemplate = pair.getValue();
			List<Map<String, Object>> pyMypayOrderLogs = jdbcTemplate.queryForList(
				"SELECT * FROM PY_MYPAY_ORDER_LOG WHERE status = '已支付-未通知 ➞ 已支付-未通知' AND order_no LIKE 'M20180817%' ORDER BY order_no DESC");
			for (int i = 0; i < pyMypayOrderLogs.size(); i++) {
				Map<String, Object> rs = pyMypayOrderLogs.get(i);
				String row = custName + " : " + rs.get("ORDER_NO") + " -- " + rs.get("STATUS") + " -- "
					+ rs.get("CREATE_DATE");
				System.out.println(row);
				String dd = rs.get("CREATE_DATE").toString().trim().substring(0, 19);
				LocalDateTime dateTime = LocalDateTime.parse(dd, formatter);
				if (dateTime.compareTo(target) > 0) {
					msgs.append(row).append("\n");
				}

			}

		}

		FileUtils.writeStringToFile(new File("C:/Users/yu_da/OneDrive/Desktop/已支付-未通知.log"), msgs.toString(),
			"UTF-8");

		System.out.println("***********************");
		System.out.println("***********************");
		System.out.println("***********************");
		System.out.println(msgs);
		System.out.println("**********************");

		PyCustRepository pyCustRepository = run.getBean(PyCustRepository.class);

		List<Map<String, Object>> notYetNotifyOrder = pyCustRepository.queryForListPyNotYetNotifyOrder();
		List<Map<String, Object>> failOrder = pyCustRepository.queryForListPyNotifyFailOrder();

		System.out.println(notYetNotifyOrder.size());
		System.out.println(failOrder.size());

	}

}
