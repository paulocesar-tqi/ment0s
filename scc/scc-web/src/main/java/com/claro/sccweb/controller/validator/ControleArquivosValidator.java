package com.claro.sccweb.controller.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.SearchResultForm;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;

public class ControleArquivosValidator implements Validator {

	/**
	 * Como o formul�rio para relat�rio de arquivos do Connect possui dois tipos de form , o m�todo
	 * supports ir� testar duas inst�ncias.
	 */
	 
	public boolean supports(Class<?> clazz) {		
		
		return ((PesquisaArquivosConnectTO.class.equals(clazz)) || (SearchResultForm.class.equals(clazz)));
	}

	 
	public void validate(Object arg0, Errors errors) {
		if (arg0 instanceof PesquisaArquivosConnectTO){
		Date now = new Date();		
		PesquisaArquivosConnectTO form = (PesquisaArquivosConnectTO)arg0;
		
		if ((form.getDataInicio() == null) || (form.getDataFinal() == null)){
			if (form.getDataInicio() == null)
				{
				errors.rejectValue("dataInicio", "periodoInvalido","Campo Obrigat�rio");
				}		
			if (form.getDataFinal() == null)
				{
				errors.rejectValue("dataFinal", "periodoInvalido","Campo Obrigat�rio");
				}
		}
		else if ((form.getDataInicio() != null) && (form.getDataFinal() != null))
			{
				if (form.getDataFinal().before(form.getDataInicio()))
					{
					errors.rejectValue("dataInicio", "periodoInvalido","Per�odo Inv�lido");
					}
			}
		else if ((form.getDataInicio() != null) && (form.getDataInicio().after(now)))
			{
				errors.rejectValue("dataInicio", "dataNoFuturo","Per�odo no Futuro");
			}
		else if ((form.getDataFinal() != null) && (form.getDataFinal().after(now)))
			{
				errors.rejectValue("dataFinal", "dataNoFuturo","Per�odo no Futuro");
			}}
		else if (arg0 instanceof SearchResultForm){
			/*Sem valida��es para fazer.*/
		}
	}

	
	
}
