package tk.yudady.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	ApplicationContext applicationContext;

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		System.out.println("MyBeanFactoryPostProcessor.setAttribute");
	}

	public MyBeanFactoryPostProcessor() {
		System.out.println("MyBeanFactoryPostProcessor.constructor");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		System.out.println("postProcessBeanFactory");
	}
}
