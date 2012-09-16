package com.claro.sccweb.controller.processados.pos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.form.ItemSumarioArquivoProcessado;
import com.claro.sccweb.form.helper.NavegacaoArquivoCDRs;

/**
 * Gera sumário de CDRs por status.
 *
 */
@Controller
@RequestMapping(value="/user/pos/processados/sumario/status")
public class SumarioStatusProcessadosPosController extends BaseFormController {

	/**
	 * Envio do arquivo selecionado para sumário por GET.
	 * @param seqArquivo PK do arquivo.
	 * @param model Model
	 * @return Model e View da primeira tela de sumário contendo as totalizações de quantidade de CDRs por status.
	 * @throws Exception
	 */
	@RequestMapping(value="/arquivo/{seqArquivo}" , method=RequestMethod.GET)
	public ModelAndView selecionaArquivo(@PathVariable("seqArquivo") Long seqArquivo,Model model) throws Exception
	{
		debug("Iniciando sumário de arquivo de processados pós-pago por status.");
		ModelAndView mav = new ModelAndView();
		if (!validaSessao(seqArquivo))
			{
			debug("Usuário executou navegação inválida ao acessar /user/pos/processados/sumario/arquivo/{seqArquivo}");
			throw new Exception("Navegação inválida. Um arquivo de processados deve ser selecionado antes.");
			}
		debug("Pesquisando arquivo de processados "+seqArquivo);
		SccArquivoCobilling sccArquivoCobilling = getServiceManager().getArquivosService().carregaArquivoByPK(seqArquivo);
		if (sccArquivoCobilling == null)
			{
			debug("Arquivo com COD_SEQ "+seqArquivo+" não encontrado na base de dados.");
			throw new Exception("Arquivo com COD_SEQ "+seqArquivo+" não encontrado na base de dados.");
			}		
		NavegacaoArquivoCDRs navegacaoArquivoCDRs = (NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0);
		navegacaoArquivoCDRs.setArquivoSelecionado(sccArquivoCobilling);
		getSessionDataManager().setBackURL("/user/pos/processados/pesquisa/back.scc");
		executaService();
		mav.setViewName("sumario_processados_pos_status");
		return mav;
	}
	
	public void executaService() throws Exception
	{
		NavegacaoArquivoCDRs navegacaoArquivoCDRs = (NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0);
		List<ItemSumarioArquivoProcessado> cdrs = getServiceManager().getSumarioService().geraSumarioProcessadoPosXStatus(navegacaoArquivoCDRs.getArquivoSelecionado());						
		navegacaoArquivoCDRs.setSumario(cdrs);
	}
	
	@RequestMapping(value="/back" , method = RequestMethod.GET)
	public ModelAndView back() throws Exception
	{
		ModelAndView mav = new ModelAndView();
		executaService();
		mav.setViewName("sumario_processados_pos_status");
		return mav;
	}
	
	/**
	 * Popula a datatable com totalizações por status de CDRs. 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/table/json" , method= RequestMethod.GET )
	public void populaSumarioPorStatus(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<ItemSumarioArquivoProcessado> result = ((NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0)).getSumario();
		JSONArray data = new JSONArray();		
		for (int i=0;i<result.size();i++)
			{
			 JSONArray row = new JSONArray();
			 row.put(result.get(i).getKey());
			 row.put(result.get(i).getDescricaoItem());
			 row.put(result.get(i).getTotal());
			 data.put(row);
			}
		 JSONObject jsonResponse = new JSONObject();
		 jsonResponse.put("aaData", data);
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
	
	
	/**
	 * Verifica se o usuário tem uma sessão de navegação de arquivo de CDRs válida e se o arquivo selecionado encontra-se nela.
	 * @return
	 */
	private boolean validaSessao(Long seqArquivo)
	{
		return true;
	}
	
}
