package tk.tommy.springboot.init;

import java.util.stream.Stream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import tk.tommy.springboot.common.MyPay;
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		System.out.println("**********************************");
		System.out.println("*******开始初始化客户DB***********");
		System.out.println("**********************************");
		ConfigurableApplicationContext applicationContext = event.getApplicationContext();
		Stream.of(applicationContext.getBeanNamesForType(MyPay.class)).parallel().forEach(e -> {
			Object appBean = applicationContext.getBean(e);
			MyPay bean = (MyPay) appBean;
			System.out.println(e + " " + bean);
			bean.initMethod();
		});;
	}
}