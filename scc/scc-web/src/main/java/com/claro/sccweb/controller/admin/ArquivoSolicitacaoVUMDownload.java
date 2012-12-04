package com.claro.sccweb.controller.admin;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.form.CadastroSolicitacaoVUMForm;
import com.claro.sccweb.view.SccAbstractView;

public class ArquivoSolicitacaoVUMDownload extends SccAbstractView {

	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CadastroSolicitacaoVUMForm form = (CadastroSolicitacaoVUMForm) getFormFromCache(
				ArquivoSolicitacaoVUMController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");

		if (!getServiceManager(request).getSccSolicitacaoVumService().fileExists(form)) {
			throw new ControllerExecutionException("Arquivo " + form.getNomeArquivo() + " não encontrado no servidor");
		}

		OutputStream stream = response.getOutputStream();
		response.setContentType("file");
		response.setHeader("Content-disposition", "attachment; filename=" + form.getNomeArquivo());
		getServiceManager(request).getSccSolicitacaoVumService().writeFile(form, stream);
	}

	protected boolean generatesDownloadContent() {
		return true;
	}


}
