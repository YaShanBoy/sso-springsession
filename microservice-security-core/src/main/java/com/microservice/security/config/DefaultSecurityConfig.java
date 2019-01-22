package com.microservice.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated() // 其他地址的访问均需验证权限
				.and()
				.formLogin()
				//http://localhost:9001
				.loginPage("http://localhost:9001/security/auth/user/login") // 登录页
				.permitAll()
				.and()
				.logout().logoutSuccessUrl("/index.html");
	}
	
}
