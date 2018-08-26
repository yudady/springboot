package tk.tommy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tk.tommy.springboot.service.MyPaySystemNewsService;
import tk.tommy.springboot.service.MypayOrderService;
import tk.tommy.springboot.service.RdService;
@RestController
public class IndexController {

	@Autowired
	RdService rdService;

	@Autowired
	MyPaySystemNewsService myPaySystemNewsService;

	@Autowired MypayOrderService mypayOrderService;

	@GetMapping("/")
	public Object index() {
		return rdService.rdUser();
	}

	@GetMapping("/order/count")
	public Object findOrderUsedCount() {
		return mypayOrderService.findTodayOrderCount();
	}

	@GetMapping("/mypayOrder/count")
	public Object findMyPayOrderUsedCount() {
		return mypayOrderService.findTodayMyPayOrderCount();
	}

	@GetMapping("/notify/first")
	public Object findFirstNotifyData() {
		return mypayOrderService.findLogs();
	}

	@GetMapping("/notify/{custNum}")
	public Object findFirstNotifyDataByCustNum(@PathVariable String custNum) {
		return mypayOrderService.findLogByCustNum(custNum);
	}

	@GetMapping("/systemNews/{custNum}")
	public Object systemNews(@PathVariable String custNum) {
		return myPaySystemNewsService.systemNews(custNum);
	}

}
