package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroMotivoRejeicaoForm;

public class CadastroMotivoRejeicaoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {	
		return clazz.equals(CadastroMotivoRejeicaoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroMotivoRejeicaoForm form = (CadastroMotivoRejeicaoForm)arg;
		if (form.getOperacao() != null)
			{
			if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
				{
				ValidatorUtil.verificaCampoObrigatorio("entity.cdMotivoRejeicao", form.getEntity().getCdMotivoRejeicao(), errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.cdClassificacaoMotivo", form.getEntity().getCdClassificacaoMotivo(), errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.dsMotivoRejeicao", form.getEntity().getDsMotivoRejeicao(), errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.inTipoClassificacao", form.getEntity().getInTipoClassificacao(), errors);
				ValidatorUtil.verificaCampoObrigatorio("entity.txDetalhamentoMotivo", form.getEntity().getTxDetalhamentoMotivo(), errors);
				}
			}
		
	}

	
	
}
