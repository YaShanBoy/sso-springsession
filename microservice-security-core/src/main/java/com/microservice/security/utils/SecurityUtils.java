package com.microservice.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {
	
	public static User currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object details = authentication.getPrincipal();
		return (User)details;
	}
}
