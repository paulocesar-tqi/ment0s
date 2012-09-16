package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroContratoPosForm;

public class CadastroContratoPosValidator implements Validator{

	 
	public boolean supports(Class<?> clazz) {	
		return clazz.equals(CadastroContratoPosForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroContratoPosForm form = (CadastroContratoPosForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
			{
			ValidatorUtil.verificaCampoObrigatorio("entity.dsContratoCobilling", form.getEntity().getDsContratoCobilling(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dtInicioVigencia",  form.getEntity().getDtInicioVigencia(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dtFimVigencia",  form.getEntity().getDtFimVigencia(), errors);
			ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
			}		
	}

	
	
}
