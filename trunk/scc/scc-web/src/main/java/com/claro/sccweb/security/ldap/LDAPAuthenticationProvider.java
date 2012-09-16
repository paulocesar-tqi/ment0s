package com.claro.sccweb.security.ldap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;


public class LDAPAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	/**
	 * Serviço para realizar pesquisas de usuários no LDAP.
	 */
	private UserDetailsService userDetailsService;
	
	/**
	 * Context source para conexão no Active Directory.
	 */
	private ContextSource contextSource;
	
	
	
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "Classe LDAPAuthenticationProvider precisa de um userDetailsService configurado");
		Assert.notNull(this.contextSource, "Classe LDAPAuthenticationProvider precisa de um contextSource configurado");		
	}

	public Authentication authenticate(Authentication authentication)	throws AuthenticationException {
		UserDetails userDetails = getUserDetailsService().loadUserByUsername( authentication.getName());		
		return createSuccessAuthentication(userDetails,authentication,userDetails);
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	private Authentication createSuccessAuthentication(Object principal, Authentication authentication,
            UserDetails user) {        
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal,authentication.getCredentials(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

	public ContextSource getContextSource() {
		return contextSource;
	}

	public void setContextSource(ContextSource contextSource) {
		this.contextSource = contextSource;
	}

	
	
}
