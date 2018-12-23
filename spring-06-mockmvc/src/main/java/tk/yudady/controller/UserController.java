package tk.yudady.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.yudady.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("")
	public Object user() {

		System.out.println("UserController.user start --------------");
		userService.findUser();
		System.out.println("UserController.user end--------------");
		return "user";
	}
}
