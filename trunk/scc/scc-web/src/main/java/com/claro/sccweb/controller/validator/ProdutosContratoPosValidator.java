package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.ProdutosContratoPosForm;

public class ProdutosContratoPosValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ProdutosContratoPosForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		ProdutosContratoPosForm form = (ProdutosContratoPosForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.dtInicioVigencia",  form.getEntity().getDtInicioVigencia(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dtFimVigencia",  form.getEntity().getDtFimVigencia(), errors);
			ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
				
		}		
	}

	
	
}
