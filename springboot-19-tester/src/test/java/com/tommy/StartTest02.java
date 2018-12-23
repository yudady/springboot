package com.tommy;

import com.tommy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartTest02App.class)
public class StartTest02 {

    @Autowired
    UserService userService;
    @Test
    public void findUser() throws Exception {
        userService.findUser();
    }

}