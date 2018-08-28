package tk.yudady.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tk.yudady.vo.User;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int addUser(User user) {
		return jdbcTemplate.update("INSERT INTO  t_user (username , password) VALUES (? , ?)",
			user.getUsername(), user.getPassword());
	}

	public String findPasswordByUsername(String username) {
		return jdbcTemplate.queryForObject("SELECT password FROM t_user WHERE username = ? ",
			new Object[]{username}, String.class);

	}
}
