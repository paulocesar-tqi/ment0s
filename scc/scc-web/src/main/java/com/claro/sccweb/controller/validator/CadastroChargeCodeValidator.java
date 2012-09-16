package com.claro.sccweb.controller.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroChargeCodeForm;
import com.claro.sccweb.form.CadastroPenalidadeForm;

public class CadastroChargeCodeValidator implements Validator {

	Logger logger = Logger.getLogger(CadastroChargeCodeValidator.class);
	
	
	
	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(CadastroChargeCodeForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroChargeCodeForm form = (CadastroChargeCodeForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.cdCharge", form.getEntity().getCdCharge(), errors);			
		}
	}

	
}
