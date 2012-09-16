package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroPreDominioForm;

public class CadastroPreDominioValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroPreDominioForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroPreDominioForm form = (CadastroPreDominioForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE))
			{
			ValidatorUtil.verificaCampoObrigatorio("entity.dsDominio", form.getEntity().getDsDominio(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.id.cdDominio", form.getEntity().getId().getCdDominio(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.id.tpDominio", form.getEntity().getId().getTpDominio(), errors);
			}
	}

	
	
}
