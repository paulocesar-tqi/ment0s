package com.claro.sccweb.controller.processados.pre;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.controller.validator.PesquisarProcessadosPreValidator;


/**
 * Funcionalidade : Arquivo -> Processados
 * Módulo : Pré-Pago 
 *
 * Realiza pesquisa de arquivos pré-pago processados.
 */
@Controller
@RequestMapping(value="/user/pre/processados/pesquisa_old")
public class PesquisaProcessadosPreController_old extends BaseFormController {

	 
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		binder.setValidator(new PesquisarProcessadosPreValidator());
	}
	
	/**
	 * Inicia uma nova pesquisa.
	 * @return Model e View da tela de filtro.
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaPesquisa() throws Exception
	{
		debug("Iniciando nova pesquisa de arquivos de processados pré-pago.");
		reset();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pesquisa_processados_pre_pesquisa");
		mav.addObject("filtro", new SCCArquivoCobillingFiltro());
		return mav;
	}
	
	
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView executa(@Valid @ModelAttribute("filtro")  SCCArquivoCobillingFiltro form,BindingResult bindingResult,Model model) throws Exception
	{		
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors())
			{
			mav.setViewName("pesquisa_processados_pre_pesquisa");	
			return mav;
			}		
		List<SccArquivoCobilling> arquivos = getServiceManager().getArquivosService().pesquisaArquivosPrePago(form);	
		cacheMyForm(this.getClass(), form);		
		SearchResultList searchResultList = new SearchResultList();
		searchResultList.setResult(arquivos);
		searchResultList.setResultClassType(SccArquivoCobilling.class);
		getSessionDataManager().setSearchResultList(searchResultList);	
		mav.setViewName("pesquisa_processados_pre_resultados");
		return mav;
	}
	
	@RequestMapping(value="/back" , method=RequestMethod.GET)
	public ModelAndView back() throws Exception
	{
		ModelAndView mav = new ModelAndView();
		SCCArquivoCobillingFiltro form = (SCCArquivoCobillingFiltro)getMyFormFromCache(this.getClass());				
		List<SccArquivoCobilling> arquivos = getServiceManager().getArquivosService().pesquisaArquivosPrePago(form);	
		cacheMyForm(this.getClass(), form);		
		SearchResultList searchResultList = new SearchResultList();
		searchResultList.setResult(arquivos);
		searchResultList.setResultClassType(SccArquivoCobilling.class);
		getSessionDataManager().setSearchResultList(searchResultList);
		mav.setViewName("pesquisa_processados_pre_resultados");
		return mav;
	}
	
	/**
	 * Popula o combo com tipos de arquivos. Para a pequisa de processados pós-pago , esses tipos são 
	 * todos os que possuem tipo de batimento = REM.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("tiposArquivo")
	public List<SccTipoArquivo> populaTiposArquivo() throws Exception
	{			
		List<SccTipoArquivo> comboList = new ArrayList<SccTipoArquivo>();
		SccTipoArquivo sccTipoArquivo = new SccTipoArquivo();
		sccTipoArquivo.setCdTipoArquivo(BasicDAO.GET_ALL);
		sccTipoArquivo.setDsTipoArquivo("Todos");
		comboList.add(sccTipoArquivo);
		comboList.addAll(getServiceManager().getArquivosService().pesquisaTiposArquivoPrePago());
		return comboList;		
	}
	
	@ModelAttribute("statusArquivo")
	public List<SccStatusArquivo> populaStatusArquivo() throws Exception
	{
		List<SccStatusArquivo> comboList = new ArrayList<SccStatusArquivo>();
		SccStatusArquivo sccStatusArquivo = new SccStatusArquivo();
		sccStatusArquivo.setCdStatusArquivo(BasicDAO.GET_ALL_STRING);
		sccStatusArquivo.setDsStatusArquivo("Todos");
		comboList.add(sccStatusArquivo);
		comboList.addAll(getServiceManager().getArquivosService().pesquisaStatusArquivoPrePago());
		return comboList;
	}
	
	
	/**
	 * Método utilizado pela Datatable através de AJAX para popular a tabela de resultados da pesquisa.
	 * @param request HTTPRequest
	 * @param response HTTPResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/arquivos/json" , method= RequestMethod.GET )
	public void getListaGeralArquivos(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		
		List<SccArquivoCobilling> result = getSessionDataManager().getSearchResultList().getResult();
		JSONArray data = new JSONArray();
		for (int i=0;i<result.size();i++)
			{
			 JSONArray row = new JSONArray();
			 row.put(result.get(i).getSqArquivo());
			 row.put(result.get(i).getNoArquivo());
			 row.put(formataData(result.get(i).getDtProcClaro()));
			 row.put(formataData(result.get(i).getDtInicioTrafego()));
			 row.put(formataData(result.get(i).getDtFimTrafego()));
			 row.put(result.get(i).getCdStatusArquivo().getDsStatusArquivo());
			 row.put(result.get(i).getTipoArquivo().getDsTipoArquivo());
			 row.put(result.get(i).getQtRegistros());
			 data.put(row);
			}
		 JSONObject jsonResponse = new JSONObject();
		 jsonResponse.put("aaData", data);
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
	
}
