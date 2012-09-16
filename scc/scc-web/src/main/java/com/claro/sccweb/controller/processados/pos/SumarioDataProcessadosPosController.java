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
 * Gera sumário de CDRs por data.
 *
 */
@Controller
@RequestMapping(value="/user/pos/processados/sumario/data")
public class SumarioDataProcessadosPosController extends BaseFormController {

	
	@RequestMapping(value="/cdStatus/{cdStatus}" , method=RequestMethod.GET)
	public ModelAndView selecionaArquivo(@PathVariable("cdStatus") Long cdStatus,Model model) throws Exception
	{
		debug("Iniciando sumário de arquivo de processados pós-pago por data.");
		if (!validaSessao(cdStatus))
			{
			debug("Usuário executou navegação inválida ao acessar /user/pos/processados/sumario/data/cdStatus/{cdStatus}");
			throw new Exception("Navegação inválida. Um grupo de CDRs deve ser selecionado antes.");
			}
		NavegacaoArquivoCDRs navegacaoArquivoCDRs = (NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0);
		SccArquivoCobilling sccArquivoCobilling = navegacaoArquivoCDRs.getArquivoSelecionado();
		 
		getSessionDataManager().setBackURL("/scc/user/pos/processados/sumario/status/arquivo/"+sccArquivoCobilling.getSqArquivo());
		List<ItemSumarioArquivoProcessado> items =  getServiceManager().getSumarioService().geraSumarioProcessadoPosXDataXStatus(sccArquivoCobilling, cdStatus);
		navegacaoArquivoCDRs.setSumario(items);
		navegacaoArquivoCDRs.setStatusSelecionado(cdStatus);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sumario_processados_pos_datas");
		return mav;
	}
	
	@RequestMapping(value="/back" , method=RequestMethod.GET)
	public ModelAndView back() throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sumario_processados_pos_datas");
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
			 row.put(result.get(i).getDescricaoItem());
			 row.put(result.get(i).getTotal());
			 row.put(i);
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
	private boolean validaSessao(Long cdStatus)
	{
		return true;
	}
	
}
