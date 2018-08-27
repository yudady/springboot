package tk.yudady.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class DefaultController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping({"/", "/home"})
	public String home(Principal principal,Model model) {

		model.addAttribute("username" , principal.getName());

		return "/home";
	}

	@PostMapping({"/redirectHome"})
	public String redirectHome() {
		logger.info("登入成功");
		return "redirect:/home";

	}

	@GetMapping("/admin/admin")
	public String admin() {
		return "/admin/admin";
	}

	@GetMapping("/user/user")
	public String user() {
		return "/user/user";
	}

	@GetMapping("/about")
	public String about() {
		return "/about";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
