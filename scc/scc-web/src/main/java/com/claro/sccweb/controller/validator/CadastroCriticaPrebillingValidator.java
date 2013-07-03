/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.CadastroCriticaPrebillingForm;

/**
 * @author vagner.souza
 *
 */
public class CadastroCriticaPrebillingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(CadastroCriticaPrebillingForm.class);
	}


	@Override
	public void validate(Object obj, Errors errors) {
		

	}

}
