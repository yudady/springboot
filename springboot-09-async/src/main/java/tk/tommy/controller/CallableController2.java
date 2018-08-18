package tk.tommy.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CallableController2 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/order")
	public Callable<String> order() throws Exception {
		logger.info("主线程开始");

		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				TimeUnit.SECONDS.sleep(5);
				logger.info("副线程返回");
				return "success";
			}
		};
		logger.info("主线程結束");
		return result;
	}

}