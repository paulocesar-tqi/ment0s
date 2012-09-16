package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroAliquotaImpostoForm;

public class CadastroAliquotaImpostoValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroAliquotaImpostoForm.class);
	}
	
	public void validate(Object arg, Errors errors) {
		/*
		System.out.println("CHEGOU 1");
		CadastroAliquotaImpostoForm form = (CadastroAliquotaImpostoForm)arg;
		System.out.println("CHEGOU 2");
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)) {
			ValidatorUtil.verificaCampoObrigatorio("entity.id.dtInicioVigencia", form.getEntity().getId().getDtInicioVigencia(), errors);
			//ValidatorUtil.verificaCampoObrigatorio("entity.pcAliquotaImposto", form.getEntity().getPcAliquotaImposto(), errors);
			ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getId().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
		}
		*/
	}
	
}
