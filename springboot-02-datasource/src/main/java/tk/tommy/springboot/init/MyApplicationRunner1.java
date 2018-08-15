package tk.tommy.springboot.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by pangkunkun on 2017/9/3.
 */
@Component
@Order(2)
public class MyApplicationRunner1 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments var1) throws Exception {
		System.out.println("MyApplicationRunner   @Order(2)");
		System.out.println("MyApplicationRunner   @Order(2)");
		System.out.println("MyApplicationRunner   @Order(2)");
	}

}