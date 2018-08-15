package tk.tommy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tk.tommy.bean.MyBean;
import tk.tommy.bean.MyBean2;
import tk.tommy.bean.MyBeanFactory;
import tk.tommy.config.MyConfig;
import tk.tommy.init.MyBeanPostProcessor;
public class AppTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext anno = new AnnotationConfigApplicationContext(MyConfig.class,
			MyBeanPostProcessor.class);
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

		anno.close();

	}
}
