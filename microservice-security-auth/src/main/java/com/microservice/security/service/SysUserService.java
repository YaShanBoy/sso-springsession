package com.microservice.security.service;

import com.microservice.security.domain.SysUserPo;

public interface SysUserService {
	
	SysUserPo loadUserByUsername(int username);
	
}
