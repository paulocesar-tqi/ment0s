package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroMapaStatusForm;

public class CadastroMapaStatusValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroMapaStatusForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroMapaStatusForm form = (CadastroMapaStatusForm)arg;
		if (form.getOperacao() != null)
		{
			if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
			{				
				ValidatorUtil.verificaCampoObrigatorio("entity.id.dtInicioVigencia", form.getEntity().getId().getDtInicioVigencia(), errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.id.cdStatusDe", form.getEntity().getId().getCdStatusDe(), errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.id.cdStatusPara", form.getEntity().getId().getCdStatusPara(), errors);
				ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getId().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
			}
		}
		
	}

	
	
}
