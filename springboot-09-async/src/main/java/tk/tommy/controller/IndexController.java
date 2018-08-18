package tk.tommy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "首頁tags", value = "首頁value", description = "首頁-Controller")
public class IndexController {

	@GetMapping("/")
	@ApiOperation("首頁1-executeSlowTask")
	public String executeSlowTask() {

		return "tommy";
	}
	@GetMapping("/t2")
	@ApiOperation("首頁2-executeSlowTask")
	public String executeSlowTask1() {

		return "tommy";
	}
	@GetMapping("/t3")
	@ApiOperation("首頁3-executeSlowTask")
	public String executeSlowTask2() {

		return "tommy";
	}
}
