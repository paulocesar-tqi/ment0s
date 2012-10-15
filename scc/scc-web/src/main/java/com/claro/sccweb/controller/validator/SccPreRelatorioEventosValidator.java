package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccPreRelatorioEventosForm;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioEventosValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {

			return clazz.equals(SccPreRelatorioEventosForm.class);
		}

		@Override
		public void validate(Object _form, Errors errors) {
			
			SccPreRelatorioEventosForm form = (SccPreRelatorioEventosForm)_form;
			if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
			{
				ValidatorUtil.verificaCampoObrigatorio("dtInicioEvento", form.getDtInicioEvento(), errors);
				ValidatorUtil.verificaCampoObrigatorio("dtFimEvento", form.getDtFimEvento(), errors);
				ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicioEvento(), form.getDtFimEvento(), errors);	
			}
			


		}

}
