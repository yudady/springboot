package tk.yudady.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/user/me")
	public Object user() {
		Map<String, String> map = new HashMap<>();
		map.put("k", "v");
		return map;
	}
}
