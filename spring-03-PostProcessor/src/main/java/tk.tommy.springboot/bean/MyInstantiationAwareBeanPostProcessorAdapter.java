package tk.tommy.springboot.bean;

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

	// public MyInstantiationAwareBeanPostProcessorAdapter() {
	// super();
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.constructor");
	// }
	//
	// @Override
	// public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws
	// BeansException {
	// //
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.predictBeanType");
	// return super.predictBeanType(beanClass, beanName);
	// }
	//
	// @Nullable
	// @Override
	// public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass,
	// String beanName)
	// throws BeansException {
	//
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.determineCandidateConstructors");
	// return super.determineCandidateConstructors(beanClass, beanName);
	// }
	//
	// @Override
	// public Object getEarlyBeanReference(Object bean, String beanName) throws
	// BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.getEarlyBeanReference");
	// return super.getEarlyBeanReference(bean, beanName);
	// }
	//
	// @Nullable
	// @Override
	// public Object postProcessBeforeInstantiation(Class<?> beanClass, String
	// beanName) throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation");
	// return super.postProcessBeforeInstantiation(beanClass, beanName);
	// }
	//
	// @Override
	// public boolean postProcessAfterInstantiation(Object bean, String beanName)
	// throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation");
	// return super.postProcessAfterInstantiation(bean, beanName);
	// }
	//
	// @Override
	// public PropertyValues postProcessPropertyValues(PropertyValues pvs,
	// PropertyDescriptor[] pds, Object bean,
	// String beanName) throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues");
	// return super.postProcessPropertyValues(pvs, pds, bean, beanName);
	// }
	//
	// @Override
	// public Object postProcessBeforeInitialization(Object bean, String beanName)
	// throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInitialization");
	// return super.postProcessBeforeInitialization(bean, beanName);
	// }
	//
	// @Override
	// public Object postProcessAfterInitialization(Object bean, String beanName)
	// throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessAfterInitialization");
	// return super.postProcessAfterInitialization(bean, beanName);
	// }
}
