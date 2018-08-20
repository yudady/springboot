package tk.tommy.springboot.dao.rd;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RdRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> queryForListPyCust() {

		String sql = "SELECT * FROM PY_CUST ORDER BY ID ";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> queryForListPyOrder() {

		String sql = "SELECT * FROM PY_ORDER ORDER BY REMARK ";
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

	public int rdFail() {
		String sql = " UPDATE FAIL_SQL SET n = '123' WHERE TG";
		return jdbcTemplate.update(sql);
	}

	public Integer rdOk() {
		String sql = " UPDATE PY_USER SET DESCR = 'zonvan123' WHERE id = 1";
		return jdbcTemplate.update(sql);
	}
}
