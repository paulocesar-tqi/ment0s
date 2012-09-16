package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroPacotePreForm;

public class CadastroPacotePreValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroPacotePreForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroPacotePreForm form = (CadastroPacotePreForm) arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE))) {
			ValidatorUtil.verificaCampoObrigatorio("entity.cdPacotePrepago",form.getEntity().getCdPacotePrepago(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.noPacotePrepago",form.getEntity().getNoPacotePrepago(), errors);
		}		
	}
}
