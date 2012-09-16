package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroOperadoraForm;

public class CadastroOperadoraValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(CadastroOperadoraForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroOperadoraForm form = (CadastroOperadoraForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.cdEot", form.getEntity().getCdEot(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dsOperadora", form.getEntity().getDsOperadora(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.sgUf", form.getEntity().getSgUf(), errors);
		}	
	}

	
	
}
