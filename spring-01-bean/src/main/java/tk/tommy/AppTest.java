package tk.tommy;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tk.tommy.bean.*;
import tk.tommy.springboot.config.MyConfig;
import tk.tommy.init.MyBeanDefinitionRegistryPostProcessor;
import tk.tommy.init.MyBeanFactoryPostProcessor;
import tk.tommy.init.MyBeanPostProcessor;
public class AppTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(MyConfig.class,
			MyBeanPostProcessor.class, MyBeanFactoryPostProcessor.class,
			MyBeanDefinitionRegistryPostProcessor.class, SingletonBean2.class);
		LOGGER.debug("annotationConfigApplicationContext =>  {} ", anno);
		LOGGER.debug("myBean =>  {} ", anno.getBean("myBean"));
		LOGGER.debug("myBean =>  {} ", anno.getBean(MyBean.class));

		LOGGER.debug("");
		LOGGER.debug("");
		LOGGER.debug("");

		LOGGER.debug("[LOG]myBean2 =>  {} ", anno.getBean(MyBean2.class));
		LOGGER.debug("myBean2 =>  {} ", anno.getBean(MyBean2.class));
		LOGGER.debug("myBean2 =>  {} ", anno.getBean(MyBean2.class));
		LOGGER.debug("myBean2 =>  {} ", anno.getBean(MyBean2.class));

		// MyBeanFactory
		LOGGER.debug("MyBeanFactory =>  {} ", anno.getBean(MyBeanFactory.class));
		LOGGER.debug("MyBeanFactory =>  {} ", anno.getBean(MyBeanFactory.class));
		LOGGER.debug("MyBeanFactory =>  {} ", anno.getBean(MyBeanFactory.class));
		for (int i = 0; i < 10; i++) {
			LOGGER.debug("动态注册 listener =>  {} ", i + " - " + anno.getBean("registryBean" + i));
		}

		LOGGER.debug("SingletonBean.inject.PrototypeBean =>  {} ",
			anno.getBean(SingletonBean.class).getPrototypeBean("t", new Random().nextInt(100) + 100));
		LOGGER.debug("SingletonBean.inject.PrototypeBean =>  {} ",
			anno.getBean(SingletonBean.class).getPrototypeBean("g", new Random().nextInt(100) + 100));
		LOGGER.debug("SingletonBean.inject.PrototypeBean =>  {} ",
			anno.getBean(SingletonBean.class).getPrototypeBean("h", new Random().nextInt(100) + 100));

		LOGGER.debug("-------------------------");

		LOGGER.debug("SingletonBean2.inject.PrototypeBean =>  {} ",
			anno.getBean(SingletonBean2.class).getPrototypeBean("h", new Random().nextInt(100) + 100));
		LOGGER.debug("SingletonBean2.inject.PrototypeBean =>  {} ",
			anno.getBean(SingletonBean2.class).getPrototypeBean("h", new Random().nextInt(100) + 100));

	}
}
