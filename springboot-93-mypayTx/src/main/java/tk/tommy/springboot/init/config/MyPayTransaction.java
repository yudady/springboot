package tk.tommy.springboot.init.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyPayTransaction {

}