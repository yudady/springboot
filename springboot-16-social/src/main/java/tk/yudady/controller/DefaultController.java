package tk.yudady.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
@Controller
@Api("返回頁面專用的Controller")
public class DefaultController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping({"/", "/home.html"})
	public String home(Principal principal, Model model) {

		model.addAttribute("username", principal.getName());

		return "/home";
	}

	@GetMapping("/admin/admin.html")
	public String admin() {
		return "/admin/admin";
	}

	@GetMapping("/user/user.html")
	public String user() {
		return "/user/user";
	}

	@GetMapping("/about.html")
	public String about() {
		return "/about";
	}

	@GetMapping("/login.html")
	public String login() {
		return "/login";
	}

	@GetMapping("/403.html")
	public String error403() {
		return "/error/403";
	}

}
