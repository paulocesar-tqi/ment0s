package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroSolicitacaoVUMForm;

public class CadastroSolicitacaoVUMValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroSolicitacaoVUMForm.class);
	}

	public void validate(Object arg, Errors errors) {
		CadastroSolicitacaoVUMForm form = (CadastroSolicitacaoVUMForm) arg;
		if (form.getOperacao() != null) {
			if (form.getOperacao().equals("INSERIR")) {
				ValidatorUtil.verificaCampoObrigatorio("dataInicioRepasse", form.getDataInicioRepasse(), errors);
				ValidatorUtil.verificaCampoObrigatorio("dataFimRepasse", form.getDataFimRepasse(), errors);
				ValidatorUtil.verificaCampoObrigatorio("cdEOTLD", form.getCdEOTLD(), errors);
				ValidatorUtil.verificaCampoObrigatorio("plataforma", form.getPlataforma(), errors);
				ValidatorUtil.verificaCampoObrigatorio("tipoArquivo", form.getTipoArquivo(), errors);
			}
		}
	}

}
