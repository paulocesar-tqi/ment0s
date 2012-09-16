package com.claro.sccweb.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.ServiceManager;
import com.claro.sccweb.service.SessionDataManager;

/**
 * Super classe básica para controllers baseados em Tiles View Resolver. 
 *
 */
public abstract class BaseFormController {
	
	protected Logger logger = Logger.getLogger(BaseFormController.class);
	public static final String MODULO_POS_PAGO = "POS";
	public static final String MODULO_PRE_PAGO = "PRE";
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	
	static {
    	Locale locale = new Locale("pt","BR");
    	decimalFormat = NumberFormat.getInstance(locale);
    	decimalFormat.setMinimumFractionDigits(2);
    	decimalFormat.setMaximumFractionDigits(2);
    }
	
	/**
	 * Prefixo a ser usado durante o cache de forms na session do usuário.
	 */
	public static final String CACHE_PREFIX = "Form_";
	
	/**
	 * variável de sessão para indicar a url do botão voltar configurada pelo Controller. 
	 */
	public static final String BACK = "_BACK_BUTTON_";
	
	/**
	 * Variável de sessão para armazenar listas de objetos para a display tag.
	 */
	public static final String DISPLAY_TAG_SPACE_1 = "_DISPLAY_TAG_SPACE_1";
	
	/**
	 * Variável de sessão para armazenar listas de objetos para a display tag.
	 */
	public static final String DISPLAY_TAG_SPACE_2 = "_DISPLAY_TAG_SPACE_2";
	
	/**
	 * Variável de sessão para armazenar listas de objetos para a display tag.
	 */
	public static final String DISPLAY_TAG_SPACE_3 = "_DISPLAY_TAG_SPACE_3";
	
	public static final String DISPLAY_TAG_SPACE_4 = "_DISPLAY_TAG_SPACE_4";
	
	protected final static List<BasicStringItem> tiposBatimento = new ArrayList<BasicStringItem>();
	
	static {
		tiposBatimento.add(new BasicStringItem("FIS","Arquivo Fiscal (FIS)"));
		tiposBatimento.add(new BasicStringItem("QRT","Arquivo de Questionamento de Retorno (QRT)"));
		tiposBatimento.add(new BasicStringItem("REM","Arquivo de Remessa (REM)"));
		tiposBatimento.add(new BasicStringItem("COB","Arquivo de Cobrança (COB)"));
		tiposBatimento.add(new BasicStringItem("RET","Arquivo de Retorno (RET)"));
		tiposBatimento.add(new BasicStringItem("QRM","Arquivo de Questionamento de Remessa (QRM)"));
	}
	
	/**
	 * Formato de data padrão aceito pelo sistema.
	 */
	protected SimpleDateFormat sccDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Formato monetário padrão.
	 */
	DecimalFormat sccCurrencyFormat = new DecimalFormat("#.##");
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sccDateFormat, true));
	}
	
	@Autowired
	private ServiceManager serviceManager;
	
	@Autowired
	private SessionDataManager sessionDataManager;
	
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	public SessionDataManager getSessionDataManager() {
		return sessionDataManager;
	}
	
	public void setSessionDataManager(SessionDataManager sessionDataManager) {
		this.sessionDataManager = sessionDataManager;
	}
	
	/**
	 * Handler padrão para erros na camada de acesso a dados.
	 * @param daoException
	 * @return
	 */
	@ExceptionHandler(DAOException.class)
	public ModelAndView handleDAOException(DAOException daoException,HttpServletRequest request, HttpServletResponse response) {
		logger.error("Erro de DAO. [User : "+getSessionDataManager().getUserPrincipal()+"]", daoException);
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		if (daoException.getCause() != null)
			daoException.getCause().printStackTrace(printWriter);
		else
			daoException.printStackTrace(printWriter);
		request.setAttribute("mensagemErro", "Query : "+daoException.getQuery()+",Message : "+daoException.getMessage());
		request.setAttribute("detalhesErro", writer.toString());
		ModelAndView mv = new ModelAndView();				
		mv.setViewName("error.daoLevel");
		return mv;
	}
	
	/**
	 * Handler padrão para erros na camada de acesso a dados.
	 * @param daoException
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public ModelAndView handleServiceException(ServiceException serviceException,HttpServletRequest request, HttpServletResponse response) {
		logger.error("Erro de service. [User : "+getSessionDataManager().getUserPrincipal()+"]", serviceException);
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		if (serviceException.getCause() != null)
			serviceException.getCause().printStackTrace(printWriter);
		else
			serviceException.printStackTrace(printWriter);
		request.setAttribute("mensagemErro", serviceException.getMessage());
		request.setAttribute("detalhesErro", writer.toString());
		ModelAndView mv = new ModelAndView();				
		mv.setViewName("error.serviceLevel");
		return mv;
	}
	
	/**
	 * Handler padrão para erros na camada de acesso a dados.
	 * @param daoException
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleUnexpectedException(Exception exception,HttpServletRequest request, HttpServletResponse response) {		
		logger.error("Erro inesperado. [User : "+getSessionDataManager().getUserPrincipal()+"]", exception);
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		exception.printStackTrace(printWriter);
		request.setAttribute("mensagemErro", exception.getClass().getName()+" : "+exception.getMessage());
		request.setAttribute("detalhesErro", writer.toString());
		ModelAndView mv = new ModelAndView();				
		mv.setViewName("error.unexpectedLevel");
		return mv;		
	}
	
	public SimpleDateFormat getSccDateFormat() {
		return sccDateFormat;
	}
	
	public DecimalFormat getSccCurrencyFormat() {
		return sccCurrencyFormat;
	}
	
	public String formataData(Date date){
		if (date == null)
			return "-";
		else
			return getSccDateFormat().format(date);
	}
	
	public String formataValorMonetario(Double valor){
		if (valor == null)
			return "-";
		else
			return getSccCurrencyFormat().format(valor);
	}
	
	/**
	 * No sistema atual ,o tipo de arquivo se refere a um campo não normalizado chamado TIPO DE BATIMENTO.
	 * Para evitar uma query pesada na base , esses tipos serão listados diretamente pelo código.  
	 * @return
	 */
	public static List<BasicStringItem> getTiposBatimentoArquivo() {		
		return tiposBatimento;
	}
	
	/**
	 * Limpa a sessão de controller.
	 * @throws Exception
	 */
	protected void reset() throws Exception {
		getSessionDataManager().setSearchResultList(new SearchResultList());
		getSessionDataManager().setFormCache(new HashMap<String, Object>());
	}
	
	protected void debug(String message) {
		logger.debug("["+getSessionDataManager().getUserPrincipal()+"]"+message);
	}
	
	protected void info(String message) {
		logger.info("["+getSessionDataManager().getUserPrincipal()+"]"+message);
	}
	
	protected void error(String message,Throwable th) {
		logger.error("["+getSessionDataManager().getUserPrincipal()+"]"+message,th);
	}
	
	protected List<BasicIntegerItem> _populaComboMeses() {
		List<BasicIntegerItem> lista = new ArrayList<BasicIntegerItem>();
		lista.add(new BasicIntegerItem(1L,"Janeiro"));
		lista.add(new BasicIntegerItem(2L,"Fevereiro"));
		lista.add(new BasicIntegerItem(3L,"Março"));
		lista.add(new BasicIntegerItem(4L,"Abril"));
		lista.add(new BasicIntegerItem(5L,"Maio"));
		lista.add(new BasicIntegerItem(6L,"Junho"));
		lista.add(new BasicIntegerItem(7L,"Julho"));
		lista.add(new BasicIntegerItem(8L,"Agosto"));
		lista.add(new BasicIntegerItem(9L,"Setembro"));
		lista.add(new BasicIntegerItem(10L,"Outubro"));
		lista.add(new BasicIntegerItem(11L,"Novembro"));
		lista.add(new BasicIntegerItem(12L,"Dezembro"));		
		return lista;
	}
	
	protected List<BasicStringItem> _populaUF() {
		List<BasicStringItem> lista = new ArrayList<BasicStringItem>();
		lista.add(new BasicStringItem("AC","AC"));
		lista.add(new BasicStringItem("AL","AL"));
		lista.add(new BasicStringItem("AM","AM"));
		lista.add(new BasicStringItem("AP","AP"));
		lista.add(new BasicStringItem("BA","BA"));
		lista.add(new BasicStringItem("CE","CE"));
		lista.add(new BasicStringItem("DF","DF"));
		lista.add(new BasicStringItem("ES","ES"));
		lista.add(new BasicStringItem("GO","GO"));
		lista.add(new BasicStringItem("MA","MA"));
		lista.add(new BasicStringItem("MG","MG"));
		lista.add(new BasicStringItem("MS","MS"));
		lista.add(new BasicStringItem("MT","MT"));
		lista.add(new BasicStringItem("PA","PA"));
		lista.add(new BasicStringItem("PB","PB"));
		lista.add(new BasicStringItem("PE","PE"));
		lista.add(new BasicStringItem("PI","PI"));
		lista.add(new BasicStringItem("PR","PR"));
		lista.add(new BasicStringItem("RJ","RJ"));
		lista.add(new BasicStringItem("RN","RN"));
		lista.add(new BasicStringItem("RO","RO"));
		lista.add(new BasicStringItem("RR","RR"));
		lista.add(new BasicStringItem("RS","RS"));
		lista.add(new BasicStringItem("SC","SC"));
		lista.add(new BasicStringItem("SE","SE"));
		lista.add(new BasicStringItem("TO","TO"));
		return lista;
	}
	
	/**
	 * Limpa os espaços de memória (session) usados pela displaytag.
	 * @param request
	 */
	protected void cleanDisplayTag(HttpServletRequest request) {
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_1);
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_2);
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_3);
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_4);
	}
	
	/**
	 * Realiza o cache do form do controller na sessão para persistir um snapshot de um dado momento ao longo de requests.
	 * @param clazz
	 */
	protected void cacheMyForm(Class clazz,Object object) {
		getSessionDataManager().getFormCache().put(clazz.getName()+CACHE_PREFIX, object);
	}
	
	/**
	 * Recupera o form a partir do cache de session.
	 * @param clazz
	 */
	protected Object getMyFormFromCache(Class clazz) {
		return getSessionDataManager().getFormCache().get(clazz.getName()+CACHE_PREFIX);
	}
	
	/**
	 * Limpa o cache do controller liberando memória.
	 * @param clazz
	 */
	protected void cleanMyFormCache(Class clazz) {
		getSessionDataManager().getFormCache().remove(clazz.getName()+CACHE_PREFIX);
	}
	
	/**
	 * Aramzena na session e agrupa o valor junto a outros valores do controller.
	 * @param clazz
	 * @param key
	 * @param value
	 * @param request
	 */
	protected void storeInSession(Class clazz,String key,Object value,HttpServletRequest request) {		
		debug("Controller "+clazz.getName()+" armazenando objeto na key "+key);
		if (!getSessionDataManager().getControllerSessionValues().containsKey(clazz.getName()))
			getSessionDataManager().getControllerSessionValues().put(clazz.getName(), new ArrayList<String>());
			request.getSession().setAttribute(key, value);
		if (!getSessionDataManager().getControllerSessionValues().get(clazz.getName()).contains(key))
			getSessionDataManager().getControllerSessionValues().get(clazz.getName()).add(key);
	}
	 
	/**
	 * Limpa completamente a  session do controller. Normalmente essa limpeza ocorre em métodos /new ou na finalização de um processamento.
	 * @param clazz
	 * @param request
	 */
	protected void cleanSession(Class clazz,HttpServletRequest request) {
		logger.debug("Iniciando limpeza da session do controller "+clazz.getName());
		if (getSessionDataManager().getControllerSessionValues().containsKey(clazz.getName())) {
			List<String> keys = getSessionDataManager().getControllerSessionValues().get(clazz.getName());
			for (int i=0;i<keys.size();i++) {				 
				debug("Removendo objeto "+keys.get(i)+" da session");
				request.getSession().removeAttribute(keys.get(i));
			}
		}
	}
	
	/**
	 * Limpa as chaves informadas da session.
	 * @param clazz
	 * @param request
	 * @param keys
	 * @throws ControllerExecutionException
	 */
	protected void cleanSession(Class clazz,HttpServletRequest request,String...keys) throws ControllerExecutionException {
		for (String key : keys) {			 
			debug("Removendo objeto "+key+" da session");
			if (getSessionDataManager().getControllerSessionValues().get(clazz.getName()) != null)
				getSessionDataManager().getControllerSessionValues().get(clazz.getName()).remove(key);
			request.getSession().removeAttribute(key);
		}		 
	}
	
	/**
	 * Encontra o último dia do mês dentro do ano informado.
	 * @param mes
	 * @param ano
	 * @return
	 */
	protected Date calculaDataFinalPeriodo(Long mes,Long ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano.intValue());
		cal.set(Calendar.MONTH,mes.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 *  Retorna um objeto Date apontando para o primeiro dia do período informado.
	 */
	protected Date calculaDataInicialPeriodo(Long mes,Long ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ano.intValue());
		cal.set(Calendar.MONTH,mes.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 * Retorna o número de dias corridos entre duas datas.
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	protected long calculaDiferencaEmDias(Date dataInicial,Date dataFinal) {
		if (dataInicial.equals(dataFinal))
			return 0;
		long dif = dataFinal.getTime() - dataInicial.getTime();
		return (dif/1000)/ 60 / 60 /24;
	}
	
	/**
	 * Retorna um array de dias entre duas datas informadas.
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	protected Date[] listaDiasEntreDatas(Date dataInicial,Date dataFinal) {
		Date[] datas = null;
		if (dataInicial.equals(dataFinal)) {
			datas = new Date[]{dataInicial};				
		} else {
			long qtDias = calculaDiferencaEmDias(dataInicial, dataFinal);
			datas = new Date[(int)qtDias];
			for (int i=0;i<qtDias;i++) {
				Calendar cal  = Calendar.getInstance();
				cal.setTime(dataInicial);
				cal.add(Calendar.DAY_OF_MONTH, i);
				datas[i] = cal.getTime();
			}
		}
		return datas;
	}
	
	protected void printBindingErrors(List<ObjectError> errors) {
		if (errors != null) {
			for (int i=0;i<errors.size();i++) {
				info(errors.get(i).getObjectName()+" : "+errors.get(i).getCode());
			}
		}
	}
	
	public List<SccOperadora> populaHoldingClaro(boolean mandatorrio) throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		if (!mandatorrio) {
			SccOperadora allValues = new SccOperadora();
			allValues.setCdEot(BasicDAO.GET_ALL_STRING);
			allValues.setDsOperadora("Todas");
			comboList.add(0,allValues);
		}			
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaHoldingClaro());
		return comboList;
	}
	
	public List<SccOperadora> populaOperadorasExternas(boolean mandatorio) throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		if (!mandatorio) {
			SccOperadora allValues = new SccOperadora();
			allValues.setCdEot(BasicDAO.GET_ALL_STRING);
			allValues.setDsOperadora("Todas");
			comboList.add(0,allValues);
		}
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
}
