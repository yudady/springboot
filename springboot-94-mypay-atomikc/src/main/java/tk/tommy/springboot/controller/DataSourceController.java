package tk.tommy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tk.tommy.springboot.service.DataSourceService;

import javax.naming.NameNotFoundException;
@RestController
public class DataSourceController {

	@Autowired
	DataSourceService dataSourceService;

	@GetMapping("/createDatasource/{custNum}")
	public Object createDatasource(@PathVariable String custNum) {
		dataSourceService.createDatasource(custNum);
		return "ok";
	}

	@GetMapping("/destroyDatasource/{custNum}")
	public Object destroyDatasource(@PathVariable String custNum) throws NameNotFoundException {
		dataSourceService.destroyDatasource(custNum);
		return "ok";
	}
}
