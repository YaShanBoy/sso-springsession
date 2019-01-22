package com.microservice.demo.feign;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="microservice-security-auth",fallback=UserFeignFallback.class)
public interface UserFeign {
	
	@RequestMapping("/security/auth/user/currentUser")
	public Map<?,?> currentUser();
}

@Component
class UserFeignFallback implements UserFeign{

	@Override
	public Map<?,?> currentUser() {
		
		return new HashMap<String,String>();
	}
	
}