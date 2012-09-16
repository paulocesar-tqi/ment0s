package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroPenalidadeForm;
import com.claro.sccweb.form.CadastroProdutoPreForm;

public class CadastroProdutoPreValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroProdutoPreForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroProdutoPreForm form = (CadastroProdutoPreForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.noProdutoPrepago", form.getEntity().getNoProdutoPrepago(), errors);			
		}		
	}

	
	
}
