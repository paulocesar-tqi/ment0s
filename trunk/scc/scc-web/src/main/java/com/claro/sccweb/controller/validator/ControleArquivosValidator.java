package com.claro.sccweb.controller.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.ControleArquivoForm;
import com.claro.sccweb.form.SearchResultForm;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;

public class ControleArquivosValidator implements Validator {

	/**
	 * Como o formulário para relatório de arquivos do Connect possui dois tipos de form , o método
	 * supports irá testar duas instâncias.
	 */
	 
	public boolean supports(Class<?> clazz) {		
		
		return ((ControleArquivoForm.class.equals(clazz)) || (SearchResultForm.class.equals(clazz)));
	}

	 
	public void validate(Object arg0, Errors errors) {
		if (arg0 instanceof ControleArquivoForm){
		Date now = new Date();		
		ControleArquivoForm form = (ControleArquivoForm)arg0;
		
		if ((form.getFiltro().getDataInicio() == null) || (form.getFiltro().getDataFinal() == null)){
			if (form.getFiltro().getDataInicio() == null)
				{
				errors.rejectValue("dataInicio", "periodoInvalido","Campo Obrigatório");
				}		
			if (form.getFiltro().getDataFinal() == null)
				{
				errors.rejectValue("dataFinal", "periodoInvalido","Campo Obrigatório");
				}
		}
		else if ((form.getFiltro().getDataInicio() != null) && (form.getFiltro().getDataFinal() != null))
			{
				if (form.getFiltro().getDataFinal().before(form.getFiltro().getDataInicio()))
					{
					errors.rejectValue("dataInicio", "periodoInvalido","Período Inválido");
					}
			}
		else if ((form.getFiltro().getDataInicio() != null) && (form.getFiltro().getDataInicio().after(now)))
			{
				errors.rejectValue("dataInicio", "dataNoFuturo","Período no Futuro");
			}
		else if ((form.getFiltro().getDataFinal() != null) && (form.getFiltro().getDataFinal().after(now)))
			{
				errors.rejectValue("dataFinal", "dataNoFuturo","Período no Futuro");
			}}
		else if (arg0 instanceof SearchResultForm){
			/*Sem validações para fazer.*/
		}
	}

	
	
}
