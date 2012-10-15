/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.RelatorioNaoConfAjustesForm;

/**
 * @author 93046251
 *
 */
public class RelatorioNaoConfAjustesValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(RelatorioNaoConfAjustesForm.class); 
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		RelatorioNaoConfAjustesForm form = (RelatorioNaoConfAjustesForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("dtInicio", form.getDtInicio(), errors);
			ValidatorUtil.verificaCampoObrigatorio("dtFim", form.getDtFim(), errors);
			ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicio(), form.getDtFim(), errors);	
			
		}
		

	}

}
