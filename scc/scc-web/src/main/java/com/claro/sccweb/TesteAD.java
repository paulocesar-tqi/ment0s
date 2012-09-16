package com.claro.sccweb;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class TesteAD {

	public static String ldapUri = "ldap://MONTEIRO:389";
	public static String usersContainer = "cn=users";

	public static void main(String args[]) {
		/*if (args.length != 2) {
			System.out.println("Usage: test userName password");
			return;
		}*/
		String username = "93274088@CLARO";
		String password = "Mu@dibi1902";
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapUri);
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			DirContext ctx = new InitialDirContext(env);
			SearchControls ctls = new SearchControls();
			String[] attrIDs = { "cn" };
			ctls.setReturningAttributes(attrIDs);
			ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
			NamingEnumeration answer = ctx.search(usersContainer,
					"(objectclass=group)", ctls);
			while (answer.hasMore()) {
				SearchResult rslt = (SearchResult) answer.next();
				Attributes attrs = rslt.getAttributes();
				System.out.println(attrs.get("cn"));
			}
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
