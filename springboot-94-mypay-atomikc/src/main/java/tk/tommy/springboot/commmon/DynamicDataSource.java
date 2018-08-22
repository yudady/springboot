package tk.tommy.springboot.commmon;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;

import tk.tommy.springboot.service.CustService;

@Component
public class DynamicDataSource implements CommandLineRunner {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${oracle.sid}")
	private String sid;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	CustService custService;

	@Override
	public void run(String... strings) throws Exception {

		List<Map<String, Object>> custs = custService.findCusts();
		for (Map<String, Object> map : custs) {
			String id = "" + map.get("ID");
			Object disable_date = map.get("DISABLE_DATE");
			if (Objects.nonNull(disable_date)) {
				continue;
			}

			if ("003".equalsIgnoreCase(map.get("NAME").toString())) {
				continue;
			}
			String ip = (map.get("API_ADDR").toString()).replace("http://", "").split("/")[0];
			if ("001".equalsIgnoreCase(map.get("NAME").toString())) {
				ip = "35.185.147.170";
			}

			if ("zvo11g01".equalsIgnoreCase(sid)) {
				ip = "192.168.0.23";
			}

			String custName = "" + map.get("ID");
			String user = map.get("DB_USER").toString().trim();
			String password = map.get("DB_PWD").toString().trim();

			createJdbcTemplate(custName, ip, sid, user, password);
		}

	}

	public void createJdbcTemplate(String custNum, String ip, String sid, String user, String password) {
		String url = "jdbc:oracle:thin:@" + ip + ":1521:" + sid;

		String datasourceName = "dataSource" + custNum;
		String jdbcTemplateName = "jdbcTemplate" + custNum;

		// 获取BeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
			.getAutowireCapableBeanFactory();

		// ------------------------------------------------

		// 创建bean信息.
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
			.genericBeanDefinition(AtomikosNonXADataSourceBean.class);
		beanDefinitionBuilder.addPropertyValue("uniqueResourceName", datasourceName);
		beanDefinitionBuilder.addPropertyValue("driverClassName", "oracle.jdbc.OracleDriver");
		beanDefinitionBuilder.addPropertyValue("url", url);
		beanDefinitionBuilder.addPropertyValue("user", user);
		beanDefinitionBuilder.addPropertyValue("password", password);
		beanDefinitionBuilder.addPropertyValue("poolSize", "5");
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		defaultListableBeanFactory.registerBeanDefinition(datasourceName, beanDefinition);

		// 创建bean信息.
		BeanDefinitionBuilder beanDefinitionBuilder2 = BeanDefinitionBuilder
			.genericBeanDefinition(JdbcTemplate.class);
		beanDefinitionBuilder2.addConstructorArgReference(datasourceName);
		AbstractBeanDefinition beanDefinition2 = beanDefinitionBuilder2.getBeanDefinition();

		defaultListableBeanFactory.registerBeanDefinition(jdbcTemplateName, beanDefinition2);

		logger.info("create datasource => {} ", datasourceName);
		logger.info("create jdbcTemplate => {} ", jdbcTemplateName);
	}

}
