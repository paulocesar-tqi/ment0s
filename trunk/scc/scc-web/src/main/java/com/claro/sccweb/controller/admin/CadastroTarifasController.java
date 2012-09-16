package com.claro.sccweb.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.CadastroTarifasValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroTarifasForm;

public class CadastroTarifasController extends BaseOperationController<CadastroTarifasForm> {

	private final CadastroTarifasValidator validator = new CadastroTarifasValidator();
	
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		return null;
	}
	
	public ModelAndView selecionar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		return null;
	}
	
	 
	protected String getViewName() {		
		return "tarifas_filtro";
	}	
	
	 
	protected CadastroTarifasForm getForm() {
		return new CadastroTarifasForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	
	
}
