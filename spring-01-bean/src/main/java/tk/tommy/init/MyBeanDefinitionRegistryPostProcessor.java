package tk.tommy.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import tk.tommy.bean.RegistryBean;

/**
 * BeanFactoryPostProcessor 子类
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	/**
	 * 功能：注册bean到spring容器
	 * 
	 * @param registry
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println(
			"动态注册 bean ， spring 容器初始化完后 postProcessBeanDefinitionRegistry.postProcessBeanDefinitionRegistry : "
				+ registry);

		for (int i = 0; i < 10; i++) {

			BeanDefinitionBuilder bdf = BeanDefinitionBuilder.rootBeanDefinition(RegistryBean.class);
			bdf.addPropertyValue("name", "name-tommy" + i);

			registry.registerBeanDefinition("registryBean" + i, bdf.getBeanDefinition());
		}

	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println(
			"spring 容器初始化完后 postProcessBeanDefinitionRegistry.postProcessBeanFactory : " + beanFactory);
	}
}
