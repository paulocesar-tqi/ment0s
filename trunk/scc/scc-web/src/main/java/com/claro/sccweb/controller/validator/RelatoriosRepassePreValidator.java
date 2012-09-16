package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.controller.repasse.pre.RelatoriosRepassePreController;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

public class RelatoriosRepassePreValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RelatoriosRepassePreForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		RelatoriosRepassePreForm form = (RelatoriosRepassePreForm)arg;
		if (form.getOperacao().equals(RelatoriosRepassePreController.OPERACAO_PESQUISAR))
			{
			if (form.getAnoRelatorio() == null)
				errors.rejectValue("anoRelatorio", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getCdProdutoPrepago() == null) || (form.getCdProdutoPrepago().equals(BasicDAO.NULL_STRING)))
				errors.rejectValue("cdProdutoPrepago", "campoObrigatorio", "Campo Obrigatório");
			}
		
		
	}

	
	
}
