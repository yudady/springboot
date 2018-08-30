package tk.yudady.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/")
	public Object index() {
		return "hello";
	}

	@GetMapping("/err")
	public Object err() {
		int a = 1 / 0;
		return "err";
	}
}
