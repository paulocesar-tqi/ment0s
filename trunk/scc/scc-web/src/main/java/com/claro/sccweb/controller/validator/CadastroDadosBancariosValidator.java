/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.CadastroDadosBancariosForm;

/**
 * @author vagner.souza
 *
 */
public class CadastroDadosBancariosValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(CadastroDadosBancariosForm.class);
	}


	@Override
	public void validate(Object obj, Errors errors) {
		

	}

}
