package com.tommy.springboot.controller;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tommy.tools.CustDbInfo;
import com.tommy.tools.JdbcLoadDatas;

public class DemoListener implements ServletContextListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("==>DemoListener启动");

		List<CustDbInfo> custsInfos = JdbcLoadDatas.getCustsInfo();
		for (CustDbInfo custsInfo : custsInfos) {
			System.out.println("DB : " + custsInfo.toString());
		}
		System.out.println("==>DemoListener启动end");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}