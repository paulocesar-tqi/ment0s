/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RelatorioArquivosFiscaisForm;

/**
 * @author 92031709
 *
 */
public class RelatorioArquivosFiscaisValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioArquivosFiscaisForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioArquivosFiscaisForm form = (RelatorioArquivosFiscaisForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if ((form.getAnoCiclo() == null) || (form.getAnoCiclo().equals("")))
				errors.rejectValue("anoCiclo", "campoObrigatorio", "Campo Obrigatório");
		}
	}
}