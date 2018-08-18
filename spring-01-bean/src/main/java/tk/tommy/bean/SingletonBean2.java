package tk.tommy.bean;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean2 {

	private ApplicationContext applicationContext;
	private PrototypeBean prototypeBean;

	@Lookup
	public PrototypeBean getPrototypeBean(String name, int age) {
		System.out.println("**" + String.valueOf(LocalTime.now()));
		return prototypeBean;
	}

}