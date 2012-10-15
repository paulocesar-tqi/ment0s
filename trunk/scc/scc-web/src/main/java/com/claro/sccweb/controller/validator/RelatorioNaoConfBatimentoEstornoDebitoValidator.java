package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.RelatorioNaoConfBatimentoEstornoDebitoForm;

public class RelatorioNaoConfBatimentoEstornoDebitoValidator implements
		Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(RelatorioNaoConfBatimentoEstornoDebitoForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		RelatorioNaoConfBatimentoEstornoDebitoForm form = (RelatorioNaoConfBatimentoEstornoDebitoForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("anoReferencia", form.getAnoReferencia(), errors);
		}


	}

}
