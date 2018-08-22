package tk.tommy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tk.tommy.springboot.service.IndexService;

import javax.naming.NameNotFoundException;
@RestController
public class IndexController {

	@Autowired
	IndexService indexService;

	@GetMapping("/")
	public Object index() {
		return indexService.rd();
	}

	@GetMapping("/systemNews/{custNum}")
	public Object systemNews(@PathVariable String custNum) {
		return indexService.systemNews(custNum);
	}

	@GetMapping("/createDatasource/{custNum}")
	public Object createDatasource(@PathVariable String custNum) {
		indexService.createDatasource(custNum);
		return "ok";
	}

	@GetMapping("/destroyDatasource/{custNum}")
	public Object destroyDatasource(@PathVariable String custNum) throws NameNotFoundException {
		indexService.destroyDatasource(custNum);
		return "ok";
	}

}
