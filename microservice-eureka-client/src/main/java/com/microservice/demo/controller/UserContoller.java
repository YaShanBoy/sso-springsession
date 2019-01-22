package com.microservice.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.domain.UserPo;
import com.microservice.demo.feign.UserFeign;

@RestController
@RequestMapping("user")
public class UserContoller {
	
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("{id}")
	@PreAuthorize("hasAuthority('coding')")
	public UserPo findUserById(@PathVariable("id")Integer id) {
		UserPo user = new UserPo();
		user.setUserId(id);
		user.setUsername("zuifeng");
		user.setPassword("123456");
		return user;
	}
	
	@RequestMapping("currentUser")
	public Map<?,?> testFeign() {
		
		return userFeign.currentUser();
	}
}
