package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.form.DemonstrativoRepassePosPagoForm;

/**
 * Validador da tela de Demonstrativo de Repasse P�s-Pago.
 *
 */
public class DemonstrativoRepassePosPagoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return DemonstrativoRepassePosPagoForm.class.equals(clazz);
	}

	 
	public void validate(Object arg, Errors errors) {
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)arg;
		if(form.getOperacao().equals("PESQUISAR")){		
		if ((form.getOperadoraClaro() == null) || (form.getOperadoraClaro().equals(BasicDAO.NULL_STRING)))
			errors.rejectValue("operadoraClaro", "campoObrigatorio", "Campo Obrigat�rio");
		if ((form.getOperadoraExterna() == null) || (form.getOperadoraExterna().equals(BasicDAO.NULL_STRING)) || (form.getCdEOT().equals(BasicDAO.GET_ALL_STRING)))
			errors.rejectValue("operadoraExterna", "campoObrigatorio", "Campo Obrigat�rio");
		if ((form.getProdutoCobilling() == null) || (form.getProdutoCobilling().equals(BasicDAO.NULL)))
			errors.rejectValue("produtoCobilling", "campoObrigatorio", "Campo Obrigat�rio");
		
		if ((form.getPeriodo() == null) || (form.getPeriodo().equals(BasicDAO.NULL)))
			errors.rejectValue("periodo", "campoObrigatorio", "Campo Obrigat�rio");
		
		if ((form.getMesRelatorio() == null) || (form.getMesRelatorio().equals(BasicDAO.NULL)))
			errors.rejectValue("mesRelatorio", "campoObrigatorio", "Campo Obrigat�rio");
		
		if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals(BasicDAO.NULL)))
			errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigat�rio");
		}
		
	}

	
	
}
