package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccRelatorioChamadasRefaturamentoForm;

/**
* @author 92038883
*
*/

public class SccRelatorioChamadasRefaturamentoValidator implements Validator {

			@Override
			public boolean supports(Class<?> clazz) {

				return clazz.equals(SccRelatorioChamadasRefaturamentoForm.class);
			}

			@Override
			public void validate(Object _form, Errors errors) {
				
				SccRelatorioChamadasRefaturamentoForm form = (SccRelatorioChamadasRefaturamentoForm)_form;
				if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
				{
					ValidatorUtil.verificaCampoObrigatorio("dtInicioPeriodo", form.getDtInicioPeriodo(), errors);
					ValidatorUtil.verificaCampoObrigatorio("dtFimPeriodo", form.getDtFimPeriodo(), errors);
					ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicioPeriodo(), form.getDtFimPeriodo(), errors);	
				}
				


			}

}
