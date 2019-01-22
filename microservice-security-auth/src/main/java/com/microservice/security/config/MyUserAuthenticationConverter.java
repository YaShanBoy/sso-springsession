package com.microservice.security.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MyUserAuthenticationConverter extends DefaultUserAuthenticationConverter{
	
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		Object principal = authentication.getPrincipal();
		if(principal instanceof User) {
			log.info("userdetails：是用user类");
		}
		response.put("company", "达摩学院");
		response.put(USERNAME, authentication.getName());
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}
	
}
