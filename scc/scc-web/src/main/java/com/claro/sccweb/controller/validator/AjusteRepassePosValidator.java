package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.repasse.pos.AjusteRepassePosController;
import com.claro.sccweb.form.AjusteRepassePosForm;

public class AjusteRepassePosValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AjusteRepassePosForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		AjusteRepassePosForm form = (AjusteRepassePosForm)arg;
		if (form.getOperacao().equals(AjusteRepassePosController.OPERACAO_AJUSTAR))
			{
			try {
				Double.parseDouble(form.getValor());
			} catch (Exception e)
				{
				errors.rejectValue("valor", "numeroInvalido", "Número Inválido");
				}
			if ((form.getObservacao() == null) || (form.getObservacao().trim().equals("")))
				{
				errors.rejectValue("observacao", "campoObrigatorio", "Campo Obrigatório");
				}
			}
		
	}

	
	
}
