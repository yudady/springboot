package tk.tommy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

//	@Bean
//	public RestTemplate restTemplate() {
//		RestTemplate restClient = new RestTemplate(
//			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
//
//		// Add one interceptor like in your example, except using anonymous class.
//		restClient.setInterceptors(Collections.singletonList((request, body, execution) -> {
//			return execution.execute(request, body);
//		}));
//
//		return restClient;
//	}
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restClient = new RestTemplate();

		return restClient;
	}
}
