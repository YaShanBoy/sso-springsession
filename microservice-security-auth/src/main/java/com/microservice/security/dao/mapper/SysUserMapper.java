package com.microservice.security.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.microservice.security.domain.SysUserPo;

@Mapper
public interface SysUserMapper {

	public SysUserPo loadUserByUsername(int username);
	
}
