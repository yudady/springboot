package tk.tommy.springboot.init;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import tk.tommy.springboot.common.MyPay;

@Component
public class MyPayUtils {

	@Autowired
	ApplicationContext applicationContext;

	public PlatformTransactionManager getPlatformTransactionManager(String custName) {
		return applicationContext.getBean(custName, MyPay.class).getPlatformTransactionManager();
	}

	public JdbcTemplate getJdbcTemplateByCustName(String custName) {
		return applicationContext.getBean(custName, MyPay.class).getJdbcTemplate();
	}

	public Map<String, MyPay> getAll() {
		return applicationContext.getBeansOfType(MyPay.class);

	}

}
