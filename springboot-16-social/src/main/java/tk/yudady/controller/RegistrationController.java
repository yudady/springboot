package tk.yudady.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import tk.yudady.service.UserService;
import tk.yudady.vo.User;

import javax.validation.Valid;

@Controller
public class RegistrationController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired UserService userService;

	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mav = new ModelAndView("registration");
		mav.addObject(new User());
		return mav;
	}

	@PostMapping("/registration")
	public String registUser(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.info("registration hasErrors ");
			return "/registration";
		}
		userService.addUser(user);
		return "/login";

	}

}
