package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;
import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CreditosValidator;
import com.claro.sccweb.form.ControleRemessaCicloForm;
import com.claro.sccweb.form.CreditosForm;
import com.claro.sccweb.form.SccIndicadorForm;

@Controller
@RequestMapping(value="/user/repasse/pre/creditos")
public class CreditosController extends BaseFormController {

	
	public static final int TAMANHO_PAGINA = 100;
	
	private final CreditosValidator validator = new CreditosValidator();
	
	/**
	 * Pesquisar créditos de acordo com o filtro informado.
	 */
	public static final String OPERACAO_PESQUISAR = "pesquisar";
	
	
	/**
	 * Detalhes créditos.
	 */
	public static final String OPERACAO_DETALHES = "detalhes";
	
	public static final String OPERACAO_EXCEL = "excel";
	
	/**
	 * Reset na página.
	 */
	public static final String OPERACAO_NOVA = "nova";
	
	
	 
	  public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		super.initBinder(binder);
	}
	
	
	 /**
	   * Handler de entrada na funcionaldidade. 
	   * @param request HTTP Request
	   * @param response HTTP Response
	   * @return Model ane View com o filtro da consulta.
	   * @throws Exception
	   */
	  @RequestMapping(value="/new" , method = RequestMethod.GET)
	  public ModelAndView novaConsulta(HttpServletRequest request, HttpServletResponse response) throws Exception
	  {
		  cleanMyFormCache(getClass());		  
		  cleanDisplayTag(request);		  
		  ModelAndView mav = new ModelAndView();
		  mav.addObject("filtro", new CreditosForm());
		  mav.setViewName(getViewName());
		  return mav;
	  }
	  
	  /**
	   * Handler de requisições do controller.
	   * @param request
	   * @param response
	   * @param form
	   * @param bindingResult
	   * @param model
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value="/execute" , method = { RequestMethod.GET, RequestMethod.POST })
	  public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  CreditosForm form,BindingResult bindingResult,Model model) throws Exception
	  {
		  ModelAndView mav = null;
		  if (bindingResult.hasErrors())
		  	{
			  mav = new ModelAndView(getViewName()); 
		  	}
		  else if (form.getOperacao().equals(OPERACAO_NOVA))
		  	{
			  mav = novaConsulta(request, response);
		  	}
		  else if (form.getOperacao().equals(OPERACAO_PESQUISAR))
		  	{
			  mav = pesquisa(request, response, form, bindingResult, model);
		  	}
		  else if (form.getOperacao().equals(OPERACAO_DETALHES))
		  	{
			  mav = detalhes(request, response, form, bindingResult, model);
		  	}
		
		  return mav;
	  }
	  
	  
		@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
		public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, CreditosForm form) throws Exception {
			
			  ModelAndView mav = null;
			  if(form.getOperacao().equals("pesquisar")){
				  cleanMyFormCache(getClass());		  
				  cleanDisplayTag(request);		  
				  List<RelCreditosPrePagoView> view = getServiceManager().getCreditosPrePagoService().carregaRelatorioCreditos(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdTipoCredito(), form.getCdStatusCredito(), form.getDataInicial(),form.getDataFinal());
				  form.setListCreditosPrePago(view);
				  cacheMyForm(getClass(), form);
				  storeInSession(getClass(), DISPLAY_TAG_SPACE_1, view, request);	
				  mav = new ModelAndView(getViewName(), "filtro", form);
				  
			  }else{
				  mav = new ModelAndView(getViewName());
			  }
			  
			  return mav;
			
		}

	  
		public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null) {
				mav.addObject("filtro", form);
			} else {
				mav.addObject("filtro", getForm());
			}
	    	return mav;  
		}
	  
		protected ControleRemessaCicloForm getForm() {
			return new ControleRemessaCicloForm();
		}
	  
	  
/*	  @SuppressWarnings("unused")
	@RequestMapping(value="/pagina" , method=RequestMethod.GET)
		public ModelAndView pagina(@RequestParam("page") Integer page,HttpServletRequest request, HttpServletResponse response ) throws Exception
		{
			//PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)getMyFormFromCache(getClass());
			ModelAndView mav = new ModelAndView("creditos_pre_consulta_resultados");		
			SccPaginatedList paginatedList = new SccPaginatedList();
			paginatedList.setObjectsPerPage(TAMANHO_PAGINA);
			paginatedList.setPageNumber(page);
			CreditosForm form = (CreditosForm)getMyFormFromCache(getClass());
			List<RelCreditosPrePagoView> view = getServiceManager().getCreditosPrePagoService().carregaRelatorioCreditos(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdTipoCredito(), form.getCdStatusCredito(), form.getDataInicial(),form.getDataFinal(),paginatedList.getObjectsPerPage(),paginatedList.getPageNumber()-1);		  
			paginatedList.setList(view);
			paginatedList.setFullListSize(form.getQuantidadeResultados());
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, paginatedList, request);
			mav.addObject("filtro",form);
			return mav;
		}
*/	  
	  /**
	   * Pesquisa arquivos de créditos de acordo com o filtro informado.
	   * @param request
	   * @param response
	   * @param form
	   * @param bindingResult
	   * @param model
	   * @return
	   * @throws Exception
	   */
	  private ModelAndView pesquisa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  CreditosForm form,BindingResult bindingResult,Model model) throws Exception  {
		
		  ModelAndView mav = null;
		  if(form.getOperacao().equals("pesquisar")){
			  cleanMyFormCache(getClass());		  
			  cleanDisplayTag(request);		  
			  List<RelCreditosPrePagoView> view = getServiceManager().getCreditosPrePagoService().carregaRelatorioCreditos(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdTipoCredito(), form.getCdStatusCredito(), form.getDataInicial(),form.getDataFinal());
			  form.setListCreditosPrePago(view);
			  cacheMyForm(getClass(), form);
			  storeInSession(getClass(), DISPLAY_TAG_SPACE_1, view, request);	
			  mav = new ModelAndView(getViewName(), "filtro", form);
			  
		  }else{
			  mav = new ModelAndView(getViewName());
		  }
		  
		  return mav;
	  }
	  
	  /**
	   * Executado quando o usuário seleciona uma linha na tabela de resultados.
	   * @param request
	   * @param response
	   * @param form
	   * @param bindingResult
	   * @param model
	   * @return
	   * @throws Exception
	   */
	  private ModelAndView detalhes(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  CreditosForm form,BindingResult bindingResult,Model model) throws Exception
	  {
		  ModelAndView mav = new ModelAndView("creditos_pre_consulta_resultados");		  
		  List<RelDetalheCreditoPrePagoView> detalhes = getServiceManager().getCreditosPrePagoService().carregaDetalhesCredito(form.getSeqArquivo(), form.getSeqCredito());
		  storeInSession(getClass(), DISPLAY_TAG_SPACE_2, detalhes, request);
		  return mav;
	  }
	  
		@RequestMapping(value = "/detalhe", method=RequestMethod.GET)
		public List<RelDetalheCreditoPrePagoView> editar(@RequestParam("sqArquivo") Long sqArquivo, @RequestParam("sqCredito") Long sqCredito , HttpServletRequest request,HttpServletResponse response, CreditosForm form) throws Exception {
			
			 List<RelDetalheCreditoPrePagoView> detalhes = getServiceManager().getCreditosPrePagoService().carregaDetalhesCredito(sqArquivo, sqCredito);
			 storeInSession(getClass(), DISPLAY_TAG_SPACE_2, detalhes, request);
			 form.setListDetalhes(detalhes);
			
			return detalhes;

		}

	  
	
		@ModelAttribute("operadorasClaro")
		public List<SccOperadora> populaOperadorasClaro() throws Exception {
			List<SccOperadora> comboList = new ArrayList<SccOperadora>();
			SccOperadora allValues = new SccOperadora();
			allValues.setCdEot(BasicDAO.GET_ALL_STRING);
			comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
			return comboList;
		}
	  
 
	  @ModelAttribute("operadorasExternas")
		public List<SccOperadora> populaOperadorasExternas() throws Exception
		{
			List<SccOperadora> comboList = new ArrayList<SccOperadora>();			
			comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
			return comboList;
		}
	  

	  
	  @ModelAttribute("tiposCredito")
	  public List<BasicStringItem> pesquisaTiposCredito() throws Exception
	  {
		  List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		  BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
		  comboList.add(0,allValues);
		  List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("CDORG");
		  for (int i=0;i<rows.size();i++)
		  	{
			  SccPreDominio item = rows.get(i);
			  comboList.add(new BasicStringItem(item.getId().getCdDominio(),item.getDsDominio())); 
		  	}
		  return comboList;
	  }
	  
	  
	    
	  
	  @ModelAttribute("statusCredito")
	  public List<BasicStringItem> pesquisaStatusCredito() throws Exception
	  {		  
		  List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		  BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
		  comboList.add(0,allValues);
		  List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("STCRD");
		  for (int i=0;i<rows.size();i++)
		  	{
			  SccPreDominio item = rows.get(i);
			  comboList.add(new BasicStringItem(item.getId().getCdDominio(),item.getDsDominio())); 
		  	}
		  return comboList;
	  }
	  
	  public String getViewName()
	  {
		  return "creditos_pre_consulta_filtro";
	  }
}
