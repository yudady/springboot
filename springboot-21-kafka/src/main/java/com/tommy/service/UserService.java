package com.tommy.service;

import com.tommy.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    UserDao userDao;
	public void findUser() {
		System.out.println("UserService.findUser");
        userDao.findUser();

	}
}
