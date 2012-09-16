package com.claro.sccweb.controller.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.SearchResultForm;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;

public class ControleArquivosValidator implements Validator {

	/**
	 * Como o formulário para relatório de arquivos do Connect possui dois tipos de form , o método
	 * supports irá testar duas instâncias.
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
				errors.rejectValue("dataInicio", "periodoInvalido","Campo Obrigatório");
				}		
			if (form.getDataFinal() == null)
				{
				errors.rejectValue("dataFinal", "periodoInvalido","Campo Obrigatório");
				}
		}
		else if ((form.getDataInicio() != null) && (form.getDataFinal() != null))
			{
				if (form.getDataFinal().before(form.getDataInicio()))
					{
					errors.rejectValue("dataInicio", "periodoInvalido","Período Inválido");
					}
			}
		else if ((form.getDataInicio() != null) && (form.getDataInicio().after(now)))
			{
				errors.rejectValue("dataInicio", "dataNoFuturo","Período no Futuro");
			}
		else if ((form.getDataFinal() != null) && (form.getDataFinal().after(now)))
			{
				errors.rejectValue("dataFinal", "dataNoFuturo","Período no Futuro");
			}}
		else if (arg0 instanceof SearchResultForm){
			/*Sem validações para fazer.*/
		}
	}

	
	
}
