package tk.tommy.springboot.command;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tk.tommy.springboot.vo.DbManager;

@Service
@Order(10)
public class RunningQuery implements CommandLineRunner {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run(String... strings) throws Exception {

//		Map<String, JdbcTemplate> its = DbManager.getAll();
//		Iterator<Map.Entry<String, JdbcTemplate>> iterator = its.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Map.Entry<String, JdbcTemplate> pair = iterator.next();
//			String custName = pair.getKey();
//			JdbcTemplate jdbcTemplate = pair.getValue();
//			List<Map<String, Object>> pyMypayOrderLogs = jdbcTemplate.queryForListPyCust(
//				"SELECT * FROM PY_MYPAY_ORDER_LOG WHERE status = '已支付-未通知 ➞ 已支付-未通知' AND order_no LIKE 'M20180816%' ORDER BY order_no DESC");
//			for (int i = 0; i < pyMypayOrderLogs.size(); i++) {
//				Map<String, Object> rs = pyMypayOrderLogs.get(i);
//				String row = custName + " : " + rs.get("ORDER_NO") + " -- " + rs.get("STATUS") + " -- "
//					+ " -- " + rs.get("CREATE_DATE");
//				// logger.info(row);
//				System.out.println(row);
//
//			}
//
//		}

	}

}
