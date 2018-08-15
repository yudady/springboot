package tk.tommy.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	ApplicationContext applicationContext;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/")
	public @ResponseBody String index() {

		String returnMsg = "Hello World";
		logger.debug(applicationContext + "HelloController : ", returnMsg);
		System.out.println("************applicationContext : " + applicationContext);




		return returnMsg;
	}
}
