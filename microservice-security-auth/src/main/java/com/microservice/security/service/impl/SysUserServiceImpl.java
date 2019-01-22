package com.microservice.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.security.dao.mapper.SysUserMapper;
import com.microservice.security.domain.SysUserPo;
import com.microservice.security.service.SysUserService;


@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public SysUserPo loadUserByUsername(int username) {
		return sysUserMapper.loadUserByUsername(username);
	}
	
}
