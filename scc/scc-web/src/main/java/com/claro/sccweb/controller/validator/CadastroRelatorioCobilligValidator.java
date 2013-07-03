/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroRelatorioCobillingForm;

/**
 * @author 93046251
 *
 */
public class CadastroRelatorioCobilligValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(CadastroRelatorioCobillingForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {

		CadastroRelatorioCobillingForm form = (CadastroRelatorioCobillingForm)_form;
		
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE))){
			ValidatorUtil.verificaCampoObrigatorio("entity.descricao", form.getEntity().getNoRelatorio(), errors);
								
		}

		
	}

}
