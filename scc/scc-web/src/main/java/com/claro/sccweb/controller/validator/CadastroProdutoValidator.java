package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroProdutoForm;

public class CadastroProdutoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroProdutoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroProdutoForm form = (CadastroProdutoForm) arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE))) {
			ValidatorUtil.verificaCampoObrigatorio("entity.noProdutoCobilling",form.getEntity().getNoProdutoCobilling(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dtInicioVigencia",form.getEntity().getDtInicioVigencia(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dtFimVigencia", form.getEntity().getDtFimVigencia(), errors);
			ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
		}
	}

}
