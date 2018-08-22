package com.tommy.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.tommy.springboot.common.DynamicRegisterBean2SpringContainer;

@Service
@Order(1)
public class RunningService implements CommandLineRunner {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DynamicRegisterBean2SpringContainer dynamicRegisterBean2SpringContainer;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void run(String... strings) throws Exception {

		Map<String, Object> propertyValue = new HashMap<>();
		propertyValue.put("uniqueResourceName", "dataSourceC");
		propertyValue.put("driverClassName", "oracle.jdbc.OracleDriver");
		propertyValue.put("url", "jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
		propertyValue.put("user", "mypaycenter");
		propertyValue.put("password", "myPay4Zv");
		dynamicRegisterBean2SpringContainer.dynamicCreateBeanByValue(AtomikosNonXADataSourceBean.class,
			propertyValue, "dataSourceC");




		// 创建bean信息.
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
			.genericBeanDefinition(AtomikosNonXADataSourceBean.class);
		beanDefinitionBuilder.addPropertyValue("uniqueResourceName", "dataSourceC");
		beanDefinitionBuilder.addPropertyValue("driverClassName", "oracle.jdbc.OracleDriver");
		beanDefinitionBuilder.addPropertyValue("url", "jdbc:oracle:thin:@192.168.0.23:1521:zvo11g01");
		beanDefinitionBuilder.addPropertyValue("user", "mypaycenter");
		beanDefinitionBuilder.addPropertyValue("password", "myPay4Zv");
		beanDefinitionBuilder.addPropertyValue("poolSize", "5");
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		// 获取BeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
			.getAutowireCapableBeanFactory();

		defaultListableBeanFactory.registerBeanDefinition("dataSourceC", beanDefinition);

		System.out.println("-----------------");


		// 创建bean信息.
		BeanDefinitionBuilder beanDefinitionBuilder2 = BeanDefinitionBuilder
			.genericBeanDefinition(JdbcTemplate.class);
		beanDefinitionBuilder2.addConstructorArgReference("dataSourceC");
		AbstractBeanDefinition beanDefinition2 = beanDefinitionBuilder2.getBeanDefinition();


		defaultListableBeanFactory.registerBeanDefinition("jdbcTemplateC", beanDefinition2);

	}

}
