package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.repasse.pre.CreditosController;
import com.claro.sccweb.form.CreditosForm;

public class CreditosValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CreditosForm.class);

	}

	 
	public void validate(Object arg, Errors errors) {
		CreditosForm form = (CreditosForm)arg;
		if ((form.getOperacao().equals(CreditosController.OPERACAO_PESQUISAR)) || (form.getOperacao().equals(CreditosController.OPERACAO_EXCEL)))
			{
			if (form.getDataInicial() == null)
				errors.rejectValue("dataInicial", "campoObrigatorio", "Campo Obrigatório");
			if (form.getDataFinal() == null)
				errors.rejectValue("dataFinal", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getDataInicial() != null) && (form.getDataFinal() != null))
				{
				if (!form.getDataFinal().after(form.getDataInicial()))
					{
					errors.rejectValue("dataFinal", "campoObrigatorio", "Data final deve ser maior que inicial");
					}
				}
			}
	}

	
	
}
