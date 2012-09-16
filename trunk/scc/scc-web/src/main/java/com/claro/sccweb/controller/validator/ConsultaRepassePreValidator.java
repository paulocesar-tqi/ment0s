package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.controller.repasse.pre.ConsultaRepassePreController;
import com.claro.sccweb.form.ConsultaRepassePreForm;

public class ConsultaRepassePreValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ConsultaRepassePreForm.class);		
	}
	
	public void validate(Object arg, Errors errors) {
		/*
		ConsultaRepassePreForm form = (ConsultaRepassePreForm)arg;
		if (form.getOperacao().equals(ConsultaRepassePreController.OPERACAO_PESQUISAR)) {
			
			System.out.println("\n DENTRO IF \n");
			
			if ((form.getTo().getCdEOTLD() == null) || (form.getTo().getCdEOTLD().equals(BasicDAO.NULL_STRING))) {
				System.out.println("\n DENTRO IF 2\n");
				errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");
			}
			
			if ((form.getTo().getCdProdutoPrepago() == null) || (form.getTo().getCdProdutoPrepago().equals(BasicDAO.NULL_STRING))) {
				System.out.println("\n DENTRO IF 1\n");
				errors.rejectValue("cdProdutoPrepago", "campoObrigatorio", "Campo Obrigatório");
			}
			
			if (form.getTo().getAno() == null) {
				System.out.println("\n DENTRO IF 3	\n");
				errors.rejectValue("ano", "campoObrigatorio", "Campo Obrigatório");
			}
			
			//31919618 - 03/08/2012 as 14:40
			//31919978 - 03/08/2012 as 15:00
			//31919697 - 03/08/2012 as 15:30
			
		}
		*/
	}
	
}
