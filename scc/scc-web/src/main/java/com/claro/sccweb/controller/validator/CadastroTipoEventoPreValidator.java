package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroTipoEventoPreForm;

public class CadastroTipoEventoPreValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroTipoEventoPreForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		
		CadastroTipoEventoPreForm form = (CadastroTipoEventoPreForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.id.cdTipoEvento", form.getEntity().getId().getCdTipoEvento(), errors);
		}	
	}

	
	
}
