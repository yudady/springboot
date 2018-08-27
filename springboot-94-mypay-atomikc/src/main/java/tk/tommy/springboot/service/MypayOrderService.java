package tk.tommy.springboot.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.tommy.springboot.utils.MyPayUtil;

@Service
public class MypayOrderService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyPayUtil myPayUtil;

	@Transactional
	public Object systemNews(String custNum) {
		JdbcTemplate jt = myPayUtil.getJdbcTemplate(custNum);
		return jt.queryForList("SELECT * FROM PY_SYSTEM_NEWS");

	}

	public Object findLogs() {
		LocalDateTime now1 = LocalDateTime.now();
		List<Map<String, Object>> collect = myPayUtil.getCustsNum().stream().parallel().map(custNum -> {
			Map<String, Object> map = new LinkedHashMap<>();
			Map<Object, List<Map<String, Object>>> logByCustNum = findLogByCustNum(custNum);
			if (logByCustNum.entrySet().size() > 0) {
				map.put(custNum, logByCustNum);
			}
			return map;
		}).filter(m -> m.entrySet().size() > 0).collect(Collectors.toList());
		LocalDateTime now2 = LocalDateTime.now();

		Duration duration = Duration.between(now1, now2);
		long seconds = duration.getSeconds();

		logger.debug("[LOG] seconds	= 【" + seconds + "】");

		return collect;
	}

	public Map<Object, List<Map<String, Object>>> findLogByCustNum(String custNum) {
		String searchDate = LocalDate.now().toString().replace("-", "");
		String sql = "SELECT * FROM PY_MYPAY_ORDER_LOG WHERE order_no LIKE 'M" + searchDate + "%' ";
		JdbcTemplate jdbcTemplate = myPayUtil.getJdbcTemplate(custNum);

		Map<Object, List<Map<String, Object>>> collect = jdbcTemplate.queryForList(sql).stream()
			.collect(Collectors.groupingBy(x -> x.get("ORDER_NO"))).entrySet().stream().filter(m -> {
				return m.getValue().stream().anyMatch(m2 -> {
					if (Objects.isNull(m2.get("REMARK"))) {
						m2.put("REMARK", "");
					}
					return m2.get("REMARK").toString().contains("第1次执行回调");
				});

			}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return collect;
	}

	public Object findTodayMyPayOrderCount() {

		LocalDateTime now1 = LocalDateTime.now();
		List<Map<String, Integer>> collect = myPayUtil.getCustsNum().stream().parallel().map(custNum -> {
			Map<String, Integer> map = new LinkedHashMap<>();
			Integer countByCustNum = findTodayMyPayOrderCountByCustNum(custNum);
			if (countByCustNum > 0) {
				map.put(custNum, countByCustNum);
			}
			return map;
		}).filter(m -> m.entrySet().size() > 0).collect(Collectors.toList());
		LocalDateTime now2 = LocalDateTime.now();

		Duration duration = Duration.between(now1, now2);
		long seconds = duration.getSeconds();

		logger.debug("[LOG] seconds	= 【" + seconds + "】");

		return collect;
	}

	public Integer findTodayMyPayOrderCountByCustNum(String custNum) {
		String searchDate = LocalDate.now().toString().replace("-", "");
		String sql = "SELECT COUNT(1) FROM PY_MYPAY_ORDER WHERE order_no LIKE 'M" + searchDate + "%' ";
		JdbcTemplate jdbcTemplate = myPayUtil.getJdbcTemplate(custNum);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Object findTodayOrderCount() {
		LocalDateTime now1 = LocalDateTime.now();
		List<Map<String, Integer>> collect = myPayUtil.getCustsNum().parallelStream().map(custNum -> {
			Map<String, Integer> map = new LinkedHashMap<>();
			Integer countByCustNum = findTodayOrderCountByCustNum(custNum);
			if (countByCustNum > 0) {
				map.put(custNum, countByCustNum);
			}
			return map;
		}).filter(m -> m.entrySet().size() > 0).collect(Collectors.toList());
		LocalDateTime now2 = LocalDateTime.now();

		Duration duration = Duration.between(now1, now2);
		long seconds = duration.getSeconds();

		logger.debug("[LOG] seconds	= 【" + seconds + "】");

		return collect;
	}

	public Integer findTodayOrderCountByCustNum(String custNum) {
		String searchDate = LocalDate.now().toString().replace("-", "");
		String sql = "SELECT COUNT(1) FROM PY_ORDER WHERE order_no LIKE '" + searchDate + "%' ";
		JdbcTemplate jdbcTemplate = myPayUtil.getJdbcTemplate(custNum);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
