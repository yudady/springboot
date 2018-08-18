package tk.tommy.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tk.tommy.springboot.dao.RdRepository;
import tk.tommy.springboot.vo.MyPayManager;

@RestController
public class HelloController {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ApplicationContext applicationContext;

	// LocalDateTime target = LocalDateTime.parse("2018-08-17 22:30:00", formatter);
	// LocalDateTime target = LocalDateTime.parse("2018-08-18 13:00:00", formatter);

	@Autowired
	RdRepository rdRepository;

	String sql2Msg = "已支付-通知中 ➞ 已支付-通知中";
	String sql1Msg = "已支付-通知失败 ➞ 已支付-通知成功";
	String sql3Msg = "已支付-通知中 ➞ 已支付-通知失败";

	@RequestMapping(value = "/")
	public @ResponseBody String index() throws IOException {

		LocalDateTime target = LocalDateTime.now().plusMinutes(-15);
		String searchDate = LocalDate.now().toString().replace("-", "");

		String sql1 = "SELECT * FROM PY_MYPAY_ORDER_LOG WHERE status = '" + sql1Msg + "' AND order_no LIKE 'M"
			+ searchDate + "%' ORDER BY order_no DESC";

		String sql2 = "SELECT * FROM PY_MYPAY_ORDER_LOG WHERE status = '" + sql2Msg + "' AND order_no LIKE 'M"
			+ searchDate + "%' ORDER BY order_no DESC";

		String sql3 = "SELECT * FROM PY_MYPAY_ORDER_LOG WHERE status = '" + sql3Msg + "' AND order_no LIKE 'M"
			+ searchDate + "%' ORDER BY order_no DESC";

		StringBuffer msgs = new StringBuffer();

		Map<String, JdbcTemplate> its = MyPayManager.getAll();
		// 已支付-未通知 ➞ 已支付-通知失败
		its.entrySet().stream().parallel().forEach(pair -> {
			String custName = pair.getKey();
			JdbcTemplate jdbcTemplate = pair.getValue();
			List<Map<String, Object>> pyMypayOrderLogs1 = jdbcTemplate.queryForList(sql1);
			// msgs.append(sql1Msg).append("<br/>");
			for (int i = 0; i < pyMypayOrderLogs1.size(); i++) {
				Map<String, Object> rs = pyMypayOrderLogs1.get(i);
				String row = custName + " : " + rs.get("ORDER_NO") + " -- " + rs.get("STATUS") + " -- "
					+ rs.get("CREATE_DATE");
				System.out.println(row);
				String dd = rs.get("CREATE_DATE").toString().trim().substring(0, 19);
				LocalDateTime dateTime = LocalDateTime.parse(dd, formatter);
				if (dateTime.compareTo(target) > 0) {
					msgs.append(row).append("<br/>");
				}

			}
			// msgs.append(sql2Msg).append("<br/>");
			List<Map<String, Object>> pyMypayOrderLogs2 = jdbcTemplate.queryForList(sql2);
			for (int i = 0; i < pyMypayOrderLogs2.size(); i++) {
				Map<String, Object> rs = pyMypayOrderLogs2.get(i);
				String row = custName + " : " + rs.get("ORDER_NO") + " -- " + rs.get("STATUS") + " -- "
					+ rs.get("CREATE_DATE");
				System.out.println(row);
				String dd = rs.get("CREATE_DATE").toString().trim().substring(0, 19);
				LocalDateTime dateTime = LocalDateTime.parse(dd, formatter);
				if (dateTime.compareTo(target) > 0) {
					msgs.append(row).append("<br/>");
				}

			}

			// msgs.append(sql3Msg).append("<br/>");
			List<Map<String, Object>> pyMypayOrderLogs3 = jdbcTemplate.queryForList(sql3);
			for (int i = 0; i < pyMypayOrderLogs3.size(); i++) {
				Map<String, Object> rs = pyMypayOrderLogs3.get(i);
				String row = custName + " : " + rs.get("ORDER_NO") + " -- " + rs.get("STATUS") + " -- "
					+ rs.get("CREATE_DATE");
				System.out.println(row);
				String dd = rs.get("CREATE_DATE").toString().trim().substring(0, 19);
				LocalDateTime dateTime = LocalDateTime.parse(dd, formatter);
				if (dateTime.compareTo(target) > 0) {
					msgs.append(row).append("<br/>");
				}

			}
		});

		/*
		 * Iterator<Map.Entry<String, JdbcTemplate>> iterator =
		 * its.entrySet().iterator(); while (iterator.hasNext()) { Map.Entry<String,
		 * JdbcTemplate> pair = iterator.next(); String custName = pair.getKey();
		 * JdbcTemplate jdbcTemplate = pair.getValue(); List<Map<String, Object>>
		 * pyMypayOrderLogs = jdbcTemplate.queryForList(
		 * "SELECT * FROM PY_MYPAY_ORDER_LOG WHERE status = '已支付-未通知 ➞ 已支付-未通知' AND order_no LIKE 'M20180817%' ORDER BY order_no DESC"
		 * ); for (int i = 0; i < pyMypayOrderLogs.size(); i++) { Map<String, Object> rs
		 * = pyMypayOrderLogs.get(i); String row = custName + " : " + rs.get("ORDER_NO")
		 * + " -- " + rs.get("STATUS") + " -- " + rs.get("CREATE_DATE");
		 * System.out.println(row); String dd =
		 * rs.get("CREATE_DATE").toString().trim().substring(0, 19); LocalDateTime
		 * dateTime = LocalDateTime.parse(dd, formatter); if (dateTime.compareTo(target)
		 * > 0) { msgs.append(row).append("\n"); }
		 * 
		 * }
		 * 
		 * }
		 */

		if (false) {
			FileUtils.writeStringToFile(new File("C:/Users/yu_da/OneDrive/Desktop/已支付-未通知.log"),
				msgs.toString(), "UTF-8");
		}

		if (StringUtils.isBlank(msgs.toString())) {
			msgs.append("no data").append("  -----  JdbcTemplate.size() " + its.size());
		}
		return msgs.toString();
	}

	@RequestMapping(value = "/count")
	public @ResponseBody String count() throws IOException {

		List<Map<String, Object>> notYetNotifyOrder = rdRepository.queryForListPyNotYetNotifyOrder();
		List<Map<String, Object>> failOrder = rdRepository.queryForListPyNotifyFailOrder();

		System.out.println(notYetNotifyOrder.size());
		System.out.println(failOrder.size());

		return "notYetNotifyOrder : " + notYetNotifyOrder.size() + " --  failOrder : " + failOrder.size();
	}

	@RequestMapping(value = "/orders")
	public @ResponseBody String orderAll() throws IOException {

		List<Map<String, Object>> orders = rdRepository.queryForListPyOrder();

		return "orders : " + orders.size();
	}

}
