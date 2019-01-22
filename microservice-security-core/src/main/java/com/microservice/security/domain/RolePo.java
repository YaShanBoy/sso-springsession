package com.microservice.security.domain;

import java.util.Set;

import lombok.Data;

@Data
public class RolePo {

	private String role;

	private String description;

	private Set<AuthorityPo> authorities;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePo other = (RolePo) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "RolePo [role=" + role + ", description=" + description + ", authorities=" + authorities + "]";
	}

}
