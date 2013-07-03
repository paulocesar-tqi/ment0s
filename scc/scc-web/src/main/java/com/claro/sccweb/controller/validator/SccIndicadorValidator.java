/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.sccweb.form.SccIndicadorForm;

/**
 * @author vagner.souza
 *
 */
public class SccIndicadorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(SccIndicadorForm.class);
	}

	@SuppressWarnings("unused")
	@Override
	public void validate(Object obj, Errors errors) {
		
		SccAgingIndicador form = (SccAgingIndicador) obj;

	}

}
