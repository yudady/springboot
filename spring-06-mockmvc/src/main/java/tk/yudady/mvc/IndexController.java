package tk.yudady.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class IndexController {

	@GetMapping("/")
	public Object index() {
		return "index";
	}
}
