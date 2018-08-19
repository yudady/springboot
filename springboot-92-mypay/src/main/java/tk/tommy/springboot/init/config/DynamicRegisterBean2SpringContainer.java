package tk.tommy.springboot.init.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DynamicRegisterBean2SpringContainer {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ApplicationContext applicationContext;

	public static String getCamelCase(Class clazz, String refer) {
		String re = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1)
			+ refer;

		System.out.println(re);
		return re;
	}

	public void dynamicCreateBean(Class clazz, String refer) {
		dynamicCreateBean(clazz, null, null, refer);
	}

	public void dynamicCreateBeanByValue(Class clazz, Map<String, Object> propertyValue, String refer) {
		dynamicCreateBean(clazz, propertyValue, null, refer);
	}

	public void dynamicCreateBeanByReference(Class clazz, Map<String, String> propertyReference,
		String refer) {
		dynamicCreateBean(clazz, null, propertyReference, refer);
	}

	public void dynamicCreateBean(Class clazz, Map<String, Object> propertyValue,
		Map<String, String> propertyReference, String refer) {

		// 获取context.

		// 获取BeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
			.getAutowireCapableBeanFactory();

		// 创建bean信息.
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);

		if (Objects.nonNull(propertyValue)) {
			Iterator<Map.Entry<String, Object>> iterator = propertyValue.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> pair = iterator.next();
				String key = pair.getKey();
				Object value = pair.getValue();

				// addPropertyValue
				beanDefinitionBuilder.addPropertyValue(key, value);
			}

		}

		if (Objects.nonNull(propertyReference)) {
			Iterator<Map.Entry<String, String>> iterator = propertyReference.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> pair = iterator.next();
				String key = pair.getKey();
				String value = pair.getValue();

				// addPropertyReference
				beanDefinitionBuilder.addPropertyReference(key, value);
			}

		}

		// 动态注册bean.
		defaultListableBeanFactory.registerBeanDefinition(getCamelCase(clazz, refer),
			beanDefinitionBuilder.getBeanDefinition());

	}

	public void dynamicDeleteBean(Class clazz, String refer) {
		// 获取context

		// 获取BeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
			.getAutowireCapableBeanFactory();

		// 删除bean.
		defaultListableBeanFactory.removeBeanDefinition(getCamelCase(clazz, refer));
	}

}
