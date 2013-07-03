/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccDisputaForm;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccDisputaForm.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		
		SccDisputaForm form = (SccDisputaForm) obj;
		if(StringUtils.isNotEmpty(form.getOperacao()) && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("filtro.mes", form.getFiltro().getMes(), errors);
			ValidatorUtil.verificaCampoObrigatorio("filtro.ano", form.getFiltro().getAno(), errors);
		}

	}

}
