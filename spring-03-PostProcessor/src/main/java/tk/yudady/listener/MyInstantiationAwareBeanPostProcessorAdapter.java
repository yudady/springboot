package tk.yudady.listener;

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
	// public Object getEarlyBeanReference(Object listener, String beanName) throws
	// BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.getEarlyBeanReference");
	// return super.getEarlyBeanReference(listener, beanName);
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
	// public boolean postProcessAfterInstantiation(Object listener, String beanName)
	// throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation");
	// return super.postProcessAfterInstantiation(listener, beanName);
	// }
	//
	// @Override
	// public PropertyValues postProcessPropertyValues(PropertyValues pvs,
	// PropertyDescriptor[] pds, Object listener,
	// String beanName) throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues");
	// return super.postProcessPropertyValues(pvs, pds, listener, beanName);
	// }
	//
	// @Override
	// public Object postProcessBeforeInitialization(Object listener, String beanName)
	// throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInitialization");
	// return super.postProcessBeforeInitialization(listener, beanName);
	// }
	//
	// @Override
	// public Object postProcessAfterInitialization(Object listener, String beanName)
	// throws BeansException {
	// System.out.println("MyInstantiationAwareBeanPostProcessorAdapter.postProcessAfterInitialization");
	// return super.postProcessAfterInitialization(listener, beanName);
	// }
}
