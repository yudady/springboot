package tk.yudady.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tk.yudady.model.User;
import tk.yudady.service.UserService;
@Controller
@RequestMapping("/users")
public class UserRestController {

	private UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/{id}")
	public @ResponseBody User findById(@PathVariable("id") Long id) {
		return userService.findById(1L);
	}

	// @RequestMapping(method = RequestMethod.POST)
	@PostMapping("/{id}")
	public @ResponseBody User postUser(@PathVariable("id") Long id) {
		return userService.findById(1L);
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody User user) {
		// update by id
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		// delete by id
	}
}