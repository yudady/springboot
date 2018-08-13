package com.tommy.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/")
	public @ResponseBody String index() {

		String returnMsg = "Hello World";
		logger.debug("HelloController : ", returnMsg);

		return returnMsg;
	}
}
