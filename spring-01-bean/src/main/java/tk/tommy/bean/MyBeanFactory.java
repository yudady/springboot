package tk.tommy.bean;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.FactoryBean;

public class MyBeanFactory implements FactoryBean<Callable<String>> {


	public void init(){
		System.out.println("MyBeanFactory.init");
	}

	public void destroy(){
		System.out.println("MyBeanFactory.destroy");
	}

	@Override
	public Callable<String> getObject() throws Exception {
		return () -> ("tommy");
	}

	@Override
	public Class<?> getObjectType() {
		return Callable.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
