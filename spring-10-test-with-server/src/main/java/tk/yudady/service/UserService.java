package tk.yudady.service;

import org.springframework.stereotype.Service;

import tk.yudady.model.User;
@Service
public class UserService {

	public User findById(Long id) {
		User user = new User();
		user.setId(id);
		user.setName("tommy");
		return user;
	}
}