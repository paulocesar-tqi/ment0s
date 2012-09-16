package com.claro.sccweb.controller.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RecepcaoTransmissaoForm;

public class RecepcaoTransmissaoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RecepcaoTransmissaoForm.class);
	}

	 
	public void validate(Object arg0, Errors errors) {
		Date now = new Date();		
		RecepcaoTransmissaoForm form = (RecepcaoTransmissaoForm)arg0;
		if ((form.getPesquisa().getDataInicialPeriodo() == null) || (form.getPesquisa().getDataFinalPeriodo() == null)){
			if (form.getPesquisa().getDataInicialPeriodo() == null)
				{
				errors.rejectValue("pesquisa.dataInicialPeriodo", "periodoInvalido","Campo Obrigat�rio");
				}		
			if (form.getPesquisa().getDataFinalPeriodo() == null)
				{
				errors.rejectValue("pesquisa.dataFinalPeriodo", "periodoInvalido","Campo Obrigat�rio");
				}
		}
		else if ((form.getPesquisa().getDataInicialPeriodo() != null) && (form.getPesquisa().getDataFinalPeriodo() != null))
			{
				if (form.getPesquisa().getDataFinalPeriodo().before(form.getPesquisa().getDataInicialPeriodo()))
					{
					errors.rejectValue("pesquisa.dataInicialPeriodo", "periodoInvalido","Per�odo Inv�lido");
					}
			}
		else if ((form.getPesquisa().getDataInicialPeriodo() != null) && (form.getPesquisa().getDataInicialPeriodo().after(now)))
			{
				errors.rejectValue("pesquisa.dataInicialPeriodo", "dataNoFuturo","Per�odo no Futuro");
			}
		else if ((form.getPesquisa().getDataFinalPeriodo() != null) && (form.getPesquisa().getDataFinalPeriodo().after(now)))
			{
				errors.rejectValue("pesquisa.dataFinalPeriodo", "dataNoFuturo","Per�odo no Futuro");
			}
	}
	}


