/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.RelatorioBatimentoFiscalForm;

/**
 * @author 92031709
 *
 */
public class RelatorioBatimentoFiscalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioBatimentoFiscalForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioBatimentoFiscalForm form = (RelatorioBatimentoFiscalForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if (form.getCdCSP().equals(BasicDAO.NULL_STRING))
				errors.rejectValue("cdCSP", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getMesCiclo() == null) || (form.getMesCiclo().equals("")))
				errors.rejectValue("mesCiclo", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getAnoCiclo() == null) || (form.getAnoCiclo().equals("")))
				errors.rejectValue("anoCiclo", "campoObrigatorio", "Campo Obrigatório");
		}
	}
}