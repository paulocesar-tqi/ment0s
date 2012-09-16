package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.SolicitacaoRepassePrePagoForm;

public class SolicitacaoRepassePrePagoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(SolicitacaoRepassePrePagoForm.class); 
	}

	 
	public void validate(Object arg, Errors errors) {
		SolicitacaoRepassePrePagoForm form = (SolicitacaoRepassePrePagoForm)arg;
		if (form.getOperacao() != null)
			{
			if (form.getOperacao().equals("INSERIR"))
				{
				if (form.getAnoRelatorio() == null)
					errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigatório");
				if ((form.getCdEOTLD() == null) || (form.getCdEOTLD().equals(BasicDAO.NULL_STRING)))
					errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");
				if ((form.getCdProdutoPrepago() == null) || (form.getCdProdutoPrepago().equals(BasicDAO.NULL_STRING)))
					errors.rejectValue("cdProdutoPrepago", "campoObrigatorio", "Campo Obrigatório");				
				}
			}
		
	}

	
	
}
