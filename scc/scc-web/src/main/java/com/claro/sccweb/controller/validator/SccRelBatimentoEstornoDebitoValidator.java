package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccRelBatimentoEstornoDebitoForm;

/**
 * @author 92038883
 *
 */


public class SccRelBatimentoEstornoDebitoValidator implements Validator {
		
		@Override
		public boolean supports(Class<?> clazz) {

			return clazz.equals(SccRelBatimentoEstornoDebitoForm.class);
		}

		@Override
		public void validate(Object _form, Errors errors) {
			
			SccRelBatimentoEstornoDebitoForm form = (SccRelBatimentoEstornoDebitoForm)_form;
			if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
			{
				ValidatorUtil.verificaCampoObrigatorio("mmCiclo", form.getMmCiclo(), errors);
				ValidatorUtil.verificaCampoObrigatorio("aaCiclo", form.getAaCiclo(), errors);
				/*ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicioCredito(), form.getDtFimCredito(), errors);*/	
			}
			
		}

}
