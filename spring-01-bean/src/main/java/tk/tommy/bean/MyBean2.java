package tk.tommy.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBean2 implements InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("****MyBean2.afterPropertiesSet*****");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("****MyBean2.destroy*****");
	}

}
