/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.RelatorioConfirmacaoRepasseForm;

/**
 * @author 92031709
 *
 */
public class RelatorioConfirmacaoRepasseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioConfirmacaoRepasseForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioConfirmacaoRepasseForm form = (RelatorioConfirmacaoRepasseForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if (form.getCdEOTLD().equals(BasicDAO.NULL_STRING))
				errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");
			if (form.getCdStatusRepasse().equals(BasicDAO.NULL_STRING))
				errors.rejectValue("cdStatusRepasse", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getMesRepasse() == null) || (form.getMesRepasse().equals("")))
				errors.rejectValue("mesRepasse", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getAnoRepasse() == null) || (form.getAnoRepasse().equals("")))
				errors.rejectValue("anoRepasse", "campoObrigatorio", "Campo Obrigatório");
		}
	}
}