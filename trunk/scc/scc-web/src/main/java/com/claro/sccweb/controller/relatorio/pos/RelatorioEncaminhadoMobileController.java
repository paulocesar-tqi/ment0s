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
import com.claro.cobillingweb.persistence.view.SccEncaminhadoMobileView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioEncaminhadoMobileValidator;
import com.claro.sccweb.decorator.rownum.entity.SccEncaminhadoMobileViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioEncaminhadoMobileForm;
import com.claro.sccweb.service.SccEncaminhadoMobileService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/encaminhadoMobile")
public class RelatorioEncaminhadoMobileController extends
		BaseOperationController<RelatorioEncaminhadoMobileForm> {
	
	@Autowired
	private SccEncaminhadoMobileService sccEncaminhadoMobileService;
	
	private final RelatorioEncaminhadoMobileValidator validator = new RelatorioEncaminhadoMobileValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioEncaminhadoMobileForm formEncaminhadoMobile = (RelatorioEncaminhadoMobileForm)form;
		List<SccEncaminhadoMobileView> rows = gerarRelatorioEncaminhadoMobile(formEncaminhadoMobile);
		
		List<SccEncaminhadoMobileViewDecorator> decoratorList = new ArrayList<SccEncaminhadoMobileViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccEncaminhadoMobileViewDecorator decorator = new SccEncaminhadoMobileViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccEncaminhadoMobileView> gerarRelatorioEncaminhadoMobile(RelatorioEncaminhadoMobileForm form) throws DAOException, ServiceException {
		return sccEncaminhadoMobileService.gerarRelatorioEncaminhadoMobile(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_encaminhado_mobile_filtro";
	}

	@Override
	protected RelatorioEncaminhadoMobileForm getForm() {
		
		return new RelatorioEncaminhadoMobileForm();
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
	
	

	public SccEncaminhadoMobileService getSccEncaminhadoMobileService() {
		return sccEncaminhadoMobileService;
	}

	public void setSccEncaminhadoMobileService(
			SccEncaminhadoMobileService sccEncaminhadoMobileService) {
		this.sccEncaminhadoMobileService = sccEncaminhadoMobileService;
	}

}
