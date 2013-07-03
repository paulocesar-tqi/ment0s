package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccPreRelatorioCriticasForm;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioCriticasValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {

			return clazz.equals(SccPreRelatorioCriticasForm.class);
		}

		@Override
		public void validate(Object _form, Errors errors) {
			
			SccPreRelatorioCriticasForm form = (SccPreRelatorioCriticasForm)_form;
			if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
			{
				ValidatorUtil.verificaCampoObrigatorio("dtInicioEvento", form.getDtInicioEvento(), errors);
				ValidatorUtil.verificaCampoObrigatorio("dtFimEvento", form.getDtFimEvento(), errors);
				ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicioEvento(), form.getDtFimEvento(), errors);	
			}
			


		}

}
