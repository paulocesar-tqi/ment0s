/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RelatorioExtracaoCDRsForm;

/**
 * @author 92031709
 *
 */
public class RelatorioExtracaoCDRsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioExtracaoCDRsForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioExtracaoCDRsForm form = (RelatorioExtracaoCDRsForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if ((form.getDtInicial() == null) || (form.getDtInicial().equals("")))
				errors.rejectValue("dtInicial", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getDtFinal() == null) || (form.getDtFinal().equals("")))
				errors.rejectValue("dtFinal", "campoObrigatorio", "Campo Obrigatório");
		}
	}
}