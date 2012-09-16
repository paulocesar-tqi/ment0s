/**
 * 
 */
package com.claro.sccweb.controller.relatorio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.sccweb.controller.validator.ProcessoParametroValidator;
import com.claro.sccweb.decorator.rownum.entity.SccParamProcessoDecorator;
import com.claro.sccweb.dto.SccParamProcessoDto;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ProcessoParametroForm;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.ServiceManager;
import com.claro.sccweb.service.SessionDataManager;

/**
 * @author 93046251
 *
 */
//@Controller
//@RequestMapping(value="/user/relatorio/processo/parametro")
public class ProcessoParametroController {

	public static final String DISPLAY_TAG_SPACE_1 = "_DISPLAY_TAG_SPACE_1";
	
	public static final String DISPLAY_TAG_SPACE_2 = "_DISPLAY_TAG_SPACE_2";
	
	public static final String DISPLAY_TAG_SPACE_3 = "_DISPLAY_TAG_SPACE_3";
	
	public static final String DISPLAY_TAG_SPACE_4 = "_DISPLAY_TAG_SPACE_4";
	
	public static final String CACHE_PREFIX = "Form_";
	
	public static final String FORM_NAME = "filtro";

	
	private final ProcessoParametroValidator validator = new ProcessoParametroValidator();
	
	@Autowired
	private ServiceManager serviceManager;
	
	//@Autowired
	private SessionDataManager sessionDataManager;

	
	protected void cleanDisplayTag(HttpServletRequest request) {
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_1);
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_2);
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_3);
		request.getSession().removeAttribute(DISPLAY_TAG_SPACE_4);
	}

	@SuppressWarnings("rawtypes")
	protected void cleanMyFormCache(Class clazz) {
		getSessionDataManager().getFormCache().remove(clazz.getName()+CACHE_PREFIX);
	}
	
	@SuppressWarnings("rawtypes")
	protected void cleanSession(Class clazz,HttpServletRequest request) {
		//logger.debug("Iniciando limpeza da session do controller "+clazz.getName());
		if (getSessionDataManager().getControllerSessionValues().containsKey(clazz.getName())) {
			List<String> keys = getSessionDataManager().getControllerSessionValues().get(clazz.getName());
			for (int i=0;i<keys.size();i++) {				 
			//	debug("Removendo objeto "+keys.get(i)+" da session");
				request.getSession().removeAttribute(keys.get(i));
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected void storeInSession(Class clazz,String key,Object value,HttpServletRequest request) {		
		//debug("Controller "+clazz.getName()+" armazenando objeto na key "+key);
		if (!getSessionDataManager().getControllerSessionValues().containsKey(clazz.getName()))
			getSessionDataManager().getControllerSessionValues().put(clazz.getName(), new ArrayList<String>());
			request.getSession().setAttribute(key, value);
		if (!getSessionDataManager().getControllerSessionValues().get(clazz.getName()).contains(key))
			getSessionDataManager().getControllerSessionValues().get(clazz.getName()).add(key);
	}
	
	
	
	
	@RequestMapping(value="/new") 
	public  ModelAndView  iniciar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		cleanDisplayTag(request);
		cleanMyFormCache(getClass());
		cleanSession(getClass(), request);
		mav.addObject(FORM_NAME,getForm());
		return mav;	 
	}
	
	
	@ModelAttribute("tiposProcessos")
	public List<SccParamProcessoDto> populaComboTipoProcesso() throws Exception{
		
		List<SccParamProcessoDto> comboList = (List<SccParamProcessoDto>) converterEntity();
		SccParamProcessoDto allvalues = new SccParamProcessoDto();
		
		allvalues.setCdProcesso("Todos");
		allvalues.setNmParametro(BasicDAO.GET_ALL_STRING);
		comboList.add(allvalues);
		return comboList;
	}
	
	private List<SccParamProcessoDto> converterEntity() throws DAOException, ServiceException{
		List<String> lstStr = getServiceManager().getSccParamProcessoService().listarProcessos();
		List<SccParamProcessoDto> rows = new ArrayList<SccParamProcessoDto>();
		if (lstStr != null && !lstStr.isEmpty()){
			for (String cdProcesso : lstStr) {
				SccParamProcessoDto dto = new SccParamProcessoDto();
				dto.setCdProcesso(cdProcesso);
				rows.add(dto);
			}
		}
		return rows;
	}
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		ProcessoParametroForm formProcesso = (ProcessoParametroForm) form;
		
		List<SccParamProcessoDto> rows = buscarProcessoParametros(formProcesso);
		
		List<SccParamProcessoDecorator> decoratorList = montarRelatorio(rows);
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	private List<SccParamProcessoDecorator> montarRelatorio(List<SccParamProcessoDto> rows){
		
		List<SccParamProcessoDecorator> decoratorList = new ArrayList<SccParamProcessoDecorator>(rows.size());
		if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.size(); i++) {
				SccParamProcessoDecorator decorator = new SccParamProcessoDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
		}
		return decoratorList;
	}
	
	
	private List<SccParamProcessoDto> buscarProcessoParametros(ProcessoParametroForm form) throws DAOException, ServiceException {
		
		List<SccParamProcesso> list = getServiceManager().getSccParamProcessoService().pesquisaProcessoParametros(form.getTipoProcesso(), form.getDataInicial(), form.getDataFinal());
		List<SccParamProcessoDto> listDto = new ArrayList<SccParamProcessoDto>();
		for (int i = 0; i < list.size(); i++) {
			SccParamProcessoDto dto = new SccParamProcessoDto();
			dto.setCdProcesso(list.get(i).getId().getCdProcesso());
			dto.setNmParametro(list.get(i).getId().getNmParametro());
			dto.setCdTipoParametro(list.get(i).getCdTipoParametro());
			dto.setDtAtualizacao(list.get(i).getDtAtualizacao());
			dto.setDtCriacao(list.get(i).getDtCriacao());
			dto.setTxValorParametro(list.get(i).getTxValorParametro());
			dto.setCdUsuarioManut(list.get(i).getCdUsuarioManut());
			listDto.add(dto);
		}
		return listDto; 
	}

	
	protected String getViewName() {
		
		return "relatorio_processo_parametro";
	}

	
	protected Validator getValidator() {

		return this.validator;
	}
	
	
	protected ProcessoParametroForm getForm() {
		
		return new ProcessoParametroForm();
	}

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
	
	



}
