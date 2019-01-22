package com.microservice.security.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.security.utils.SecurityUtils;

@RestController
@RequestMapping("user")
public class SysUserController {
	
	@RequestMapping("currentUser")
	public User currentUser() {
		
		return SecurityUtils.currentUser();
	}
}
