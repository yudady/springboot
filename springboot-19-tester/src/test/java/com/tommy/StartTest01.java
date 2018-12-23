package com.tommy;

import com.tommy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>
 *
 * @RunWith
 *  1.使用springboot整合junit4
 * @SpringBootTest
 *  1.當前類為springboot的測試類
 *  2.加載springboot啟動類，啟動springboot
 *
 *  </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class) //
@SpringBootTest(classes = App.class)
public class StartTest01 {

    @Autowired
    UserService userService;
    @Test
    public void findUser() throws Exception {
        userService.findUser();
    }

}