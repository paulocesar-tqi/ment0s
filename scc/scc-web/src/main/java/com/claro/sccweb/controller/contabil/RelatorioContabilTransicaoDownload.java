package com.claro.sccweb.controller.contabil;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.form.RelatorioContabilTransicaoForm;
import com.claro.sccweb.view.SccAbstractView;

public class RelatorioContabilTransicaoDownload extends SccAbstractView {

	 
	 
	protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RelatorioContabilTransicaoForm form = (RelatorioContabilTransicaoForm)getFormFromCache(RelatorioContabilTransicaoController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");		
		OutputStream stream = response.getOutputStream();
		if (getServiceManager(request).getFtpService().fileExists(form.getNomeArquivo()) == null)
			{
			throw new ControllerExecutionException("Arquivo "+form.getNomeArquivo()+" não encontrado no servidor");
			}
		response.setContentType("file");
		response.setHeader("Content-disposition","attachment; filename=" + form.getNomeArquivo());
		getServiceManager(request).getFtpService().writeFile(stream, form.getNomeArquivo());
	}
	
	 
	protected boolean generatesDownloadContent() {	
		return true;
	}
	
}
