/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.RelatorioBatimentoArquivosForm;

/**
 * @author 93046251
 *
 */
public class RelatorioBatimentoArquivosValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioBatimentoArquivosForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioBatimentoArquivosForm form = (RelatorioBatimentoArquivosForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			ValidatorUtil.verificaCampoObrigatorio("dtInicioBatimento", form.getDtInicioBatimento(), errors);
			ValidatorUtil.verificaCampoObrigatorio("dtFimBatimento", form.getDtFimBatimento(), errors);
			ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicioBatimento(), form.getDtFimBatimento(), errors);	
		}
		


	}

}
