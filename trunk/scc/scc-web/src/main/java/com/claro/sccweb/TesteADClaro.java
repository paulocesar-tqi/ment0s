package com.claro.sccweb;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.ldap.support.LdapUtils;


/**
 * Atributos interessantes:
 * mail
 * displayName
 * physicalDeliveryOfficeName
 * departament
 * memberOf
 *
 */
public class TesteADClaro {

	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		TesteADClaro teste = new TesteADClaro();
		teste.getUserBasicAttributes("93274088", teste.getLdapContext());
	}

	public LdapContext getLdapContext() {
		LdapContext ctx = null;
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "LDAP://MONTEIRO:389");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "93274088" + "@" + "CLARO");
			env.put(Context.SECURITY_CREDENTIALS, "Mu@dibi1902");
			ctx = new InitialLdapContext(env, null);
			System.out.println("Conexão OK !!.");
		} catch (NamingException nex) {
			System.out.println("LDAP Falhou: " + LdapUtils.convertLdapException(nex));
			// nex.printStackTrace();
		}
		return ctx;
	}

	public void getUserBasicAttributes(String username, LdapContext ctx) {
		
		try {

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			//String[] attrIDs = { "mail,name" };
			//constraints.setReturningAttributes(attrIDs);
			// NamingEnumeration answer =
			// ctx.search("CN=Users,DC=claro,DC=com,DC=br", "CN=Domain Guests*",
			// constraints);
			NamingEnumeration answer = ctx.search(
					"DC=claro,DC=com,DC=br", "(&(objectClass=user) (CN="+username+"))",
					constraints);

			if (answer.hasMore()) {				
				SearchResult result = (SearchResult) answer.next();
				System.out.println("RESULT : "+result.getName());
				Attribute email = result.getAttributes().get("mail");		
				System.out.println("Email : "+email.get(0));
				System.out.println("Member of "+result.getAttributes().get("memberOf").size()+" groups");
				for (int g=0;g<result.getAttributes().get("memberOf").size();g++)
					{					
					System.out.println("Grupo "+result.getAttributes().get("memberOf").get(g));					
					}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
