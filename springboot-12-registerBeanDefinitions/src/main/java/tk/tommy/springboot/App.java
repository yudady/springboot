package tk.tommy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.tommy.springboot.registerBeanDefinitions.ConcreteService;
import tk.tommy.springboot.registerBeanDefinitions.EnableThrowable;
@SpringBootApplication

@EnableThrowable(targets = {ConcreteService.class})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

}
