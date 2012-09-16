package com.claro.sccweb.controller.processados.pre;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.SearchResultList;

/**
 * Mostra detalhes de um arquivo pré-pago processado após
 * esse ter sido selecionado na tela de resultados do controller
 * {@link PesquisaProcessadosPreController_old}
 *
 */
@Controller
@RequestMapping(value="/user/pre/processados/detalhe")
public class DetalhesProcessadosPreController extends BaseFormController {

	@RequestMapping(value = "/arquivo/{arquivo}", method = RequestMethod.GET)
	public ModelAndView selecionaCDR(@PathVariable("arquivo") Long seqArquivo,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		List<ViewArquivoPrePago> detalhes =  getServiceManager().getArquivosService().carregaDetalhesArquivoPrePago(seqArquivo);
		SearchResultList searchResultList = new SearchResultList();
		searchResultList.setResultClassType(ViewArquivoPrePago.class);
		searchResultList.setResult(detalhes);
		getSessionDataManager().setSearchResultList(searchResultList);		
		mav.setViewName("pequisa_processados_pre_detalhes");
		return mav;
	}
	
	
	/**
	 * Popula a datatable com detalhes do arquivo processado pré-pago. 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/table/json" , method= RequestMethod.GET )
	public void populaSumarioPorStatus(HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		JSONArray data = new JSONArray();
		List<ViewArquivoPrePago> result = getSessionDataManager().getSearchResultList().getResult();
		for (int i=0;i<result.size();i++)
			{
			JSONArray row = new JSONArray();			 
			ViewArquivoPrePago item = result.get(i);
			row.put(item.getSQ_ARQUIVO());
			row.put(item.getNO_ARQUIVO());
			row.put(item.getNO_DIRETORIO_ARQUIVO());				
			row.put(item.getNO_MAQUINA_ARQUIVO());
			row.put(formataData(item.getDT_MOVIMENTO()));
			row.put(item.getNO_SISTEMA_RESP());
			row.put(item.getNO_PROCESSO_RESP());
			row.put(item.getIN_STATUS_PROCESSO());			 
			data.put(row);
			}
		 JSONObject jsonResponse = new JSONObject();
		 jsonResponse.put("aaData", data);
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
}
