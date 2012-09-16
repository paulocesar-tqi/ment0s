package com.claro.sccweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.form.SccPaginatedList;
import com.claro.sccweb.security.ldap.LDAPUserDetails;

/**
 * Bean para centralizar todos os dados de sessão.
 * Essa sessão não deve ser confundida com sessão web (HTTPSession). O SessionDataManager possui um 
 * escopo de usuário dentro do SCC Evolution. Essa sessão é compartilhada entre controllers e services. 
 *
 */
public class SessionDataManager {

	public static final String MODULO_POS_PAGO = "POS";
	public static final String MODULO_PRE_PAGO = "PRE";
	
	private String currentViewName;
	
	private SearchResultList searchResultList = new SearchResultList();
	 
	private String currentController;
	
	private String userDisplayName = "SYSMAP BH";
	
	private String userPrincipal = "SYSMAP";
	
	private String mail;
	
	private String module = "POS";
	
	private Map<String, Object> formCache = new HashMap<String, Object>();
	
	/**
	 * Controllers precisam muitas vezes colocar objetos na sessão do usuário para que esses objetos persistam 
	 * entre requests. Os objetos colocados por um controller são indexados pelo nome da classe de controller para que 
	 * a remoção possa ser feita de maneira mais fácil.
	 * A Lista irá conter as keys para os valores de sessão e não os objetos. O index do Map é o nome da classe do controller.
	 */
	private Map<String, List<String>> controllerSessionValues = new HashMap<String, List<String>>();
	
	private String backURL;
	
	private SccPaginatedList currentList;	 
	
	
	public SearchResultList getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(SearchResultList searchResultList) {
		this.searchResultList = searchResultList;
	}

	public String getUserDisplayName() {
		if (this.userDisplayName == null)
		{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof LDAPUserDetails){
			setUserDisplayName(((LDAPUserDetails)principal).getUserDisplayName());
			setUserPrincipal(((LDAPUserDetails) principal).getUsername());
		}
		}
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public boolean isPrePago()
	{
		return this.module.equals("PRE");
	}
	
	
	public boolean isPosPago()
	{
		return this.module.equals("POS");
	}
	
	public List getResultTable()
	{
		return getSearchResultList().getResult();
	}

	public String getUserPrincipal() {
		if (userPrincipal == null)
			{
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			setUserPrincipal(((LDAPUserDetails) principal).getUsername());
			}			
		return userPrincipal;
	}

	public void setUserPrincipal(String userPrincipal) {
		this.userPrincipal = userPrincipal;
	}

	public String getCurrentController() {
		return currentController;
	}

	public void setCurrentController(String currentController) {
		this.currentController = currentController;
	}

	public Map<String, Object> getFormCache() {
		return formCache;
	}

	public void setFormCache(Map<String, Object> formCache) {
		this.formCache = formCache;
	}

	public String getBackURL() {
		return backURL;
	}

	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}

	public SccPaginatedList getCurrentList() {
		return currentList;
	}

	public void setCurrentList(SccPaginatedList currentList) {
		this.currentList = currentList;
	}

	public Map<String, List<String>> getControllerSessionValues() {
		return controllerSessionValues;
	}

	public void setControllerSessionValues(
			Map<String, List<String>> controllerSessionValues) {
		this.controllerSessionValues = controllerSessionValues;
	}

	public String getCurrentViewName() {
		return currentViewName;
	}

	public void setCurrentViewName(String currentViewName) {
		this.currentViewName = currentViewName;
	}
	
	
}
