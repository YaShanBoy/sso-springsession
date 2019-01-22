package com.microservice.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ThymeleafController {
	
	@GetMapping({"/","index.html"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/user/index")
	public String userDetails() {
		return "/user/index";
	}
	
	@GetMapping({"/user/login.html","/user/login"})
	public String login() {
		return "/user/login";
	}
	
}
