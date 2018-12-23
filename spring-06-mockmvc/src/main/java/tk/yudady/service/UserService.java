package tk.yudady.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.yudady.dao.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public void findUser() {
        System.out.println("UserService.findUser");
		userDao.findUser();
    }
}
