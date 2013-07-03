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
import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioExtracaoCDRsValidator;
import com.claro.sccweb.decorator.rownum.entity.SccExtracaoCDRsViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioExtracaoCDRsForm;
import com.claro.sccweb.service.SccExtracaoCDRsService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/extracaoCDRs")
public class RelatorioExtracaoCDRsController extends
		BaseOperationController<RelatorioExtracaoCDRsForm> {
	
	private static final String FWD_VIEW_EXCEL ="relatorio_extracaoCDRs_filtro_excel";
	
	@Autowired
	private SccExtracaoCDRsService sccExtracaoCDRsService;
	
	private final RelatorioExtracaoCDRsValidator validator = new RelatorioExtracaoCDRsValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioExtracaoCDRsForm formExtracaoCDRs = (RelatorioExtracaoCDRsForm)form;
		List<SccExtracaoCDRsView> rows = gerarRelatorioExtracaoCDRs(formExtracaoCDRs);
		
		List<SccExtracaoCDRsViewDecorator> decoratorList = new ArrayList<SccExtracaoCDRsViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccExtracaoCDRsViewDecorator decorator = new SccExtracaoCDRsViewDecorator(rows.get(i), i);
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

	
	private List<SccExtracaoCDRsView> gerarRelatorioExtracaoCDRs(RelatorioExtracaoCDRsForm form) throws DAOException, ServiceException {
		return sccExtracaoCDRsService.gerarRelatorioExtracaoCDRs(form.getDtInicial(), form.getDtFinal(), form.getNuMsisdnOrigem());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_extracaoCDRs_filtro";
	}

	@Override
	protected RelatorioExtracaoCDRsForm getForm() {
		
		return new RelatorioExtracaoCDRsForm();
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
	
	

	public SccExtracaoCDRsService getSccExtracaoCDRsService() {
		return sccExtracaoCDRsService;
	}

	public void setSccExtracaoCDRsService(
			SccExtracaoCDRsService sccExtracaoCDRsService) {
		this.sccExtracaoCDRsService = sccExtracaoCDRsService;
	}

}
