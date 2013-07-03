/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.QuestionamentoArquivoForm;

/**
 * @author vagner.souza
 *
 */
public class QuestionamentoArquivoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(QuestionamentoArquivoForm.class);
	}

	@SuppressWarnings("unused")
	@Override
	public void validate(Object obj, Errors errors) {
		//QuestionamentoArquivoForm form = (QuestionamentoArquivoForm) obj;
		
	}

}
