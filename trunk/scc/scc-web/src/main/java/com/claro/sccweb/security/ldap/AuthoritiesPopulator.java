package com.claro.sccweb.security.ldap;

import java.util.Collection;

import javax.naming.directory.SearchResult;

import org.springframework.security.core.GrantedAuthority;

/**
 * Após pesquisa de atributos do usuário é montada a lista de autoridades com o uso dessa interface.
 *
 */
public interface AuthoritiesPopulator {

	/**
	 * Carrega lista de autoridades (roles) do usuário a partir de uma pesquisa no LDAP.
	 * @param result Resulta da pesquisa de usuário no LDAP.
	 * @return Lista de autoridades do usuário.
	 * @throws Exception
	 */
	public Collection<GrantedAuthority> getGrantedAuthorities(SearchResult result) throws Exception;
	
}
