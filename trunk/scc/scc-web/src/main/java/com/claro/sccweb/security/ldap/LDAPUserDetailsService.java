package com.claro.sccweb.security.ldap;


import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;


/**
 * Seguindo contrato do namespace <authentication-manager> do Spring Security 3.1 .
 * Responsável por pesquisar os dados do usuário no Active Directory da Claro.
 *
 */
public class LDAPUserDetailsService implements UserDetailsService,InitializingBean{

	private AuthoritiesPopulator authoritiesPopulator;
	
	/**
	 * Context source para conexão no Active Directory.
	 */
	private ContextSource contextSource;
	
	/**
	 * The constraints for LDAP search.
	 */
	SearchControls constraints = new SearchControls();
	
	
	/**
	 * Expressão de busca para usuário. O valor a ser pesqusia será colocado no lugar de #user#
	 * Exemplo : (&(objectClass=user) (CN={#user#}))
	 */
	private String userSearch = "(&(objectClass=user) (CN=#user#))";
	
	/**
	 *  Contexto a partir do qual será feita a pesquisa pelo usuário no AD.
	 */
	private String contextToSearch;
	
	 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DirContext ctx = null;
		String toSearch = null;
		try {
			ctx = contextSource.getReadOnlyContext();
			toSearch = this.userSearch.replaceAll("#user#", username);
			NamingEnumeration answer = ctx.search(contextToSearch, toSearch,constraints);
			if (answer.hasMoreElements()){				
				SearchResult result = (SearchResult) answer.next();				
				LDAPUserDetails userDetails = new LDAPUserDetails();
				userDetails.setUserName(username);
				userDetails.setUserDisplayName((String)result.getAttributes().get("displayName").get(0));
				userDetails.setAuthorities(getAuthoritiesPopulator().getGrantedAuthorities(result));				
				return userDetails;
			}else{
				throw new UsernameNotFoundException("User "+username+" não encontrado.");
				}
		} catch (NamingException nem)
			{
			throw new UsernameNotFoundException("Erro ao acessar contexto", nem);
			}
		catch (Exception e)
			{
			if (e instanceof UsernameNotFoundException)
				throw (UsernameNotFoundException)e;
			else
				throw new UsernameNotFoundException("Erro inesperado", e);
			}finally{
				toSearch = null;
				if (ctx != null){
					try {
						ctx.close();
						ctx = null;
					} catch (javax.naming.NamingException e) {}
				}
			}		
	}


	public ContextSource getContextSource() {
		return contextSource;
	}


	public void setContextSource(ContextSource contextSource) {
		this.contextSource = contextSource;
	}


	 
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.userSearch, "Expressao para pesquisa do usuario deve ser informada para classe LDAPUserDetailsService (userSearch)");
		Assert.notNull(this.contextSource, "Classe LDAPUserDetailsService precisa de um contextSource");
		Assert.notNull(this.contextToSearch, "Classe LDAPUserDetailsService precisa do parametro contextToSearch");
		Assert.notNull(this.authoritiesPopulator, "Bean authoritiesPopulator não pode ser nulo em LDAPUserDetailsService");		
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	}


	public String getUserSearch() {
		return userSearch;
	}


	public void setUserSearch(String userSearch) {
		this.userSearch = userSearch;
	}


	public String getContextToSearch() {
		return contextToSearch;
	}


	public void setContextToSearch(String contextToSearch) {
		this.contextToSearch = contextToSearch;
	}


	public AuthoritiesPopulator getAuthoritiesPopulator() {
		return authoritiesPopulator;
	}


	public void setAuthoritiesPopulator(AuthoritiesPopulator authoritiesPopulator) {
		this.authoritiesPopulator = authoritiesPopulator;
	}

	
	
}
