package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.controller.validator.ControleArquivosValidator;
import com.claro.sccweb.form.SearchResultForm;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;


/**
 * Funcionalidade : Arquivos/Controle de Arquivos
 * 
 * Páginas : /WEB-INF/jsp/arquivo/controle/
 * 
 * Descrição : Gerar relatório de Controle de Arquivos que permite consultar
 * os protocolos de arquivos SCC enviados/recebidos via Connect Direct.
 *
 */
@Controller
@RequestMapping("/user/controle/arquivos")
public class ControleArquivosController extends BaseFormController{
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{		
		super.initBinder(binder);		
		binder.setValidator(new ControleArquivosValidator());		
	}
	
	/**
	 * Popula os combos para filtros de pesquisa.
	 * @return View para usuário informar os dados de pesquisa.
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaPesquisa() throws Exception
	{		
		ModelAndView mav = new ModelAndView();				
		mav.setViewName("controle_arquivos_pesquisa");		
		mav.addObject("filtro", new PesquisaArquivosConnectTO());
		return mav;
	}
	
	@ModelAttribute("tiposArquivo")
	public List<BasicStringItem> populaListaTiposArquivo() throws Exception {		
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("TCOT%", "Arquivo de Batimento"));
		comboList.add(new BasicStringItem("TCOU%", "Arquivo de Comprovação de Fraude/Cobrança"));
		comboList.add(new BasicStringItem("EST.%", "Arquivo de Estorno de Débitos"));
		comboList.add(new BasicStringItem("TCOP%", "Arquivos de Parcelamento/Multas & Juros"));
		comboList.add(new BasicStringItem("TCOE%", "Arquivos de Remessa"));
		comboList.add(new BasicStringItem("TCOR%", "Arquivos de Retorno"));
		comboList.add(new BasicStringItem("TCOS%", "Arquivos de Saldo de Lotes Arquivos Fiscais"));		
		return comboList;
	}
	
	@ModelAttribute("statusArquivo")
	public List<BasicIntegerItem> populaListaStatusArquivo() throws Exception
	{		
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		comboList.add(new BasicIntegerItem(BasicDAO.GET_ALL, "Todos"));
		comboList.add(new BasicIntegerItem(0L, "Arquivos com Sucesso"));
		comboList.add(new BasicIntegerItem(1L, "Arquivos com Falha"));
		return comboList;
	}
	
	/**
	 * Executa a pesquisa e popula a tabela de resultados.
	 * @param form Filtro de pesquisa populado pelo usuário.
	 * @return View com a tabela de resultados.
	 */
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView mostraResultados(@Valid @ModelAttribute("filtro") PesquisaArquivosConnectTO form,BindingResult bindingResult,Model model)
	throws Exception {	
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("controle_arquivos_pesquisa");	
			return mav;
		}
		List<ControlConnectFile> resultadoPesquisa = getServiceManager().getArquivosService().pesquisaArquivosConnect(form);
		
		SearchResultList searchResultList = new SearchResultList();
		searchResultList.setResult(resultadoPesquisa);
		searchResultList.setResultClassType(ControlConnectFile.class);		
		mav.addObject("operacao", new SearchResultForm());
		getSessionDataManager().setSearchResultList(searchResultList);
				
		mav.setViewName("controle_arquivos_resultados");			
		return mav;		
	}
	
	/**
	 * Após os resultados serem apresentados na tela o usuário pode selecionar alguma operação sobre eles.
	 * @param form 
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/report" , method = RequestMethod.POST)
	public ModelAndView processaResultados(@ModelAttribute("operacao") SearchResultForm form,BindingResult bindingResult,Model model)
	throws Exception
	{
		ModelAndView mav = new ModelAndView();
		if (form.getOperation().equals(SearchResultForm.COMMAND_BACK))
			{
			return novaPesquisa();
			}
		else if (form.getOperation().equals(SearchResultForm.COMMAND_RESET))
			{
			return novaPesquisa();
			}
		else if (form.getOperation().equals(SearchResultForm.COMMAND_EXCEL))
			{
			mav.setViewName("controle_arquivos_excel");			
			}
		return mav;
	}
	
	@RequestMapping(value = "/json" , method= RequestMethod.GET )
	public void getSearchResultList(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		List<ControlConnectFile> result = getSessionDataManager().getSearchResultList().getResult();
		JSONArray data = new JSONArray(); 
		for (int i=0;i<result.size();i++)
			{
			 JSONArray row = new JSONArray();
			 row.put(result.get(i).getDEST_FILE());
			 row.put(getSccDateFormat().format(result.get(i).getPk().getSTRT_DATE()));
			 row.put(getSccDateFormat().format(result.get(i).getSTOP_DATE()));
			 if (result.get(i).getEXIT_CODE().intValue() == 0)
				 row.put("Sucesso");
			 else
				 row.put("Falha");
			 row.put(result.get(i).getEXIT_DESC());
			 data.put(row);
			}
		 JSONObject jsonResponse = new JSONObject();
		 jsonResponse.put("aaData", data);
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());		
	}
	
}
