package tk.yudady.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	// @RequestMapping("/")
	@GetMapping("/")
	public @ResponseBody Object index() {
		return "Hello World";
	}
}
