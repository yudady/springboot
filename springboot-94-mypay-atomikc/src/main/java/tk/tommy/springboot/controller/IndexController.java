package tk.tommy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tk.tommy.springboot.service.MyPaySystemNewsService;
import tk.tommy.springboot.service.MypayOrderLogService;
import tk.tommy.springboot.service.RdService;
@RestController
public class IndexController {


	@Autowired
	RdService rdService;


	@Autowired
	MyPaySystemNewsService myPaySystemNewsService;


	@Autowired
	MypayOrderLogService mypayOrderLogService;

	@GetMapping("/")
	public Object index() {
		return rdService.rdUser();
	}

	@GetMapping("/notify/first")
	public Object findFirstNotifyData() {
		return mypayOrderLogService.findLogs();
	}

	@GetMapping("/systemNews/{custNum}")
	public Object systemNews(@PathVariable String custNum) {
		return myPaySystemNewsService.systemNews(custNum);
	}

}
