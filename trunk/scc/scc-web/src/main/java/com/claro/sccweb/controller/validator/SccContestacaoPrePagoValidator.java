/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccContestacaoPrePagoForm;

/**
 * @author vagner.souza
 *
 */
public class SccContestacaoPrePagoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(SccContestacaoPrePagoForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		SccContestacaoPrePagoForm form = (SccContestacaoPrePagoForm) obj;
		if(StringUtils.isNotEmpty(form.getOperacao()) && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("filtro.mes", form.getFiltro().getMes(), errors);
			ValidatorUtil.verificaCampoObrigatorio("filtro.ano", form.getFiltro().getAno(), errors);
		}
	}

}
