package com.claro.sccweb.security.ldap;

import java.util.Collection;

import javax.naming.directory.SearchResult;

import org.springframework.security.core.GrantedAuthority;

/**
 * Ap�s pesquisa de atributos do usu�rio � montada a lista de autoridades com o uso dessa interface.
 *
 */
public interface AuthoritiesPopulator {

	/**
	 * Carrega lista de autoridades (roles) do usu�rio a partir de uma pesquisa no LDAP.
	 * @param result Resulta da pesquisa de usu�rio no LDAP.
	 * @return Lista de autoridades do usu�rio.
	 * @throws Exception
	 */
	public Collection<GrantedAuthority> getGrantedAuthorities(SearchResult result) throws Exception;
	
}
