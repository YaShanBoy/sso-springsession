package com.microservice.security.domain;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SysUserPo {
	
	private Integer userId;

	private String nickName;

	private String realName;

	private String idCard;

	private String idCardPhotos;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	private String telephone;

	private String password;

	private String sex;

	private String facePhotos;

	private String attribution1;

	private String attribution2;

	private Set<RolePo> roles;

	@Override
	public String toString() {
		return "SysUserPo [userId=" + userId + ", nickName=" + nickName + ", realName=" + realName + ", idCard="
				+ idCard + ", idCardPhotos=" + idCardPhotos + ", birthday=" + birthday + ", telephone=" + telephone
				+ ", password=" + password + ", sex=" + sex + ", facePhotos=" + facePhotos + ", attribution1="
				+ attribution1 + ", attribution2=" + attribution2 + ", roles=" + roles + "]";
	}

}
