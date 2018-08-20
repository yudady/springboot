package tk.tommy.springboot.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import tk.tommy.springboot.init.MyPayService;
import tk.tommy.springboot.service.mypay.OrderLogService;
import tk.tommy.springboot.vo.MyPay;

@RestController
public class MyPayController {

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderLogService orderLogService;
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MyPayService myPayService;

	@RequestMapping(value = "/")
	public @ResponseBody List<String> index() throws IOException {

		Map<String, MyPay> its = myPayService.getAll();
		// 已支付-未通知 ➞ 已支付-通知失败
		List<String> collect = its.entrySet().stream().parallel().map(pair -> {

			try {
				String custName = pair.getKey();
				return orderLogService.getPyMyPayOrderLog(custName);

			} catch (Throwable e) {
				e.printStackTrace();
				return "";
			}

		}).collect(Collectors.toList());

		return collect;
	}

	@GetMapping(value = "/db/{num}")
	public @ResponseBody String db(@PathVariable String num) throws IOException {

		String custName = "myPay" + num;
		MyPay bean = applicationContext.getBean(custName, MyPay.class);
		orderLogService.testTransactionFail(custName);
		return bean.getCustName() + "  " + bean.getHikariDataSource();
	}

}
