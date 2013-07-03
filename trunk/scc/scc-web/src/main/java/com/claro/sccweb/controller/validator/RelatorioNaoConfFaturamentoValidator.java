package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.RelatorioNaoConfFaturamentoForm;

public class RelatorioNaoConfFaturamentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RelatorioNaoConfFaturamentoForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		RelatorioNaoConfFaturamentoForm form = (RelatorioNaoConfFaturamentoForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			if(form.getCdCiclo() != null || form.getMmCiclo() > 0 || form.getAaCiclo() != null ){
				ValidatorUtil.verificaCampoObrigatorio("cdCiclo", form.getCdCiclo(), errors);
				ValidatorUtil.verificaCampoObrigatorio("mmCiclo", form.getMmCiclo(), errors);
				ValidatorUtil.verificaCampoObrigatorio("aaCiclo", form.getAaCiclo(), errors);	
			}
		}
	}

}
