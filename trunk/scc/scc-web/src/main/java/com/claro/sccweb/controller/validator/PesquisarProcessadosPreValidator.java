package com.claro.sccweb.controller.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.PesquisaProcessadosPreForm;

public class PesquisarProcessadosPreValidator implements Validator {

	
	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(PesquisaProcessadosPreForm.class);
	}

	 
	public void validate(Object arg0, Errors errors) {
		Date now = new Date();
		
		PesquisaProcessadosPreForm form = (PesquisaProcessadosPreForm)arg0;
		if (form.getOperacao().equalsIgnoreCase("pesquisar")){
			if ((form.getPesquisa().getDataInicialPeriodo() == null) || (form.getPesquisa().getDataFinalPeriodo() == null)){
				if (form.getPesquisa().getDataInicialPeriodo() == null)
					{
					errors.rejectValue("dataInicialPeriodo", "periodoInvalido","Campo Obrigatório");
					}		
				if (form.getPesquisa().getDataFinalPeriodo() == null)
					{
					errors.rejectValue("dataFinalPeriodo", "periodoInvalido","Campo Obrigatório");
					}
			}
			else if ((form.getPesquisa().getDataInicialPeriodo() != null) && (form.getPesquisa().getDataFinalPeriodo() != null))
				{
					if (form.getPesquisa().getDataFinalPeriodo().before(form.getPesquisa().getDataInicialPeriodo()))
						{
						errors.rejectValue("dataInicialPeriodo", "periodoInvalido","Período Inválido");
						}
				}
			else if ((form.getPesquisa().getDataInicialPeriodo() != null) && (form.getPesquisa().getDataInicialPeriodo().after(now)))
				{
					errors.rejectValue("dataInicialPeriodo", "dataNoFuturo","Período no Futuro");
				}
			else if ((form.getPesquisa().getDataFinalPeriodo() != null) && (form.getPesquisa().getDataFinalPeriodo().after(now)))
				{
					errors.rejectValue("dataFinalPeriodo", "dataNoFuturo","Período no Futuro");
				}	
		}
	}
}
