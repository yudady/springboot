package tk.yudady.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.yudady.dao.UserRepository;
import tk.yudady.vo.User;

@Service
public class UserService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public int addUser(User user) {
		logger.debug("User => {} " + ToStringBuilder.reflectionToString(user));
		String pwd = user.getPassword();
		user.setPassword(passwordEncoder.encode(pwd));
		return userRepository.addUser(user);
	}

}
