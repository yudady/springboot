package com.tommy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tommy.springboot.service.RdService;

@RestController
public class IndexController {

	@Autowired
	RdService rdService;

	@GetMapping("/")
	public Object index() {
		rdService.test();
		return "hello";
	}
}
