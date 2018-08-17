package tk.tommy.springboot.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PyCustRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> queryForListPyCust() {

		String sql = "SELECT * FROM PY_CUST ORDER BY ID ";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> queryForListPyNotYetNotifyOrder() {
		String sql = "SELECT * FROM PY_NOT_YET_NOTIFY_ORDER  ";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> queryForListPyNotifyFailOrder() {
		String sql = "SELECT * FROM PY_NOTIFY_FAIL_ORDER  ";
		return jdbcTemplate.queryForList(sql);
	}

}
