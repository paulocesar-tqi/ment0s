package com.claro.sccweb.controller.processados.pos;

import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;

public class ListaCDRsCsvHandler extends AbstractView {

	private final String SEPARATOR = ",";

	@Override
	protected void renderMergedOutputModel(Map<String,Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<SccCdrCobillingDecorator> decoratorList = (List<SccCdrCobillingDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_4, request);
		if (decoratorList == null)
			throw new ControllerExecutionException("Navegação inválida. Pesquisa de arquivos não encontrada no cache.");

		BufferedWriter writer = new BufferedWriter(response.getWriter());
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"export.csv\"");

			writer.write("Cod. Motivo"+SEPARATOR+"Status"+SEPARATOR+"Data Status"+SEPARATOR+"CSP"+SEPARATOR+"A#"+SEPARATOR+
					     "B#"+SEPARATOR+"EOT Origem"+SEPARATOR+"EOT Externa"+SEPARATOR+"Data Chamada"+SEPARATOR+
					     "Hora Chamada"+SEPARATOR+"Duracao"+SEPARATOR+"Vl. Liquido"+SEPARATOR+"Arquivo Retorno");
			writer.newLine();
			for(SccCdrCobillingDecorator cdr : decoratorList) {
				writer.write(cdr.getCdMotivo()+SEPARATOR+cdr.getStatus()+SEPARATOR+cdr.getDataStatus()+SEPARATOR+cdr.getCSP()+SEPARATOR+cdr.getNumeroA()+SEPARATOR+
			                 cdr.getNumeroB()+SEPARATOR+cdr.getEOTOrigem()+SEPARATOR+cdr.getEOTExterna()+SEPARATOR+cdr.getDataChamada()+SEPARATOR+
						     cdr.getHoraChamada()+SEPARATOR+cdr.getDuracao()+SEPARATOR+cdr.getValorLiquido()+SEPARATOR+cdr.getArquivoRetorno());			
				writer.newLine();
			}
			
		} catch (Exception e) {
			throw new ControllerExecutionException("Navegação inválida. Pesquisa de arquivos não encontrada no cache.");
		} finally {
			writer.flush(); 
			writer.close();
		}
	}

	 protected Object getFromSession(String name,HttpServletRequest request)
	 {
		 return request.getSession().getAttribute(name);
	 }
}
