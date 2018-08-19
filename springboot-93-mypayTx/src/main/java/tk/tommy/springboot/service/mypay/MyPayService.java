package tk.tommy.springboot.service.mypay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import tk.tommy.springboot.vo.MyPay;

@Component
public class MyPayService {

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
