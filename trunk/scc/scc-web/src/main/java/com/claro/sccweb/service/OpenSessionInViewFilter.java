package com.claro.sccweb.service;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class OpenSessionInViewFilter extends org.springframework.orm.hibernate3.support.OpenSessionInViewFilter {
	
	protected void closeSession(Session session, SessionFactory factory){

		session.clear();
		session.flush();
		displayMessage("Finalizando sessao");
	
	super.closeSession(session, factory);
	}
	
	private void displayMessage(String msg){

	}
	
	protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException{
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		session.setFlushMode(FlushMode.AUTO);
		displayMessage("Recuperando uma sessao");
		return session;
	}
	
	protected SessionFactory lookupSessionFactory(){

		displayMessage("Iniciando uma sessao");
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		return (SessionFactory) wac.getBean(getSessionFactoryBeanName(), SessionFactory.class);
	}


}
