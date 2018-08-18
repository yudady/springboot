package tk.tommy.springboot;

import org.springframework.boot.SpringApplication;

import org.springframework.context.ConfigurableApplicationContext;
import tk.tommy.springboot.importSelector.EnableSomeModule;
import tk.tommy.springboot.vo.AppBean;
@EnableSomeModule("ImportSelector")
public class App {

	public static void main(String[] args) {
		System.setProperty("myProp", "someValue2");
		ConfigurableApplicationContext app = SpringApplication.run(App.class, args);
		String message = app.getBean(AppBean.class).getMessage();
		System.out.println(message);
		System.out.println(message);
		System.out.println(message);
		System.out.println(message);
		System.out.println(message);
		System.out.println(message);
		System.out.println(message);
	}

}