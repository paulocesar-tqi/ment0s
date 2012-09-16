package com.claro.sccweb.controller.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.sccweb.form.SelecaoArquivoResultForm;

public class PesquisarProcessadosPosValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return (clazz.equals(SCCArquivoCobillingFiltro.class) || (clazz.equals(SelecaoArquivoResultForm.class)));
	}

	 
	public void validate(Object arg0, Errors errors) {
		Date now = new Date();
		if (arg0 instanceof SCCArquivoCobillingFiltro){
			SCCArquivoCobillingFiltro form = (SCCArquivoCobillingFiltro)arg0;
			if ((form.getDataInicialPeriodo() == null) || (form.getDataFinalPeriodo() == null)){
				if (form.getDataInicialPeriodo() == null)
					{
					errors.rejectValue("dataInicialPeriodo", "periodoInvalido","Campo Obrigatório");
					}		
				if (form.getDataFinalPeriodo() == null)
					{
					errors.rejectValue("dataFinalPeriodo", "periodoInvalido","Campo Obrigatório");
					}
			}
			else if ((form.getDataInicialPeriodo() != null) && (form.getDataFinalPeriodo() != null))
				{
					if (form.getDataFinalPeriodo().before(form.getDataInicialPeriodo()))
						{
						errors.rejectValue("dataInicialPeriodo", "periodoInvalido","Período Inválido");
						}
				}
			else if ((form.getDataInicialPeriodo() != null) && (form.getDataInicialPeriodo().after(now)))
				{
					errors.rejectValue("dataInicialPeriodo", "dataNoFuturo","Período no Futuro");
				}
			else if ((form.getDataFinalPeriodo() != null) && (form.getDataFinalPeriodo().after(now)))
				{
					errors.rejectValue("dataFinalPeriodo", "dataNoFuturo","Período no Futuro");
				}	
		}		
	}

	
}
