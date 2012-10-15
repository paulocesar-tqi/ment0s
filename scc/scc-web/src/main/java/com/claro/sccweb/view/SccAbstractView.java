package com.claro.sccweb.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.AbstractView;

import com.claro.scc.spring.ApplicationContextProvider;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.service.ServiceManager;
import com.claro.sccweb.service.SessionDataManager;

public abstract class SccAbstractView extends AbstractView {

	/**
	 * Recupera bean de gest�o de sessio. A gera��o de arquivos Excel est� ligada aos forms e resultados 
	 * de controllers , por isso a necessidade da inje��o desse bean.
	 * @param request
	 * @return
	 */
	 protected SessionDataManager getSessionDatamanager(HttpServletRequest request)
	 {
		 return (SessionDataManager)request.getSession(false).getAttribute("scopedTarget.sessionDataManager");
	 }
	 
	 /**
	  * Para exportar para Excel � , muitas vezes , necess�rio acessar dados extras da base de dados.
	  * Para isso o service manager � injetado na super classe.
	  * @param request
	  * @return
	  */
	 protected ServiceManager getServiceManager(HttpServletRequest request) {
		 return ApplicationContextProvider.getApplicationContext().getBean(ServiceManager.class);
	}

	 /**
	  * Recupera o form a partir do cache de session.
	  * @param clazz
	  */
	 @SuppressWarnings("rawtypes")
	protected Object getFormFromCache(Class clazz,HttpServletRequest request)
	 {
		 return getSessionDatamanager(request).getFormCache().get(clazz.getName()+BaseFormController.CACHE_PREFIX);
	 }
	
}
