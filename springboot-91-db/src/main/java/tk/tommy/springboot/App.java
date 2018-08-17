package tk.tommy.springboot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {



	public static void main(String[] args) throws IOException {



		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);



	}

}
