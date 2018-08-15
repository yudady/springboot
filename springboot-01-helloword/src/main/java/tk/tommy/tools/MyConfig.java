package tk.tommy.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
public class MyConfig {

	@Bean
	@Scope("prototype")
	public CustDbInfo custDbInfo() {
		return new CustDbInfo();
	}
}
