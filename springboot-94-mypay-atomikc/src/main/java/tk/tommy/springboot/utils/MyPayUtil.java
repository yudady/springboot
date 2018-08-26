package tk.tommy.springboot.utils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.atomikos.util.IntraVmObjectRegistry;

@Component
public class MyPayUtil {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${oracle.sid}")
	private String sid;

	@Autowired
	ApplicationContext applicationContext;

	Set<String> custNumSet = Collections.synchronizedSortedSet(new TreeSet<>());

	public JdbcTemplate getJdbcTemplate(String custNum) {
		return applicationContext.getBean("jdbcTemplate" + custNum, JdbcTemplate.class);
	}

	public void createDatasourceJdbcTemplate(Map<String, Object> map) {
		String custNum = "" + map.get("ID");
		try {
			applicationContext.getBean("jdbcTemplate" + custNum, JdbcTemplate.class);
			applicationContext.getBean("dataSource" + custNum, DataSource.class);
		} catch (NoSuchBeanDefinitionException e) {
			String ip = (map.get("API_ADDR").toString()).replace("http://", "").split("/")[0];
			if ("001".equalsIgnoreCase(map.get("NAME").toString())) {
				ip = "35.185.147.170";
			}

			if ("zvo11g01".equalsIgnoreCase(sid)) {
				ip = "192.168.0.23";
			}

			String user = map.get("DB_USER").toString().trim();
			String password = map.get("DB_PWD").toString().trim();

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
			//
			beanDefinitionBuilder.addPropertyValue("testQuery", "SELECT sysdate from dual");
			beanDefinitionBuilder.addPropertyValue("maxPoolSize", "10");
			beanDefinitionBuilder.addPropertyValue("minPoolSize", "5");

			/*
			 * 獲取池在空時阻塞等待連接在池中可用的最長時間（以秒為單位）。
			 */
			// beanDefinitionBuilder.addPropertyValue("borrowConnectionTimeout", "2");
			beanDefinitionBuilder.addPropertyValue("maintenanceInterval", "10");
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

			custNumSet.add(custNum);
		}

	}

	public void destroyDatasource(String custNum) throws NameNotFoundException {
		try {
			applicationContext.getBean("jdbcTemplate" + custNum, JdbcTemplate.class);
			IntraVmObjectRegistry.removeResource("dataSource" + custNum);
			BeanDefinitionRegistry factory = (BeanDefinitionRegistry) applicationContext
				.getAutowireCapableBeanFactory();
			factory.removeBeanDefinition("jdbcTemplate" + custNum);
			factory.removeBeanDefinition("dataSource" + custNum);

			custNumSet.remove(custNum);
		} catch (Throwable e) {
			if (e instanceof NoSuchBeanDefinitionException) {
				logger.warn(custNum + "。不存在，无法销毁！！！");
			} else {
				e.printStackTrace();
				throw e;
			}

		}

	}

	public Set<String> getCustsNum() {
		return custNumSet;
	}

}
