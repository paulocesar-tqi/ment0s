package com.claro.sccweb.controller.relatorio.pos;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioPrestacaoServicoPosValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelPrestacaoServicoPosForm;

//@Controller
//@RequestMapping(value="/user/relatorio/servico/pos")
public class RelatorioPrestacaoServicoPosController extends BaseOperationController<RelPrestacaoServicoPosForm> {
	
	private final RelatorioPrestacaoServicoPosValidator validator = new RelatorioPrestacaoServicoPosValidator();
	public static final String FORM_NAME = "filtro";
	public static final String DT_REF = "REF";
	public static final String DT_CLARO = "PROC";
	
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelPrestacaoServicoPosForm formEvento = (RelPrestacaoServicoPosForm) form;
		return null;

	}
	

	@Override
	protected String getViewName() {
		return "relatorio_prestacao_servico_pos_filtro";
	}

	@Override
	protected RelPrestacaoServicoPosForm getForm() {
		
		return new RelPrestacaoServicoPosForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutos() throws Exception {
		return super.populaProdutos(false);
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadoras() throws Exception {
		return super.populaOperadorasClaro(false);
	}
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		return super.populaOperadorasExternas(false);
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
	
	
	@ModelAttribute("tiposPeriodo")
	public List<BasicStringItem> populaTiposPeriodo() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(DT_REF, "Data de Referência"));
		comboList.add(new BasicStringItem(DT_CLARO, "Data de Processamento Claro"));
		return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}

	


}
