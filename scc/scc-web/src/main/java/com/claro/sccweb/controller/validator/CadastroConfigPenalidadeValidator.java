package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroConfigPenalidadeForm;

public class CadastroConfigPenalidadeValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(CadastroConfigPenalidadeForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroConfigPenalidadeForm form = (CadastroConfigPenalidadeForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
//			ValidatorUtil.verificaCampoObrigatorio("entity.cdEotLd", form.getEntity().getId().getCdEotLd(), errors);
//			ValidatorUtil.verificaCampoObrigatorio("entity.cdMotivoRejeicao",  form.getEntity().getId().getCdMotivoRejeicao(), errors);
		}		
	}
	
}
