/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.SccPosRelatorioDisputaDetalheForm;

/**
 * @author 92031709
 *
 */
public class SccPosRelatorioDisputaDetalheValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(SccPosRelatorioDisputaDetalheForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		SccPosRelatorioDisputaDetalheForm form = (SccPosRelatorioDisputaDetalheForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if (form.getCdEotLD().equals(BasicDAO.NULL_STRING))
				errors.rejectValue("cdEotLD", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getMesRelatorio() == null) || (form.getMesRelatorio().equals("")))
				errors.rejectValue("mesRelatorio", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals("")))
				errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigatório");
		}
	}
}