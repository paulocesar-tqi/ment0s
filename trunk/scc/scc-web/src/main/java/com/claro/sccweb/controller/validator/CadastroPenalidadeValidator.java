package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroContratoPosForm;
import com.claro.sccweb.form.CadastroPenalidadeForm;

public class CadastroPenalidadeValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(CadastroPenalidadeForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroPenalidadeForm form = (CadastroPenalidadeForm)arg;
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.peInicioFaixaPenalidade", form.getEntity().getPeInicioFaixaPenalidade(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.peFimFaixaPenalidade",  form.getEntity().getPeFimFaixaPenalidade(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.vlFatorCalculoPenalidade",  form.getEntity().getVlFatorCalculoPenalidade(), errors);			
			ValidatorUtil.verificaCampoObrigatorio("entity.dtInicioVigencia",  form.getEntity().getDtInicioVigencia(), errors);			
			ValidatorUtil.verificaPeriodo("entity.dtFimVigencia", form.getEntity().getDtInicioVigencia(), form.getEntity().getDtFimVigencia(), errors);
		}		
	}
	
}
