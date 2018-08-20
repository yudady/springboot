package tk.tommy.springboot.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tk.tommy.springboot.service.rd.RdService;

@RestController
public class RdController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RdService rdService;

	@RequestMapping(value = "/count")
	public @ResponseBody String count() throws IOException {

		List<Map<String, Object>> notYetNotifyOrder = rdService.queryForListPyNotYetNotifyOrder();
		List<Map<String, Object>> failOrder = rdService.queryForListPyNotifyFailOrder();

		System.out.println(notYetNotifyOrder.size());
		System.out.println(failOrder.size());

		return "notYetNotifyOrder : " + notYetNotifyOrder.size() + " --  failOrder : " + failOrder.size();
	}

	@RequestMapping(value = "/orders")
	public @ResponseBody String orderAll() throws IOException {

		List<Map<String, Object>> orders = rdService.queryForListPyOrder();

		return "orders : " + orders.size();
	}

	@RequestMapping(value = "/rd/ok")
	public @ResponseBody Integer rdOk() throws IOException {

		return rdService.rdOk();
	}
	@RequestMapping(value = "/rd/fail")
	public @ResponseBody Integer rdFail() throws IOException {

		return rdService.rdFail();
	}

}
