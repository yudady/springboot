package tk.yudady.springboot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@SpringBootApplication
public class Jpa2DbApplication implements CommandLineRunner {

	// @Autowired
	// @Qualifier("myPay")
	// private EntityManagerFactory myPayEntityManagerFactory;
	//
	//
	// @Autowired
	// @Qualifier("myCenter")
	// private EntityManagerFactory myCenterEntityManagerFactory;

	@Autowired
	ConfigurableApplicationContext context;

	private static final Logger log = LoggerFactory.getLogger(Jpa2DbApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Jpa2DbApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
