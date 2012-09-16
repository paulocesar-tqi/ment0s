package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.DemonstrativoSaldoLotesForm;

public class DemonstrativoSaldoLotesValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(DemonstrativoSaldoLotesForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		DemonstrativoSaldoLotesForm form = (DemonstrativoSaldoLotesForm)arg;
		ValidatorUtil.verificaCampoObrigatorio("mesInicio", form.getMesInicio(), errors);
		ValidatorUtil.verificaCampoObrigatorio("anoInicio", form.getAnoInicio(), errors);
		ValidatorUtil.verificaCampoObrigatorio("mesFinal", form.getMesFinal(), errors);
		ValidatorUtil.verificaCampoObrigatorio("anoFinal", form.getAnoFinal(), errors);
		ValidatorUtil.verificaMes("mesInicio", form.getMesInicio(), errors);
		ValidatorUtil.verificaMes("mesFinal", form.getMesFinal(), errors);
		ValidatorUtil.verificaNumeroPositivo("anoInicio", form.getAnoInicio(), errors);
		ValidatorUtil.verificaNumeroPositivo("anoFinal", form.getAnoFinal(), errors);
		if ((form.getAnoFinal() != null) && (form.getAnoInicio() != null))
			{
			if ((form.getAnoFinal().longValue() < form.getAnoInicio().longValue()))
				errors.rejectValue("anoInicio", "periodoInvalido", "Período Inválido");
			}
		
	}

	
	
}
