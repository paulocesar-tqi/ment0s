package com.claro.sccweb.security.weblogic;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.claro.sccweb.reflect.DuckType;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

	private static Class<?> callbackHandlerClass;
	private static Class<?> authenticationClass;
	private static Class<?> groupClass;
	
	static {
		try {
			callbackHandlerClass = Class.forName("weblogic.security.SimpleCallbackHandler");
			authenticationClass = Class.forName("weblogic.security.services.Authentication");
			groupClass = Class.forName("weblogic.security.spi.WLSGroup");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failded initializing weblogic authentication manager", e);
		}
	}
	
	private Map<String, String> roles = new HashMap<String, String>();
	
	private String securityRealm;
	
	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());
		
		Subject subject = null;
		try {
			DuckType handler = new DuckType(callbackHandlerClass, username, password);
			DuckType auth = new DuckType(authenticationClass, new Object[]{});
			if ( null == securityRealm )
				subject = (Subject)auth.call("login", handler.getContext());
			else 
				subject = (Subject)auth.call("login", securityRealm, handler.getContext());
		} catch (Exception e) {
			throw new AuthenticationServiceException("Weblogic authentication failed", e);
		}

		if ( null == subject ) throw new UsernameNotFoundException(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		Set<Principal> groups = subject.getPrincipals((Class<Principal>)groupClass);

		for ( Principal group : groups ) 
			if ( roles.containsKey(group.getName()) ) authorities.add(new SimpleGrantedAuthority(roles.get(group.getName()))); 
		
		if ( authorities.isEmpty() )
			throw new InsufficientAuthenticationException(String.format("No accepted role found for user %s", username));

		return new UsernamePasswordAuthenticationToken(new User(username, password, authorities), subject.getPublicCredentials(), authorities);
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}
	
	public Map<String, String> getRoles() {
		return roles;
	}

	public String getSecurityRealm() {
		return securityRealm;
	}

	public void setSecurityRealm(String securityRealm) {
		this.securityRealm = securityRealm;
	}

}
