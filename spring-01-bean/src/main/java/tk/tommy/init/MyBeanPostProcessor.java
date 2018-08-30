package tk.tommy.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.imageio.stream.IIOByteBuffer;
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	/**
	 * listener 属性设定完毕
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("bean处理器：bean创建之前..");

		return bean;
	}

	/**
	 * listener init 后执行
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("bean处理器：bean创建之后..");
		return bean;
	}

}
