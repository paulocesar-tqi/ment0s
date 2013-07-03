/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.SccRequisicaoForm;

/**
 * @author vagner.souza
 *
 */
public class SccRequisicaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccRequisicaoForm.class);
	}

	
	@Override
	public void validate(Object obj, Errors errors) {
		//QuestionamentoArquivoForm form = (QuestionamentoArquivoForm) obj;
		
	}

}
