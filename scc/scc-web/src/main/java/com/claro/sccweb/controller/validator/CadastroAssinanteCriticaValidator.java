package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroAssinanteCriticaForm;

public class CadastroAssinanteCriticaValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroAssinanteCriticaForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroAssinanteCriticaForm form = (CadastroAssinanteCriticaForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
		ValidatorUtil.verificaCampoObrigatorio("entity.id.cdArea", form.getEntity().getId().getCdArea(), errors);
		ValidatorUtil.verificaCampoObrigatorio("entity.id.nuPrefixo",  form.getEntity().getId().getNuPrefixo(), errors);
		ValidatorUtil.verificaCampoObrigatorio("entity.id.nuSufixoInicial",  form.getEntity().getId().getNuSufixoInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("entity.id.nuSufixoFinal",  form.getEntity().getId().getNuSufixoFinal(), errors);
		ValidatorUtil.verificaCampoObrigatorio("entity.dtInicioVigencia",  form.getEntity().getDtInicioVigencia(), errors);
		ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
		}
		
	}

	
	
}
