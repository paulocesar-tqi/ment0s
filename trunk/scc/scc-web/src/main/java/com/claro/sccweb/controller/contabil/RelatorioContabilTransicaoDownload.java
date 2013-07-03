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
		
		RelatorioContabilTransicaoForm form = (RelatorioContabilTransicaoForm) model.get("filtro");
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");	
		
		if (!getServiceManager(request).getArquivosService().fileExists(form)){
			throw new ControllerExecutionException("Arquivo "+form.getNomeArquivo()+" não encontrado no servidor");
		}

		OutputStream stream = response.getOutputStream();
		response.setContentType("file");
		response.setHeader("Content-disposition","attachment; filename=" + form.getNomeArquivo());
		getServiceManager(request).getFtpService().writeFile(stream, form.getDiretorio(), form.getNomeArquivo());
	}
	
	 
	protected boolean generatesDownloadContent() {	
		return true;
	}
	
}
