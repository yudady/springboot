package tk.tommy.comtroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public String toString() {
		String rtv = "hello world";
		logger.debug(rtv);
		return rtv;
	}
}
