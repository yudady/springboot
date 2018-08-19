package tk.tommy.springboot.init.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import tk.tommy.springboot.dao.RdRepository;
import tk.tommy.springboot.init.config.DynamicRegisterBean2SpringContainer;
import tk.tommy.springboot.vo.MyPay;

@Service
@Order(1)
public class RunningService implements CommandLineRunner, ApplicationContextAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RdRepository rdRepository;
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	DynamicRegisterBean2SpringContainer dynamicRegisterBean2SpringContainer;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run(String... strings) throws Exception {

		List<Map<String, Object>> maps = rdRepository.queryForListPyCust();

		for (Map<String, Object> map : maps) {

			Object disable_date = map.get("DISABLE_DATE");
			if (Objects.nonNull(disable_date)) {
				continue;
			}

			if ("003".equalsIgnoreCase(map.get("NAME").toString())) {
				continue;
			}

			System.out.println(map);
			String ip = (map.get("API_ADDR").toString()).replace("http://", "").split("/")[0];
			if ("001".equalsIgnoreCase(map.get("NAME").toString())) {
				ip = "35.185.147.170";
			}

			String custName = map.get("NAME").toString();
			String username = map.get("DB_USER").toString();
			String password = map.get("DB_PWD").toString();

			// applicationContext.getBean(MyPay.class, custName, ip, username, password);
			// new DynamicCustContainer(custName, ip, username, password);
//			dynamicRegisterBean2SpringContainer.dynamicCreateBean(MyPay.class,custName);
			Map<String, Object> propertyValue = new HashMap<>();
			propertyValue.put("custName", custName);
			propertyValue.put("username", username);
			propertyValue.put("password", password);
			propertyValue.put("ip", ip);
			dynamicRegisterBean2SpringContainer.dynamicCreateBeanByValue(MyPay.class, propertyValue,custName);
//			MyPay myPay = (MyPay) applicationContext.getBean(MyPay.class,custName);
//			System.out.println(myPay);
		}

	}

}
