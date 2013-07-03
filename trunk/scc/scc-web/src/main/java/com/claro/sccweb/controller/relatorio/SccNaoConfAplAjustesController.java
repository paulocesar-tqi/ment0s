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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.AjustesView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioNaoConfAjustesValidator;
import com.claro.sccweb.decorator.view.AjustesViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioNaoConfAjustesForm;
import com.claro.sccweb.service.SccNaoConfAplAjustesService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/naoconf/ajustes")
public class SccNaoConfAplAjustesController extends
BaseOperationController<RelatorioNaoConfAjustesForm> {
	
	
	public static final String FWD_VIEW_EXCEL ="relatorio_nao_conformidade_ajustes_excel";
	
	@Autowired
	private SccNaoConfAplAjustesService sccNaoConfAplAjustesService;
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioNaoConfAjustesForm form = (RelatorioNaoConfAjustesForm)_form;
		SccFiltro filtro = getFiltro(form);
		
		List<AjustesView> rows = gerarRelatorioNaoConfAjustes(filtro);
		
		List<AjustesViewDecorator> decoratorList = new ArrayList<AjustesViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			AjustesViewDecorator decorator = new AjustesViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
		
	}
	
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_EXCEL);
		return mav;
	}

	private SccFiltro getFiltro(RelatorioNaoConfAjustesForm form){
		
		SccFiltro filtro = new SccFiltro();
		filtro.setOperadoraClaro(form.getCdEOTClaro());
		filtro.setOperadoraExterna(form.getCdEOTLD());
		filtro.setDataInicialPeriodo(DateUtils.lowDateTime(form.getDtInicio()));
		filtro.setDataFinalPeriodo(DateUtils.highDateTime(form.getDtFim()));
		return filtro;
		
	}
	
	private List<AjustesView> gerarRelatorioNaoConfAjustes(SccFiltro filtro) throws DAOException, ServiceException {
		
		return sccNaoConfAplAjustesService.gerarRelatorioNaoConfAplAjuste(filtro);
	}
	
	private final RelatorioNaoConfAjustesValidator validator = new RelatorioNaoConfAjustesValidator();

	@Override
	protected String getViewName() {
		
		return "relatorio_nao_conformidade_ajustes";
	}

	@Override
	protected RelatorioNaoConfAjustesForm getForm() {
		
		return new RelatorioNaoConfAjustesForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}

	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}
	

	public SccNaoConfAplAjustesService getSccNaoConfAplAjustesService() {
		return sccNaoConfAplAjustesService;
	}

	public void setSccNaoConfAplAjustesService(
			SccNaoConfAplAjustesService sccNaoConfAplAjustesService) {
		this.sccNaoConfAplAjustesService = sccNaoConfAplAjustesService;
	}
	
	
	
	
	


}
