package tk.yudady;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import tk.yudady.mvc.TestServlet;
@SpringBootApplication
public class ServletApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletApp.class);

	@Bean
	public ServletRegistrationBean testServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new TestServlet());
		registration.addUrlMappings("/hello");
		return registration;
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ServletApp.class, args);

	}
}
