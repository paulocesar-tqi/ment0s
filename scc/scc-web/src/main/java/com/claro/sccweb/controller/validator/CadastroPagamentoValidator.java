package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.pagamento.CadastroPagamentoController;
import com.claro.sccweb.form.CadastroPagamentoForm;

public class CadastroPagamentoValidator implements Validator{

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroPagamentoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroPagamentoForm form = (CadastroPagamentoForm)arg;
		if (form.getOperacao().equals(CadastroPagamentoController.OPERACAO_PESQUISAR_REPASSES))
			{
			if (form.getAnoRepasse() == null)
				{
				errors.rejectValue("anoRepasse", "campoObrigatorio", "Campo Obrigatório");
				}
			if ((form.getCdEOT() == null) || (form.getCdEOT().equals(BasicDAO.NULL_STRING)))
				{
				errors.rejectValue("cdEOT", "campoObrigatorio", "Campo Obrigatório");
				}
			if ((form.getCdEOTLD() == null) || (form.getCdEOTLD().equals(BasicDAO.NULL_STRING)))
				{
				errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");
				}
			if ((form.getCdEOTLD() == null) || (form.getCdEOTLD().equals(BasicDAO.NULL_STRING)))
				{
				errors.rejectValue("cdEOTLD", "campoObrigatorio", "Campo Obrigatório");
				}
			
			if (form.getCdTipoContrato().equals(BaseFormController.MODULO_POS_PAGO))
				{
				if ((form.getCdProdutoCobilling() == null) || (form.getCdProdutoCobilling().equals(BasicDAO.NULL)))
					{
					errors.rejectValue("cdProdutoCobilling", "campoObrigatorio", "Campo Obrigatório");
					}
				if ((form.getCdPeriodicidade() == null) || (form.getCdPeriodicidade().equals(BasicDAO.NULL)))
					{
					errors.rejectValue("cdPeriodicidade", "campoObrigatorio", "Campo Obrigatório");
					}
				
				}
			}
		
	}

	
	
}
