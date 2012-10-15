package com.claro.sccweb.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.claro.scc.spring.ApplicationContextProvider;
import com.claro.sccweb.excel.style.CurrencyStyle;
import com.claro.sccweb.excel.style.DefaultStyle;
import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.excel.style.IntegerStyle;
import com.claro.sccweb.service.ServiceManager;
import com.claro.sccweb.service.SessionDataManager;

public abstract class BasicExcelHandler extends AbstractExcelView  {

	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	protected ExcelStyle style = new DefaultStyle();
	protected ExcelStyle currencyStyle = new CurrencyStyle();
	protected ExcelStyle integerStyle = new IntegerStyle();
	
	protected Logger logger = Logger.getLogger(BaseFormController.class);
	
	
	/**
	 * Recupera bean de gestao de sessio. A geracaoo de arquivos Excel esta ligada aos forms e resultados 
	 * de controllers , por isso a necessidade da injecao desse bean.
	 * @param request
	 * @return
	 */
	 protected SessionDataManager getSessionDatamanager(HttpServletRequest request)
	 {
		 return (SessionDataManager)request.getSession(false).getAttribute("scopedTarget.sessionDataManager");
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
	 
	 protected Object getFromSession(String name,HttpServletRequest request)
	 {
		 return request.getSession().getAttribute(name);
	 }


	 /**
	  * Para exportar para Excel, muitas vezes , necessario acessar dados extras da base de dados.
	  * Para isso o service manager e injetado na super classe.
	  * @param request
	  * @return
	  */
	public ServiceManager getServiceManager(HttpServletRequest request) {
		return ApplicationContextProvider.getApplicationContext().getBean(ServiceManager.class);
	}

	protected void debug(String message)
	{
		logger.debug(message);
	}
	
	protected void info(String message)
	{
		logger.info(message);
	}
	
	protected void error(String message,Throwable th)
	{
		logger.error(message,th);
	}
	
	
	
}
