package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.RelPrestacaoServicoPosForm;

public class RelatorioPrestacaoServicoPosValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(RelPrestacaoServicoPosForm.class);
	}

	
	@Override
	public void validate(Object obj, Errors erros) {

		RelPrestacaoServicoPosForm form = (RelPrestacaoServicoPosForm) obj;
		if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals(BasicDAO.NULL))) {
			erros.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigatório");
		}

		
		
		
	}
	
	

}
