package com.microservice.security.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.security.domain.RolePo;
import com.microservice.security.domain.SysUserPo;
import com.microservice.security.service.SysUserService;


@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		int userId = Integer.valueOf(username);
		SysUserPo sysUserPo = sysUserService.loadUserByUsername(userId);
		if(null==sysUserPo) {
			throw new UsernameNotFoundException("用户名或密码错误！！！");
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(RolePo role:sysUserPo.getRoles()) {
			authorities.addAll(role.getAuthorities());
		}
		return new User(username, sysUserPo.getPassword(), authorities);
	}

}
