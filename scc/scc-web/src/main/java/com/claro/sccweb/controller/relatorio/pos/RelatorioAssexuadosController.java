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
import com.claro.cobillingweb.persistence.view.SccAssexuadosView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioAssexuadosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccAssexuadosViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioAssexuadosForm;
import com.claro.sccweb.service.SccAssexuadosService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/assexuados")
public class RelatorioAssexuadosController extends
		BaseOperationController<RelatorioAssexuadosForm> {
	
	@Autowired
	private SccAssexuadosService sccAssexuadosService;
	
	private final RelatorioAssexuadosValidator validator = new RelatorioAssexuadosValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioAssexuadosForm formAssexuados = (RelatorioAssexuadosForm)form;
		List<SccAssexuadosView> rows = gerarRelatorioAssexuados(formAssexuados);
		
		List<SccAssexuadosViewDecorator> decoratorList = new ArrayList<SccAssexuadosViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccAssexuadosViewDecorator decorator = new SccAssexuadosViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccAssexuadosView> gerarRelatorioAssexuados(RelatorioAssexuadosForm form) throws DAOException, ServiceException {
		return sccAssexuadosService.gerarRelatorioAssexuados(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_assexuados_filtro";
	}

	@Override
	protected RelatorioAssexuadosForm getForm() {
		
		return new RelatorioAssexuadosForm();
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
	
	

	public SccAssexuadosService getSccAssexuadosService() {
		return sccAssexuadosService;
	}

	public void setSccAssexuadosService(
			SccAssexuadosService sccAssexuadosService) {
		this.sccAssexuadosService = sccAssexuadosService;
	}

}
