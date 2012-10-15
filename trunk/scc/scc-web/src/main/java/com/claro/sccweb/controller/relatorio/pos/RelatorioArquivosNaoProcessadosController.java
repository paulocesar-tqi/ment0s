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
import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioArquivosNaoProcessadosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivosNaoProcessadosViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioArquivosNaoProcessadosForm;
import com.claro.sccweb.service.SccArquivosNaoProcessadosService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/arquivosNaoProcessados")
public class RelatorioArquivosNaoProcessadosController extends
		BaseOperationController<RelatorioArquivosNaoProcessadosForm> {
	
	@Autowired
	private SccArquivosNaoProcessadosService sccArquivosNaoProcessadosService;
	
	private final RelatorioArquivosNaoProcessadosValidator validator = new RelatorioArquivosNaoProcessadosValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioArquivosNaoProcessadosForm formArquivosNaoProcessados = (RelatorioArquivosNaoProcessadosForm)form;
		List<SccArquivosNaoProcessadosView> rows = gerarRelatorioArquivosNaoProcessados(formArquivosNaoProcessados);
		
		List<SccArquivosNaoProcessadosViewDecorator> decoratorList = new ArrayList<SccArquivosNaoProcessadosViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccArquivosNaoProcessadosViewDecorator decorator = new SccArquivosNaoProcessadosViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccArquivosNaoProcessadosView> gerarRelatorioArquivosNaoProcessados(RelatorioArquivosNaoProcessadosForm form) throws DAOException, ServiceException {
		return sccArquivosNaoProcessadosService.gerarRelatorioArquivosNaoProcessados(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_arquivos_nao_processados_filtro";
	}

	@Override
	protected RelatorioArquivosNaoProcessadosForm getForm() {
		
		return new RelatorioArquivosNaoProcessadosForm();
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
	
	

	public SccArquivosNaoProcessadosService getSccArquivosNaoProcessadosService() {
		return sccArquivosNaoProcessadosService;
	}

	public void setSccArquivosNaoProcessadosService(
			SccArquivosNaoProcessadosService sccArquivosNaoProcessadosService) {
		this.sccArquivosNaoProcessadosService = sccArquivosNaoProcessadosService;
	}

}
