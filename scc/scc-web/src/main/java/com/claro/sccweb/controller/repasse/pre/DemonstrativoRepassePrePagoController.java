package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.DemonstrativoRepassePrePagoValidator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.ParOperadorasRepassePreDecorator;
import com.claro.sccweb.form.DemonstrativoRepassePrePagoForm;

/**
 * Controller para gerar demonstrativo de repasses e assinaturas pré-pago.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pre/demonstrativo")
public class DemonstrativoRepassePrePagoController extends BaseFormController {

	
	public static final String OPERACAO_PESQUISAR = "OPERACAO_PESQUISAR";
	public static final String OPERACAO_SOLICITAR = "OPERACAO_SOLICITAR";
	public static final String OPERACAO_EXCEL = "OPERACAO_EXCEL";
	public static final String OPERACAO_NOVO = "OPERACAO_NOVO";
	public static final String DEMONSTRATIVO_HOLDING = "DEMONSTRATIVO_HOLDING";
	public static final String DEMONSTRATIVO_OPERADORA = "DEMONSTRATIVO_OPERADORA";
	public static final String DEMONSTRATIVO_ASSINATURAS= "DEMONSTRATIVO_ASSINATURAS"; 
	public static final String DEMONSTRATIVO_COMPLETO = "DEMONSTRATIVO_COMPLETO";
	
	DemonstrativoRepassePrePagoValidator validator  = new DemonstrativoRepassePrePagoValidator();
	
	 
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
		info("Acessando page de demonstrativo pré-pago.");
		cleanDisplayTag(request);
		cleanSession(getClass(), request, DEMONSTRATIVO_HOLDING,DEMONSTRATIVO_OPERADORA,DEMONSTRATIVO_ASSINATURAS);
		cleanSession(getClass(), request);		
		cleanMyFormCache(this.getClass());
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getViewName());
		mav.addObject("filtro", new DemonstrativoRepassePrePagoForm());
		return mav;
	}
	
	private String getViewName()
	{
		return "repasse_pre_demonstrativo_filtro";
	}
	
	
	/**
	 * Handler para requests do usuário. De acorco com o valor do campo operacao do form , um método handler será disparado.  
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = null;
		if (bindingResult.hasErrors())			
			mav = new ModelAndView(getViewName());
		else if (form.getOperacao().equals(OPERACAO_SOLICITAR))
			mav = geraDemonstrativo(request, response, form, bindingResult, model);
		else if (form.getOperacao().equals(OPERACAO_NOVO))
			mav = novaSolicitacao(request, response);
		else if (form.getOperacao().equals(OPERACAO_PESQUISAR))
			mav = pesquisa(request, response, form, bindingResult, model);
		else if (form.getOperacao().equals(OPERACAO_EXCEL))
			mav = geraExcel(request, response, form, bindingResult, model);
		return mav;
	}
	
	
	private ModelAndView geraExcel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		info("Exportando demonstrativo de repasse pré-pago para Excel com filtro : "+form.toString());
		Map<SccOperadora, DemonstrativoRepassePreDecorator> excelModel = new HashMap<SccOperadora, DemonstrativoRepassePreDecorator>();
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm)getMyFormFromCache(getClass());
		SccOperadora operadoraLD = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(cachedForm.getCdEOTLD());
		SccProdutoPrepago produtoPrepago = getServiceManager().getContratoPrePagoService().getProdutoById(cachedForm.getCdProdutoPrepago());		
		String cdEOTOperadoraOriginal = cachedForm.getCdEOTClaro();
		ModelAndView mav = new ModelAndView("repasse_demonstrativo_pre_excel");
		List<SccOperadora> listaHolding = getServiceManager().getPesquisaDominiosService().pesquisaHoldingClaro();		
		for (int i=0;i<listaHolding.size();i++)
			{
			SccOperadora holding = listaHolding.get(i);
			List<SccOperadora> listaOperadorasHolding = getServiceManager().getOperadoraService().pesquisaOperarodasHolding(holding.getCdEot());
			cachedForm.setCdEOTClaro(holding.getCdEot());
			DemonstrativoRepassePreDecorator demonstrativoHolding = getServiceManager().getRepassePreService().carregaDemonstrativoHolding(cachedForm);
			demonstrativoHolding.setOperadora(holding);
			demonstrativoHolding.setProdutoPrepago(produtoPrepago);
			demonstrativoHolding.setOperadoraLD(operadoraLD);
			demonstrativoHolding.setPeriodo(cachedForm.getMesDemonstrativo()+"/"+cachedForm.getAnoDemonstrativo());
			for (int o=0;o<listaOperadorasHolding.size();o++)
				{
				cachedForm.setCdEOTClaro(listaOperadorasHolding.get(o).getCdEot());
				DemonstrativoRepassePreDecorator demonstrativoOperadora = getServiceManager().getRepassePreService().carregaDemonstrativoOperadoras(cachedForm);
				demonstrativoOperadora.setOperadora(listaOperadorasHolding.get(o));
				demonstrativoOperadora.setProdutoPrepago(produtoPrepago);
				demonstrativoOperadora.setOperadoraLD(operadoraLD);
				demonstrativoOperadora.setPeriodo(cachedForm.getMesDemonstrativo()+"/"+cachedForm.getAnoDemonstrativo());
				demonstrativoHolding.getDemonstrativosOperadorasHolding().add(demonstrativoOperadora);
				}
			excelModel.put(holding, demonstrativoHolding);			
			}
		storeInSession(getClass(), DEMONSTRATIVO_COMPLETO, excelModel, request);
		cachedForm.setCdEOTClaro(cdEOTOperadoraOriginal);
		return mav;
	}
	
	/**
	 * Método handler para gerar o demonstrativo de acordo com o filtro informado pelo usuário.	  
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private ModelAndView geraDemonstrativo(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
	{	
		info("Gerando demonstrativo de repasse-pré-pago com filtro "+form.toString());
		ModelAndView mav = new ModelAndView(getViewName());
		cleanSession(getClass(), request, DEMONSTRATIVO_HOLDING,DEMONSTRATIVO_OPERADORA,DEMONSTRATIVO_ASSINATURAS);
		String cdEOTClaro = form.getCdEOTClaro();
		SccOperadora operadoraClaro = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(cdEOTClaro);		 
		DemonstrativoRepassePrePagoForm cachedForm =  (DemonstrativoRepassePrePagoForm)getMyFormFromCache(getClass());		
		cachedForm.setCdEOTClaro(cdEOTClaro);		
		cachedForm.setDtInicial(calculaDataInicialPeriodo(cachedForm.getMesDemonstrativo(), cachedForm.getAnoDemonstrativo()));
		cachedForm.setDtFinal(calculaDataFinalPeriodo(cachedForm.getMesDemonstrativo(), cachedForm.getAnoDemonstrativo()));
		if (operadoraClaro.isHolding())
			storeInSession(getClass(), DEMONSTRATIVO_HOLDING, getServiceManager().getRepassePreService().carregaDemonstrativoHolding(cachedForm).getItens(),request);
		storeInSession(getClass(), DEMONSTRATIVO_OPERADORA, getServiceManager().getRepassePreService().carregaDemonstrativoOperadoras(cachedForm).getItens(),request);
		storeInSession(getClass(), DEMONSTRATIVO_ASSINATURAS,getServiceManager().getRepassePreService().carregaAssinaturas(cachedForm),request);
		form.setOperacao(OPERACAO_SOLICITAR);
		mav.addObject("filtro", form);
		return mav;
	}
	
	
	/**
	 * Pesquisa os pares de operadoras para os quais é possível solicitar o demonstrativo de acordo com o período e produto informados.
	 * Após a pesquisa o form é colocado na session para que os valores do filtro sejam mantidos e utilizados durante a seleção de par de operadoras
	 * para gerar demonstrativo.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private ModelAndView pesquisa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
	{	
		cleanSession(getClass(), request);
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccOperadora> operadoras = getServiceManager().getOperadoraService().pesquisaOperarodasHolding(form.getCdEOTHolding());
		SccOperadora operadoraLD = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTLD());
		List<ParOperadorasRepassePreDecorator> listaParesOperadoras = new ArrayList<ParOperadorasRepassePreDecorator>();
		for (int i=0;i<operadoras.size();i++)
			{
			ParOperadorasRepassePreDecorator decorator = new ParOperadorasRepassePreDecorator(operadoraLD, operadoras.get(i));
			listaParesOperadoras.add(decorator);
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, listaParesOperadoras, request);
			}		
		mav.addObject("filtro", new DemonstrativoRepassePrePagoForm());
		cacheMyForm(getClass(), form);		
		return  mav;
	}
	
	
	/**
	 * Popula combo Holding Claro.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("holdingClaro")
	public List<SccOperadora> populaHoldingClaro() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.NULL_STRING);
		allValues.setDsOperadora("Selecione");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaHoldingClaro());
		return comboList;
	}
	
	/**
	 * Popula combo Operadora Externa
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.NULL_STRING);
		allValues.setDsOperadora("Selecione");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
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
	   * Inicializa o combo com produtos para pré-pago.
	   * Os valores desse combo são populados dinamicamente dependendo da operadora LD selecionada.
	   * @return
	   * @throws Exception
	   */
	  @ModelAttribute("produtos")
		public List<SccProdutoPrepago> populaProdutosPrePago() throws Exception
		{
			List<SccProdutoPrepago> comboList = new ArrayList<SccProdutoPrepago>();
			SccProdutoPrepago nullValue = new SccProdutoPrepago();
			nullValue.setNoProdutoPrepago("Selecione...");
			nullValue.setCdProdutoPrepago(BasicDAO.NULL_STRING);
			comboList.add(0,nullValue);
			return comboList;
		}
	  
	  
	  /**
	   * Método usado através de AJAX para atualizar a lista de produtos após a seleção de operadora LD e Holding Claro.
	   * @param cdEOT
	   * @param request
	   * @param response
	   * @throws Exception
	   */
	  @RequestMapping(value="/json/lista_produtos/{cdEOTHolding}/{cdEOTLD}" , method=RequestMethod.GET)
	  public void atualizaProdutos(@PathVariable("cdEOTHolding") String cdEOTHolding,@PathVariable("cdEOTLD") String cdEOTLD,HttpServletRequest request, HttpServletResponse response)
	  throws Exception
	  {
		  debug("Executando atualização de produtos pré-pago através de AJAX para eotClaro "+cdEOTHolding+" e LD "+cdEOTLD);
		  List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(cdEOTHolding, cdEOTLD, false);			  		
			  JSONObject jsonResponse = new JSONObject();					
		jsonResponse.put("0L","Selecione....");		
			for (int i=0;i<produtos.size();i++)
				{			
				jsonResponse.put(produtos.get(i).getCdProdutoPrepago().toString(), produtos.get(i).getNoProdutoPrepago());			
				}
			 response.setContentType("application/json");
		     response.getWriter().print(jsonResponse.toString());
	  }
}
