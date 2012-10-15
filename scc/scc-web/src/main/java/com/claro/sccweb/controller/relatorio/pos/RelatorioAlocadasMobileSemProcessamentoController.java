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
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioAlocadasMobileSemProcessamentoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccAlocadasMobileSemProcessamentoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioAlocadasMobileSemProcessamentoForm;
import com.claro.sccweb.service.SccAlocadasMobileSemProcessamentoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/alocadasMobileSemProcessamento")
public class RelatorioAlocadasMobileSemProcessamentoController extends
		BaseOperationController<RelatorioAlocadasMobileSemProcessamentoForm> {
	
	@Autowired
	private SccAlocadasMobileSemProcessamentoService sccAlocadasMobileSemProcessamentoService;
	
	private final RelatorioAlocadasMobileSemProcessamentoValidator validator = new RelatorioAlocadasMobileSemProcessamentoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioAlocadasMobileSemProcessamentoForm formAlocadasMobileSemProcessamento = (RelatorioAlocadasMobileSemProcessamentoForm)form;
		List<SccAlocadasMobileSemProcessamentoView> rows = gerarRelatorioAlocadasMobileSemProcessamento(formAlocadasMobileSemProcessamento);
		
		List<SccAlocadasMobileSemProcessamentoViewDecorator> decoratorList = new ArrayList<SccAlocadasMobileSemProcessamentoViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccAlocadasMobileSemProcessamentoViewDecorator decorator = new SccAlocadasMobileSemProcessamentoViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccAlocadasMobileSemProcessamentoView> gerarRelatorioAlocadasMobileSemProcessamento(RelatorioAlocadasMobileSemProcessamentoForm form) throws DAOException, ServiceException {
		return sccAlocadasMobileSemProcessamentoService.gerarRelatorioAlocadasMobileSemProcessamento(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_alocadas_mobile_sem_processamento_filtro";
	}

	@Override
	protected RelatorioAlocadasMobileSemProcessamentoForm getForm() {
		
		return new RelatorioAlocadasMobileSemProcessamentoForm();
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
	
	public SccAlocadasMobileSemProcessamentoService getSccAlocadasMobileSemProcessamentoService() {
		return sccAlocadasMobileSemProcessamentoService;
	}

	public void setSccAlocadasMobileSemProcessamentoService(
			SccAlocadasMobileSemProcessamentoService sccAlocadasMobileSemProcessamentoService) {
		this.sccAlocadasMobileSemProcessamentoService = sccAlocadasMobileSemProcessamentoService;
	}
}
