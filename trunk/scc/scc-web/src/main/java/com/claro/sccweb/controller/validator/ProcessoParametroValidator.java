/**
 * 
 */
package com.claro.sccweb.controller.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.ProcessoParametroForm;

/**
 * @author 93046251
 *
 */
public class ProcessoParametroValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(ProcessoParametroForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ProcessoParametroForm form = (ProcessoParametroForm) obj;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar"))){
			ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
			ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);
			ValidatorUtil.verificaPeriodo("dataFinal", form.getDataInicial(), form.getDataFinal(), errors);	
		}
		
	}

}
