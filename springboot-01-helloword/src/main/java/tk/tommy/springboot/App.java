package tk.tommy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class App extends SpringBootServletInitializer {

	private static ConfigurableApplicationContext context;

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) {
		context = SpringApplication.run(App.class, args);
		// ServiceRouter serviceRouter = context.getBean(ServiceRouter.class);

		// IService service = serviceRouter.getService(1);
		// service.print("hello world");
		// service = serviceRouter.getService(2);
		// service.print("hello world");
	}

}
