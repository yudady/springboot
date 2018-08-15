package tk.tommy.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
public class MyBean {

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		System.out.println("****MyBean1.afterPropertiesSet*****");
	}

	@PreDestroy
	public void destroy() throws Exception {
		System.out.println("****MyBean1.destroy*****");
	}
}
