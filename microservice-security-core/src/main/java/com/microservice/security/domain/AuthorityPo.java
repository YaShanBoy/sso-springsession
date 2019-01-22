package com.microservice.security.domain;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthorityPo implements GrantedAuthority{
	
	private static final long serialVersionUID = -2752821251240521847L;

	private String authority;
	
	private String description;

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityPo other = (AuthorityPo) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AuthorityPo [authority=" + authority + ", description=" + description + "]";
	}
	
}
