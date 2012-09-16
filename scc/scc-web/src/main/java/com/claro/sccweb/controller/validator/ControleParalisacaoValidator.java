package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.ControleParalisacaoForm;

public class ControleParalisacaoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ControleParalisacaoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		ControleParalisacaoForm form = (ControleParalisacaoForm)arg;
		if (form.getOperacao() != null)
			{
			if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_SEARCH))
				{
				ValidatorUtil.verificaCampoObrigatorio("dtInicial", form.getDtInicial(), errors);
				ValidatorUtil.verificaCampoObrigatorio("dtFinal", form.getDtFinal(), errors);
				ValidatorUtil.verificaPeriodo("dtFinal", form.getDtInicial(), form.getDtFinal(), errors);
				}
			else if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
				{
				ValidatorUtil.verificaCampoObrigatorio("entity.dtInicioParalizacao",form.getEntity().getDtInicioParalizacao(),errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.dtFimParalizacao",form.getEntity().getDtFimParalizacao(),errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.nuBoletimClaro",form.getEntity().getNuBoletimClaro(),errors);
				}
			}
	}

	
	
}
