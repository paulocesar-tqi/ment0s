package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.SolicitacaoRepassePosPagoForm;

public class SolicitacaoRepassePosPagoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return SolicitacaoRepassePosPagoForm.class.equals(clazz);
	}

	 
	public void validate(Object arg, Errors errors) {
			SolicitacaoRepassePosPagoForm form = (SolicitacaoRepassePosPagoForm)arg;
			if (form.getOperacao().equals("INCLUDE")){
			if ((form.getCdEOT() == null) || (form.getCdEOT().equals(BasicDAO.NULL_STRING)))
				errors.rejectValue("cdEOT", "campoObrigatorio", "Campo Obrigat�rio");
			
			if ((form.getCdProdutoCobilling() == null) || (form.getCdProdutoCobilling().equals(BasicDAO.NULL)))
				errors.rejectValue("cdProdutoCobilling", "campoObrigatorio", "Campo Obrigat�rio");
			
			if ((form.getCdPeriodicidadeRepasse() == null) || (form.getCdPeriodicidadeRepasse().equals(BasicDAO.NULL)))
				errors.rejectValue("cdPeriodicidadeRepasse", "campoObrigatorio", "Campo Obrigat�rio");
			
			if ((form.getMesRelatorio() == null) || (form.getMesRelatorio().equals(BasicDAO.NULL)))
				errors.rejectValue("mesRelatorio", "campoObrigatorio", "Campo Obrigat�rio");
			
			if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals(BasicDAO.NULL)))
				errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigat�rio");
			}else if (form.getOperacao().equals("SEARCH")){
				if ((form.getCdEOT() == null) || (form.getCdEOT().equals(BasicDAO.NULL_STRING)))
					errors.rejectValue("cdEOT", "campoObrigatorio", "Campo Obrigat�rio");
				if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals(BasicDAO.NULL)))
					errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigat�rio");
				if ((form.getMesRelatorio() == null) || (form.getMesRelatorio().equals(BasicDAO.NULL)))
					errors.rejectValue("mesRelatorio", "campoObrigatorio", "Campo Obrigat�rio");
			}
			
			
			
	}

	
	
}
