package tk.tommy.bean;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class MyBeanFactory implements FactoryBean<Callable<String>> {

	@Nullable
	@Override
	public Callable<String> getObject() throws Exception {
		return () -> ("tommy");
	}

	@Nullable
	@Override
	public Class<?> getObjectType() {
		return Callable.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
