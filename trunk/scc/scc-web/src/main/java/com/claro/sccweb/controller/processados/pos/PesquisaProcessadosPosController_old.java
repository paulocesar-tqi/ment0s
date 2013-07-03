package com.claro.sccweb.controller.processados.pos;

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
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.controller.validator.PesquisarProcessadosPosValidator;
import com.claro.sccweb.form.SearchResultForm;
import com.claro.sccweb.form.SelecaoArquivoResultForm;
import com.claro.sccweb.form.helper.NavegacaoArquivoCDRs;


/**
 * Controller respons�vel pela l�gica de navega��o da tela de pesquisa de arquivos de processados (processados) p�s-pago.
 * @version 1.1
 */
@Controller
@RequestMapping(value="/user/pos/processados/pesquisa_old")
public class PesquisaProcessadosPosController_old extends BaseFormController {

	/**
	 * Configura o validator e chama m�todo da super classe para demais binding.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{		
		super.initBinder(binder);		
		binder.setValidator(new PesquisarProcessadosPosValidator());		
	}
	
	/**
	 * Inicia uma nova pesquisa.
	 * @return Model e View da tela de filtro.
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaPesquisa(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		cleanDisplayTag(request);
		cleanMyFormCache(getClass());
		debug("Iniciando nova pesquisa de arquivos de processados p�s-pago.");
		reset();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pesquisa_processados_pos_pesquisa");
		mav.addObject("filtro", new SCCArquivoCobillingFiltro());
		return mav;
	}
	
	
	/**
	 * Executa a pesquisa ap�s o filtro ser aplicado pelo usu�rio. Esse m�todo iniciar� a sess�o de navega��o em arquivo de processados 
	 * com o objeto {@link NavegacaoArquivoCDRs}.
	 * @return Model e View da tela de resultados.
	 * @throws Exception
	 */
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute("filtro")  SCCArquivoCobillingFiltro form,BindingResult bindingResult,Model model) throws Exception
	{		
		debug("Iniciando execute() para pesquisa de arquivos de processados p�s-pago.");
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors())
			{
			debug("Erros de valida��o durante execu��o da pesquisa de arquivos de processados p�s-pago.");
			mav.setViewName("pesquisa_processados_pos_pesquisa");		
			}else
			{
				NavegacaoArquivoCDRs navegacaoArquivoCDRs = new NavegacaoArquivoCDRs();
				debug("Iniciando execu��o de servi�o de pesquisa de arquivos de processados p�s-pago.");
				List<SccArquivoCobilling> resultadoPesquisa = getServiceManager().getArquivosService().pesquisaArquivos(form);
				if (resultadoPesquisa != null)
					debug("Servi�o de pesquisa de arquivos de processados p�s retorno : "+resultadoPesquisa.size()+" registro(s)");
				
				navegacaoArquivoCDRs.setArquivosFiltro(resultadoPesquisa);			
				SearchResultList searchResultList = new SearchResultList();
				searchResultList.setResultClassType(NavegacaoArquivoCDRs.class);			
				searchResultList.setResult(new ArrayList<NavegacaoArquivoCDRs>());
				searchResultList.getResult().add(navegacaoArquivoCDRs);
				getSessionDataManager().setSearchResultList(searchResultList);
				mav.addObject("operacao", new SelecaoArquivoResultForm());
				mav.setViewName("pesquisa_processados_pos_resultado");
			}		
		return mav;
	}
	
	
	/**
	 * Handler para opera��es relacionadas ao resultado da pesquisa. O usu�rio pode solicitar a exporta��o dos resultados para Excel ou
	 * uma nova pesquisa. 
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/report" , method = RequestMethod.POST)
	public ModelAndView processaResultados(@ModelAttribute("operacao") SelecaoArquivoResultForm form,BindingResult bindingResult,Model model)
	throws Exception
	{
		ModelAndView mav = new ModelAndView();
		if (form.getOperation().equals(SearchResultForm.COMMAND_RESET))
			{
			return null;
			}
		else if (form.getOperation().equals(SearchResultForm.COMMAND_EXCEL))
			{
			mav.setViewName("pesquisa_processados_pos_excel");			
			}
		return mav;
	}
	
	/**
	 * M�todo executado quando o usu�rio retorna da p�gina de sum�rio para a p�gina de pesquisas.
	 * Como a op��o de voltar no browser � desabilitada para evitar erros de sess�o , o back deve ser controlado pelos controllers
	 * que permitem esse tipo de opera��o.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/back" , method = RequestMethod.GET)
	public ModelAndView back() throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("operacao", new SelecaoArquivoResultForm());
		mav.setViewName("pesquisa_processados_pos_resultado");
		return mav;
	}
	
	
	
	
	
	/**
	 * M�todo utilizado pela Datatable atrav�s de AJAX para popular a tabela de resultados da pesquisa.
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/arquivos/json" , method= RequestMethod.GET )
	public void getListaGeralArquivos(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		NavegacaoArquivoCDRs navegacaoArquivoCDRs = (NavegacaoArquivoCDRs)getSessionDataManager().getSearchResultList().getResult().get(0);
		List<SccArquivoCobilling> result = navegacaoArquivoCDRs.getArquivosFiltro();
		JSONArray data = new JSONArray();
		for (int i=0;i<result.size();i++)
			{
			 JSONArray row = new JSONArray();
			 row.put(result.get(i).getSqArquivo());
			 row.put(result.get(i).getNoArquivo());
			 row.put(result.get(i).getOperadoraClaro().getDsOperadora());
			 row.put(result.get(i).getOperadoraExterna().getDsOperadora());			 			 
			 row.put(formataData(result.get(i).getDtProcClaro()));			 
			 row.put(formataData(result.get(i).getDtProcExterna()));
			 row.put(formataData(result.get(i).getDtInicioTrafego()));
			 row.put(formataData(result.get(i).getDtFimTrafego()));
			 row.put(result.get(i).getQtRegistros());
			 data.put(row);
			}
		 JSONObject jsonResponse = new JSONObject();
		 jsonResponse.put("aaData", data);
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
	/* In�cio dos m�todos para pr�-popular o model.*/
	
	
	/**
	 * Popula o combo com os tipos de operadoras.
	 * @return Tipo de Operadoras
	 * @throws Exception
	 */
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadora() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();		
		comboList.add(new BasicStringItem("OP", "Operadora"));
		comboList.add(new BasicStringItem("HO", "Holding"));
		return comboList;
	}
	
	/**
	 * Popula o combo com tipos de arquivos. Para a pequisa de processados p�s-pago , esses tipos s�o 
	 * todos os que possuem tipo de batimento = REM.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("tiposArquivo")
	public List<SccTipoArquivo> populaTiposArquivo() throws Exception
	{
		return getServiceManager().getArquivosService().pesquisaEntidadePorTipoBatimento("REM");		
	}
	
	
	/**
	 * Popula o combo com o status do arquivo de acordo com o erro de protocolo.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("errosProtocolo")
	public List<BasicStringItem> populaErrosProtocolo() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("OK","Arquivos OK"));
		comboList.add(new BasicStringItem("NOK","Arquivos n�o OK"));
		return comboList;
	}
	
	/**
	 * Popula o combo operadorasClaro com a lista default (operadoras). 
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
		return comboList;
	}
	
	/**
	 * Pequisa as operadoras holding da Claro.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("holdingClaro")
	public List<SccOperadora> populaHoldingClaro() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaHoldingClaro());
		return comboList;
	}
	
	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	/**
	 * Popula combo com os tipos de per�odo pesquis�veis. As op��es s�o duas : DATA DE REFER�NCIA ou DATA DE PROCESSAMENTO CLARO.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("tiposPeriodo")
	public List<BasicStringItem> populaTipoPeriodo() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();		
		comboList.add(new BasicStringItem("REF", "Data de Refer�ncia"));
		comboList.add(new BasicStringItem("PROC", "Data de Processamento Claro"));
		return comboList;
	}
	
	
	/**
	 * Popula o combo com os status de arquivos. Esse combo ser� substitu�do pela lista de grupos de status.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("statusArquivo")
	public List<SccStatusArquivo> populaListaStatusArquivo() throws Exception
	{
	
		List<SccStatusArquivo> comboList = new ArrayList<SccStatusArquivo>();
		SccStatusArquivo allValues = new SccStatusArquivo();
		allValues.setCdStatusArquivo(BasicDAO.GET_ALL_STRING);
		allValues.setDsStatusArquivo("Todos");
		comboList.add(0, allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getStatusArquivo());
		return comboList;
	}
	
	
	/**
	 * M�todo que retorna objeto JSON para atualiza��o do combo de operadoras por AJAX quando o usu�rio seleciona 'Operadoras Claro'
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/operadoras/json" , method= RequestMethod.GET )
	public void populaOperadorasJSON(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<SccOperadora> list = populaOperadorasClaro();
		JSONObject jsonResponse = new JSONObject();		
		for (int i=0;i<list.size();i++)
			{			
			jsonResponse.put(list.get(i).getCdEot(),list.get(i).getDsOperadoraForCombos());			
			}
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
	
	/**
	 * M�todo que retorna objeto JSON para atualiza��o do combo de operadoras por AJAX quando o usu�rio seleciona 'Holding Claro'
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/holding/json" , method= RequestMethod.GET )
	public void populaHoldingJSON(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<SccOperadora> list = populaHoldingClaro();
		JSONObject jsonResponse = new JSONObject();		 
		for (int i=0;i<list.size();i++)
			{
			jsonResponse.put(list.get(i).getCdEot(),list.get(i).getDsOperadoraForCombos());			
			}
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
	
	/* Final dos m�todos para pr�-popular o model.*/
	
	
	
	
	
}
