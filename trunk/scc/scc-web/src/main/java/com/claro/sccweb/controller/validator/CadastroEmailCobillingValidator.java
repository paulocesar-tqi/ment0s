/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroEmailCobillingForm;

/**
 * @author 93046251
 *
 */
public class CadastroEmailCobillingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(CadastroEmailCobillingForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {

		CadastroEmailCobillingForm form = (CadastroEmailCobillingForm)_form;
		
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE))){
			ValidatorUtil.verificaCampoObrigatorio("entity.descricao", form.getEntity().getDescricao(), errors);
								
		}

		
	}

}
