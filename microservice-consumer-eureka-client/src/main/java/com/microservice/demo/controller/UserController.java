package com.microservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.demo.domain.UserPo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("{id}")
	//@HystrixCommand(fallbackMethod="findUserByIdFallback")
	public UserPo findUserById(@PathVariable("id") Integer id) {
		//UserPo user = restTemplate.getForObject("http://user-eureka-client/user/"+id, UserPo.class);
		UserPo user = userFeign.findUserById(id);
		return user;
	}
	/*
	public UserPo findUserByIdFallback(Integer id) {
		//UserPo user = restTemplate.getForObject("http://user-eureka-client/user/"+id, UserPo.class);
		//UserPo user = userFeign.findUserById(id);
		UserPo user = new UserPo();
		user.setUserId(1);
		user.setUsername("hystrix");
		user.setPassword("hystrix");
		return user;
	}
	*/
}
