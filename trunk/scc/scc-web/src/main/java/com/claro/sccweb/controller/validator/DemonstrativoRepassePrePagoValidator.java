package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.controller.repasse.pre.DemonstrativoRepassePrePagoController;
import com.claro.sccweb.form.DemonstrativoRepassePrePagoForm;

public class DemonstrativoRepassePrePagoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(DemonstrativoRepassePrePagoForm.class);		
	}

	 
	public void validate(Object arg, Errors errors) {
		DemonstrativoRepassePrePagoForm form = (DemonstrativoRepassePrePagoForm)arg;
		if (form.getOperacao().equals(DemonstrativoRepassePrePagoController.OPERACAO_PESQUISAR))
			{
			if (form.getCdEOTHolding().equals(BasicDAO.NULL_STRING))
				errors.rejectValue("cdEOTHolding", "campoObrigatorio", "Campo Obrigatório");
			if (form.getCdEOTLD().equals(BasicDAO.NULL_STRING))
				errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");			
			if ((form.getAnoDemonstrativo() == null) || (form.getAnoDemonstrativo().equals("")))
				errors.rejectValue("anoDemonstrativo", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getCdProdutoPrepago() == null) || (form.getCdProdutoPrepago().equals(BasicDAO.NULL_STRING)))
				errors.rejectValue("cdProdutoPrepago", "campoObrigatorio", "Campo Obrigatório");
			}
	}

	
	
}
