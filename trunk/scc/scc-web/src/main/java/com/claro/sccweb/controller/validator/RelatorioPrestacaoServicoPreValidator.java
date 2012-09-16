package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.RelatorioPrestacaoServicoPreForm;



public class RelatorioPrestacaoServicoPreValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(RelatorioPrestacaoServicoPreForm.class);
	}

	@Override
	public void validate(Object obj, Errors erros) {
		RelatorioPrestacaoServicoPreForm form = (RelatorioPrestacaoServicoPreForm) obj;
		if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals(BasicDAO.NULL))) {
			erros.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigatório");
		}

		
	}
	
	

}
