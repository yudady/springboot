package tk.tommy.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.tommy.mybatis.model.PyCust;
import tk.tommy.springboot.dynamic.DynamicRegisterBean2SpringContainer;
import tk.tommy.springboot.service.mypaycenter.CenterCustService;
import tk.tommy.springboot.service.rd.RdCustService;
import tk.tommy.springboot.test.User;
import tk.tommy.springboot.test.UserService;

@Controller
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CenterCustService centerCustService;

	@Autowired
	private RdCustService rdCustService;

	@Autowired
	DynamicRegisterBean2SpringContainer dynamicRegisterBean2SpringContainer;

	@Autowired
	ApplicationContext applicationContext;

	@RequestMapping(value = "/")
	public @ResponseBody String index() {
		logger.debug("HelloController");

		{
			List<PyCust> pyCusts = centerCustService.selectAll();
			pyCusts.stream().forEach(e -> logger.debug(ToStringBuilder.reflectionToString(e)));
			logger.warn("centerCustService	" + pyCusts.size());

			rdCustService.syncPyCusts(pyCusts);

		}
		logger.debug("--------------");
		logger.debug("--------------");
		logger.debug("--------------");
		logger.debug("--------------");
		logger.debug("--------------");
		{
			List<PyCust> pyCusts = rdCustService.selectAll();
			pyCusts.stream().forEach(e -> logger.debug(ToStringBuilder.reflectionToString(e)));
			logger.warn("rdCustService   " + pyCusts.size());
		}

		return "123123";
	}

	public @ResponseBody String index23423423() {

		{
			Map<String, Object> propertyValue = new HashMap<>();
			propertyValue.put("name", "张三");
			dynamicRegisterBean2SpringContainer.dynamicCreateBeanByValue(User.class, propertyValue,null);
		}

		{

			Map<String, String> propertyReference = new HashMap<>();
			propertyReference.put("user", "user");
			dynamicRegisterBean2SpringContainer.dynamicCreateBeanByReference(UserService.class,
				propertyReference,null);
		}

		System.out.println("index");
		// 获取动态注册的bean.

		System.out.println("user . " + applicationContext.containsBean("user"));
		System.out.println("userService . " + applicationContext.containsBean("userService"));
		User testService = (User) applicationContext.getBean("user");
		testService.print();

		//
		dynamicRegisterBean2SpringContainer.dynamicDeleteBean(User.class,null);

		System.out.println(applicationContext.containsBean("user"));

		return "123";
	}
}
