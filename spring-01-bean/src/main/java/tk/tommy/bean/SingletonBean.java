package tk.tommy.bean;

import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SingletonBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public SingletonBean() {
		System.out.println("Singleton instance created");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public PrototypeBean getPrototypeBean(String name, int age) {
		System.out.println("**" + String.valueOf(LocalTime.now()));
		return applicationContext.getBean(PrototypeBean.class, name, age);
	}

}