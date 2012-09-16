package com.claro.sccweb.security.ldap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.Assert;

/**
 * Popula as autoridades do usuário de acordo com o(s) grupo(s) que esse usuário faz parte. 
 *
 */
public class LDAPGroupAuthoritiesPopulator  implements AuthoritiesPopulator,InitializingBean {

	/**
	 * Nome do atributo do LDAP que comtém a lista de grupos a que o usuário pertence.
	 */
	private String groupAttributeName = "memberOf";
	
	
	/**
	 * Mapeamento do tipo <GRUPO,ROLE> baseado na informações obtidas no LDAP.
	 */
	private Map<String, String> groupsMap;
	
	public Collection<GrantedAuthority> getGrantedAuthorities(SearchResult result) throws Exception {		
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		if ((result.getAttributes().get(groupAttributeName) == null) || (result.getAttributes().get(groupAttributeName).size() == 0))
			{
			return AuthorityUtils.NO_AUTHORITIES;
			}	
			 
		for (int g=0;g<result.getAttributes().get(groupAttributeName).size();g++)
			{
			String currGroup = (String)result.getAttributes().get(groupAttributeName).get(g);
			currGroup = new DistinguishedName(currGroup).removeLast().getValue();
			if (groupsMap.containsKey(currGroup))
				{
				authorityList.add(new LDAPGrantedAuthority(groupsMap.get(currGroup)));
				}
			}
		return authorityList;
	}

	public String getGroupAttributeName() {
		return groupAttributeName;
	}

	public void setGroupAttributeName(String groupAttributeName) {
		this.groupAttributeName = groupAttributeName;
	}

	 
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.groupAttributeName,"Classe CustomLDAPAuthoritiesPopulator precisa da propriedade groupAttributeName");
		Assert.hasText(this.groupAttributeName,"Propriedade groupAttributeName da classe CustomLDAPAuthoritiesPopulator não pode estar vazia");
		Assert.notNull(this.groupsMap, "Classe CustomLDAPAuthoritiesPopulator precisa da propriedade groupsMap configurada");
		
	}

	public Map<String, String> getGroupsMap() {
		return groupsMap;
	}

	public void setGroupsMap(Map<String, String> groupsMap) {
		this.groupsMap = groupsMap;
	}
	
	

}
