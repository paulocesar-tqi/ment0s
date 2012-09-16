package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccRelatorioJurosMulta;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.DemonstrativoRepassePosPagoValidator;
import com.claro.sccweb.decorator.DemonstrativoRepassePosDecorator;
import com.claro.sccweb.decorator.RelatorioJurosMultaDecorator;
import com.claro.sccweb.decorator.RepasseServicoAdicionalDecorator;
import com.claro.sccweb.form.DemonstrativoRepassePosPagoForm;

/**
 * Controller para gerar demonstrativo de repasses , juros e multas e demonstrativo de assinatura pós-pago.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pos/demonstrativo")
public class DemonstrativoRepassePosPagoController extends BaseFormController {

	
	
	/**
	 * Possível valor do campo hidden operacao.
	 */
	public static final String OPERACAO_PESQUISAR = "PESQUISAR";
	
	/**
	 * Solicita a exportação do demonstrativo para Excel.
	 */
	public static final String OPERACAO_EXCEL = "EXCEL";
	
	/**
	 * Possível valor do campo hidden operacao.
	 */
	public static final String OPERACAO_SOLICITAR = "SOLICITAR";
	
	/**
	 * Index para atributo de request contendo o demonstrativo de repasse para uma holding.
	 */
	public static final String DEMONSTRATIVO_HOLDING = "DEMONSTRATIVO_HOLDING";
	
	/**
	 * Index para atributo de request contendo o demonstrativo de repasse para uma operadora.
	 */
	public static final String DEMONSTRATIVO_OPERADORA = "DEMONSTRATIVO_OPERADORA";
	
	/**
	 * Index para atributo de request contendo o demonstrativo de juros e multas.
	 */
	public static final String DEMONSTRATIVO_JUROS_MULTAS = "DEMONSTRATIVO_JUROS_MULTAS";
	
	/**
	 * Index para atributo de request contendo o demonstrativo de assinaturas.
	 */
	public static final String DEMONSTRATIVO_ASSINATURAS = "DEMONSTRATIVO_ASSINATURAS";
	
	/**
	 * Validator da tela de demonstrativo de repasse pós-pago.
	 */
	private final DemonstrativoRepassePosPagoValidator validator = new DemonstrativoRepassePosPagoValidator();
	
	 
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);		
		super.initBinder(binder);
	}
	
	/**
	 * Método handler de incialização da tela.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaSolicitacao(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		debug("Acessando page de demonstrativo pós-pago.");
		cleanDisplayTag(request);
		cleanSession(getClass(), request, DEMONSTRATIVO_HOLDING,DEMONSTRATIVO_OPERADORA,DEMONSTRATIVO_ASSINATURAS);
		cleanSession(getClass(), request);		
		cleanMyFormCache(this.getClass());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("repasse_pos_demonstrativo_filtro");
		mav.addObject("filtro", new DemonstrativoRepassePosPagoForm());
		return mav;
	}
	
	
	/**
	 * Handler para refresh de TAB2. Esse refresh ocorre em duas situações:
	 * 1. Quando o usuário pressiona o botão Voltar.
	 * 2. Quando o usuário clica em um link de paginação da displaytag.
	 * Como não queremos perder nenhum dado da última requisição , pegamos o form do Session manager.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tab2" , method = RequestMethod.GET)
	public ModelAndView tab2(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)getMyFormFromCache(this.getClass());
		form.setOperacao("REFRESH");
		mav.setViewName("repasse_pos_demonstrativo_filtro");
		mav.addObject("filtro", form);
		return mav;
	}
	
	/**
	 * Handler para refresh de TAB1. Esse refresh ocorre em duas situações:
	 * 1. Quando o usuário pressiona o botão Voltar.
	 * 2. Quando o usuário clica em um link de paginação da displaytag.
	 * Como não queremos perder nenhum dado da última requisição , pegamos o form do Session manager.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)getMyFormFromCache(this.getClass());
		form.setOperacao("");
		mav.setViewName("repasse_pos_demonstrativo_filtro");
		mav.addObject("filtro", form);
		return mav;
	}
	
	/**
	 * Método handler para a tela de demonstrativo de repasse pós-pago.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form com dados preenchidos pelo usuário.
	 * @param bindingResult Result das validações e binding.
	 * @param model Model
	 * @return Model and View
	 * @throws Exception DAOException e ServiceException tratadas pela super classe.
	 * @throws Exception
	 */
	@RequestMapping(value="/executa" , method = RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePosPagoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		if (form.getOperacao().equals(OPERACAO_PESQUISAR))
			return pesquisa(request, response, form, bindingResult, model);
		else if (form.getOperacao().equals(OPERACAO_SOLICITAR))
				return solicitaDemonstrativo(request, response, form, bindingResult, model);
		else if (form.getOperacao().equals(OPERACAO_EXCEL))
			return new ModelAndView("repasse_demonstrativo_pos_excel");
		else
			return null;		
	}
	
	/**
	 * Pesquisa os pares de operadora Claro/operadora LD de acordo com o filtro informado (LD , Período e Produto).
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form com dados preenchidos pelo usuário.
	 * @param bindingResult Result das validações e binding.
	 * @param model Model
	 * @return Model and View
	 * @throws Exception DAOException e ServiceException tratadas pela super classe.
	 */	
	public ModelAndView pesquisa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePosPagoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		debug("Inciando pesquisa de pares de operadoras para Demonstrativo Pós-Pago");
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("repasse_pos_demonstrativo_filtro");
		if (!bindingResult.hasErrors())
			{
			Date dtInicialPesquisa = getServiceManager().getPeriodicidadeService().calculaDataInicialPeriodo(form.getPeriodo(), form.getMesRelatorio(), form.getAnoRelatorio());
			Date dtFinalPesquisa = getServiceManager().getPeriodicidadeService().calculaDataFinalPeriodo(form.getPeriodo(), form.getMesRelatorio(), form.getAnoRelatorio());
			form.setDtFinalPeriodo(dtFinalPesquisa);
			form.setDtInicialPeriodo(dtInicialPesquisa);
			List<SccContratoAcordado> acordos = getServiceManager().getContratoService().pesquisaContratosPorProduto(form.getOperadoraClaro(), form.getOperadoraExterna(), form.getProdutoCobilling(), dtInicialPesquisa, dtFinalPesquisa, true);		
			debug("Foram encontrados "+acordos.size()+" acordos (pares de operadoras)");
			form.setCdProdutoCobilling(form.getProdutoCobilling());
			form.setCdPeriodicidadeRepasse(form.getPeriodo());
			form.setOperadoraExterna(BasicDAO.NULL_STRING);
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, acordos, request);			
			model.asMap().put("acordos", acordos);	
			}		
		cacheMyForm(this.getClass(), form);
		mav.addObject("filtro", form);			
		return mav;
	}
	
	
	/**
	 * Método handler para requisições de solicitação do demonstratvido de repasse.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form com dados preenchidos pelo usuário.
	 * @param bindingResult Result das validações e binding.
	 * @param model Model
	 * @return Model and View
	 * @throws Exception DAOException e ServiceException tratadas pela super classe.
	 */
	private ModelAndView solicitaDemonstrativo(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePosPagoForm form,BindingResult bindingResult,Model model) throws Exception
	{		
		debug("Iniciando solicitação do demonstrativo para LD "+form.getCdEOT()+" e produto "+form.getCdProdutoCobilling());
		cleanSession(getClass(), request, DEMONSTRATIVO_HOLDING,DEMONSTRATIVO_OPERADORA,DEMONSTRATIVO_ASSINATURAS,DISPLAY_TAG_SPACE_2);
		ModelAndView mav = new ModelAndView();
		form.populaTO();
		mav.setViewName("repasse_pos_demonstrativo_filtro");
		form.setOperacao(OPERACAO_SOLICITAR);		
		SccOperadora operadora = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTClaro());
		mav.addObject("filtro", form);
		cacheMyForm(getClass(), form);		
		if (operadora.isHolding())
			{
			debug("Carregando demonstrativo de repasse para holding com EOT "+form.getCdEOTClaro());
			List<DemonstrativoRepassePosDecorator> demonstrativoHolding =  getServiceManager().getRepasseService().carregaDemonstrativoRepasse(form.getCdEOTClaro(), form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo(), form.getDtFinalPeriodo(), operadora.isHolding());
			storeInSession(getClass(), DEMONSTRATIVO_HOLDING, demonstrativoHolding, request);			
			}
		debug("Carregando demonstrativo de repasse para operadora com EOT "+form.getCdEOTClaro());
		List<DemonstrativoRepassePosDecorator> demonstrativoOperadora =  getServiceManager().getRepasseService().carregaDemonstrativoRepasse(form.getCdEOTClaro(), form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo(), form.getDtFinalPeriodo(), false);		
		storeInSession(getClass(), DEMONSTRATIVO_OPERADORA, demonstrativoOperadora, request);
		
		debug("Carregando demonstrativo de assinaturas para holding com EOT "+form.getCdEOTClaro());
		List<RepasseServicoAdicionalDecorator> repasseAdicionalList = getServiceManager().getRepasseService().carregaAssinaturasRepasse(form.getCdEOTClaro(),form.getCdEOT(), form.getCdProdutoCobilling(), form.getCdPeriodicidadeRepasse(), form.getDtInicialPeriodo(),operadora.isHolding());
		storeInSession(getClass(), DEMONSTRATIVO_ASSINATURAS, repasseAdicionalList, request);		
		
		debug("Carregando demonstrativo de multas para holding com EOT "+form.getCdEOTClaro());
		List<SccRelatorioJurosMulta> multas = getServiceManager().getRepasseService().carregaJurosMultasRepasse(form.getCdEOTClaro(), form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo());
		List<RelatorioJurosMultaDecorator> multasDecoratorList = new ArrayList<RelatorioJurosMultaDecorator>();
		if (multasDecoratorList != null){
		for (int i=0;i<multas.size();i++)
			{
			multasDecoratorList.add(new RelatorioJurosMultaDecorator(multas.get(i)));
			}
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, multasDecoratorList, request);				
		return mav;
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
		allValues.setCdEot(BasicDAO.NULL_STRING);
		allValues.setDsOperadora("Selecione...");
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
		allValues.setCdEot(BasicDAO.NULL_STRING);
		allValues.setDsOperadora("Selecione...");
		comboList.add(allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	/**
	 * Carrega uma lista vazia de produtos de cobilling. 
	 * Essa lista só será populada após seleção da Operadora LD.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception
	{
		List<SccProdutoCobilling> comboList = new ArrayList<SccProdutoCobilling>();
		SccProdutoCobilling nullValue = new SccProdutoCobilling();
		nullValue.setNoProdutoCobilling("Selecione...");
		nullValue.setCdProdutoCobilling(BasicDAO.NULL);
		comboList.add(0,nullValue);
		return comboList;
	}
	
	/**
	 * Carrega uma lista vazia de periodicidades. A lista será populada após seleção 
	 * de produto e operadora LD.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("periodos")
	public List<SccPeriodicidadeRepasse> populaPeriodosCobilling() throws Exception
	{
		List<SccPeriodicidadeRepasse> comboList = new ArrayList<SccPeriodicidadeRepasse>();
		SccPeriodicidadeRepasse nullValue = new SccPeriodicidadeRepasse();
		nullValue.setCdPeriodicidadeRepasse(BasicDAO.NULL);
		nullValue.setNoPeriodicidadeRepasse("Selecione...");
		comboList.add(0,nullValue);
		return comboList;
	}
	
	/**
	 * Popula o combo de meses da tela de filtro.
	 */
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
	}
	
	/**
	 * Popula os produtos que a LD possui acordados com a Claro.
	 * @param cdEOT
	 * @param request
	 * @param response
	 * @param form
	 * @throws Exception
	 */
	@RequestMapping(value="/json/lista_produtos/{cdEOT}" , method=RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") DemonstrativoRepassePosPagoForm form) throws Exception
	{		
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOT);		
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<produtos.size();i++)
			{			
			jsonResponse.put(produtos.get(i).getCdProdutoCobilling().toString(), produtos.get(i).getNoProdutoCobilling());			
			}
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
	
	/**
	 * Popula por AJAX a lista de periodicidades disponíveis para o produto e a LD. 
	 * @param cdProduto
	 * @param cdEOT
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/json/lista_periodos/{cdProduto}/{cdEOT}" , method=RequestMethod.GET)
	public void pesquisaPeriodos(@PathVariable("cdProduto") Long cdProduto,@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		List<SccPeriodicidadeRepasse> repasses = getServiceManager().getContratoService().pesquisaPeriodicidadeRepasse(cdEOT, cdProduto);
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<repasses.size();i++)
			{			
			jsonResponse.put(repasses.get(i).getCdPeriodicidadeRepasse().toString(), repasses.get(i).getNoPeriodicidadeRepasse());			
			}
		 response.setContentType("application/json");
	     response.getWriter().print(jsonResponse.toString());
	}
	
}
