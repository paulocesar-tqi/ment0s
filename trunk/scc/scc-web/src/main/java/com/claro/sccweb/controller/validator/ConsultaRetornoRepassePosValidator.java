package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.controller.repasse.pos.ConsultaRepassePosController;
import com.claro.sccweb.form.ConsultaRepassePosForm;
import com.claro.sccweb.form.DemonstrativoRepassePosPagoForm;


/**
 * Validator para a tela de filtro da consulta de repasses.
 *
 */
public class ConsultaRetornoRepassePosValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ConsultaRepassePosForm.class);
		
	}

	 
	public void validate(Object arg, Errors errors) {
		ConsultaRepassePosForm form = (ConsultaRepassePosForm)arg;
		if(form.getOperacao().equals(ConsultaRepassePosController.OPERACAO_PESQUISAR)){
		if ((form.getCdEOTLD() == null) || (form.getCdEOTLD().equals(BasicDAO.NULL_STRING)) || (form.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)))
			errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");
		if ((form.getCdEOTClaro() == null) || (form.getCdEOTClaro().equals(BasicDAO.NULL_STRING)) )
			errors.rejectValue("cdEOTClaro", "campoObrigatorio", "Campo Obrigatório");

		if ((form.getCdProdutoCobilling() == null) || (form.getCdProdutoCobilling().equals(BasicDAO.NULL)))
			errors.rejectValue("cdProdutoCobilling", "campoObrigatorio", "Campo Obrigatório");
		
		if ((form.getCdPeriodicidade() == null) || (form.getCdPeriodicidade().equals(BasicDAO.NULL)))
			errors.rejectValue("cdPeriodicidade", "campoObrigatorio", "Campo Obrigatório");
		
		if ((form.getMesRelatorio() == null) || (form.getMesRelatorio().equals(BasicDAO.NULL)))
			errors.rejectValue("mesRelatorio", "campoObrigatorio", "Campo Obrigatório");
		
		if ((form.getAnoRelatorio() == null) || (form.getAnoRelatorio().equals(BasicDAO.NULL)))
			errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigatório");
		}
		
	}

	
	
}
