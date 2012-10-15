/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.SccAcoesCobrancaForm;


/**
 * @author 93046251
 *
 */
public class SccAcoesCobrancaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccAcoesCobrancaForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		SccAcoesCobrancaForm form = (SccAcoesCobrancaForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			
		}
		
	}

}
