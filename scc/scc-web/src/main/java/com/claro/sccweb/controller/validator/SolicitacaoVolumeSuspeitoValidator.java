package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SolicitacaoVolumeSuspeitoForm;

public class SolicitacaoVolumeSuspeitoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(SolicitacaoVolumeSuspeitoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		SolicitacaoVolumeSuspeitoForm form = (SolicitacaoVolumeSuspeitoForm)arg;
		ValidatorUtil.verificaPeriodo("dataInicial", form.getDataInicial(), form.getDataFinal(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);
		ValidatorUtil.verificaCampoObrigatorio("valorBruto", form.getValorBruto(), errors);
		ValidatorUtil.verificaCampoObrigatorio("minutos", form.getMinutos(), errors);
		ValidatorUtil.verificaNumeroPositivo("valorBruto", form.getValorBruto(), errors);
		ValidatorUtil.verificaNumeroPositivo("minutos", form.getMinutos(), errors);		
	}

	
	
}
