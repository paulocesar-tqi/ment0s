package com.claro.sccweb.security.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.util.Assert;


/**
 * Seguindo o contrato Spring LDAP.
 * 
 * Responsável por criar uma instância do DirContext.
 *
 */
public class LdapSecurityContextSource implements ContextSource ,InitializingBean {

	private String serverURL;
	private String user;
	private String password;
	
	private Hashtable<String,Object> env = new Hashtable<String,Object>();
	
	
	 
	public DirContext getReadOnlyContext() throws org.springframework.ldap.NamingException {
		try {
			return new InitialLdapContext(env, null);
		} catch (NamingException e) {
			throw LdapUtils.convertLdapException(e);
		}		
	}

	 
	public DirContext getReadWriteContext()
			throws org.springframework.ldap.NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public DirContext getContext(String principal, String credentials)
			throws org.springframework.ldap.NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	 
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.serverURL, "A URL do controlador LDAP ser ser fornecida (serverURL)");
		env = new Hashtable<String,Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, getServerURL());
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, getUser());
		env.put(Context.SECURITY_CREDENTIALS, getPassword());
		testaConexao();
	}
	
	/**
	 * Realiza uma conexão inicial para verificar se o AD está acessível ao SCC.
	 * @throws Exception
	 */
	private void testaConexao() throws Exception
	{
		DirContext context = null;
	try {
		context = new InitialLdapContext(env, null);
	} catch (Exception e)
		{
		throw new Exception("Impossível iniciar conexão com Active Directory. Verifique parâmetros do bean contextSource");
		}finally{
			if (context != null){
				context.close();
				context = null;
			}
		}
	}

	
	
		
}
