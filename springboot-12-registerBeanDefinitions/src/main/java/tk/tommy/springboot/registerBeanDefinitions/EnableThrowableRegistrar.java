package tk.tommy.springboot.registerBeanDefinitions;

import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportBeanDefinitionRegistrar 。找到资讯 然后手动注册
 *
 * ImportBeanDefinitionRegistrar 自己手动注册bean
 */
public class EnableThrowableRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("JAVA_HOME:" + environment.getProperty("JAVA_HOME"));
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
		BeanDefinitionRegistry registry) {
		Map<String, Object> annotationAttributes = importingClassMetadata
			.getAnnotationAttributes(EnableThrowable.class.getCanonicalName());
		Class<?>[] targets = (Class<?>[]) annotationAttributes.get("targets");
		if (null != targets && targets.length > 0) {
			for (Class<?> target : targets) {
				BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(target)
					.getBeanDefinition();
				registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
			}
		}
	}
}