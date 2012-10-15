package com.claro.sccweb.controller.relatorio.pos;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioAlocadasMobileValidator;
import com.claro.sccweb.decorator.rownum.entity.SccAlocadasMobileViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioAlocadasMobileForm;
import com.claro.sccweb.service.SccAlocadasMobileService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/alocadasMobile")
public class RelatorioAlocadasMobileController extends
		BaseOperationController<RelatorioAlocadasMobileForm> {
	
	@Autowired
	private SccAlocadasMobileService sccAlocadasMobileService;
	
	private final RelatorioAlocadasMobileValidator validator = new RelatorioAlocadasMobileValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioAlocadasMobileForm formAlocadasMobile = (RelatorioAlocadasMobileForm)form;
		List<SccAlocadasMobileView> rows = gerarRelatorioAlocadasMobile(formAlocadasMobile);
		
		List<SccAlocadasMobileViewDecorator> decoratorList = new ArrayList<SccAlocadasMobileViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccAlocadasMobileViewDecorator decorator = new SccAlocadasMobileViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccAlocadasMobileView> gerarRelatorioAlocadasMobile(RelatorioAlocadasMobileForm form) throws DAOException, ServiceException {
		return sccAlocadasMobileService.gerarRelatorioAlocadasMobile(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_alocadas_mobile_filtro";
	}

	@Override
	protected RelatorioAlocadasMobileForm getForm() {
		
		return new RelatorioAlocadasMobileForm();
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

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	public SccAlocadasMobileService getSccAlocadasMobileService() {
		return sccAlocadasMobileService;
	}

	public void setSccAlocadasMobileService(
			SccAlocadasMobileService sccAlocadasMobileService) {
		this.sccAlocadasMobileService = sccAlocadasMobileService;
	}
}
