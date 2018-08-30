package tk.yudady.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;

public class MyBeanPostProcessor implements BeanPostProcessor {

	ApplicationContext applicationContext;

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		System.out.println("MyBeanPostProcessor.setAttribute");
	}

	public MyBeanPostProcessor() {
		System.out.println("MyBeanPostProcessor.constructor");
	}

	@Nullable
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		// System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");

		return bean;
	}

	@Nullable
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
		return bean;
	}

}
