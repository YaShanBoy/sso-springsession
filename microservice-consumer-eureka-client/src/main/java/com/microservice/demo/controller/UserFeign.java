package com.microservice.demo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.demo.domain.UserPo;

@FeignClient(value="user-eureka-client",fallback=UserFeignImpl.class)
public interface UserFeign {
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public UserPo findUserById(@PathVariable("id") Integer id);
	
}

@Component
class UserFeignImpl implements UserFeign{

	@Override
	public UserPo findUserById(Integer id) {
		UserPo user = new UserPo();
		user.setUserId(100);
		user.setUsername("feign");
		user.setPassword("feign");
		return user;
	}
	
}