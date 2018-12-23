package com.tommy;

import com.tommy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ComponentScan
 * 更換掃描包
 */
@SpringBootApplication
@ComponentScan("com.tommy")
public class StartTest02App {

    public static void main(String[] args) {
        SpringApplication.run(StartTest02App.class, args);
    }

}