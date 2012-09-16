package com.claro.sccweb.security.ldap;

import org.springframework.security.core.GrantedAuthority;

public class LDAPGrantedAuthority implements GrantedAuthority {

	private String role;
	
	public LDAPGrantedAuthority(String role)
	{
		this.role = role;
	}
	
	 
	public String getAuthority() {
		return role;
	}

	
	
}
