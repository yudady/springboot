package com.tommy.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallableController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/callable")
	public Callable<String> executeSlowTask() {
		logger.info("Request received");
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {

				TimeUnit.SECONDS.sleep(5);

				return "Callable BACK";
			}
		};
		logger.info("Servlet thread released");

		return callable;
	}

}