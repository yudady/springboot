package tk.tommy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.tommy.springboot.service.IndexService;
@RestController
public class IndexController {

	@Autowired
	IndexService indexService;

	@GetMapping("/")
	public Object index() {
		indexService.test01("998");
		return "hello";
	}
}
