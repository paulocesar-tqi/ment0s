package com.claro.sccweb.controller.repasse.pre;

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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatoriosRepassePreValidator;
import com.claro.sccweb.decorator.RelApuracaoFechamentoPrePagoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

/**
 * Controller responsável pela funcionalidade de relatórios de 
 * repasse prá-pago. Esse cotroller gera os relatórios Sintético e Resume de Apuração.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pre/relatorios")
public class RelatoriosRepassePreController extends BaseFormController {

	private final RelatoriosRepassePreValidator validator = new RelatoriosRepassePreValidator();
	
	public static final String OPERACAO_NOVO = "NOVO";
	
	public static final String OPERACAO_EXCEL = "EXCEL";
	
	/**
	 * Operação padrão do controller.
	 */
	public static final String OPERACAO_PESQUISAR = "pesquisar";
	
	/**
	 * Tipo de relatório Sintético.
	 */
	public static final String TIPO_RELATORIOS_SINTETICO = "S";
	
	/**
	 * Tipo de relatório Resume de Apuração.
	 */
	public static final String TIPO_RELATORIOS_APURACAO = "A";
	
	 
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		binder.setValidator(validator);
	}
	
	/**
	 * Método handler de incialização da tela.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaSolicitacao(HttpServletRequest request, HttpServletResponse response) throws Exception {
		debug("Acessando page de relatórios de repasse pré-pago.");
		cleanDisplayTag(request);
		cleanMyFormCache(this.getClass());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("repasse_pre_relatorios_filtro");
		mav.addObject("filtro", new RelatoriosRepassePreForm());
		return mav;
	}
	
	/**
	 * A displaytag em sua paginação necessita submeter o form. Esse método é o handler para esse request.
	 * Se nada precisa mudar na tela o método retorna a mesma view.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @return Model and View de resultados da pesquisa já carregados.
	 * @throws Exception
	 */
    @RequestMapping(value="/tab1_sintetico" , method = RequestMethod.GET)
	public ModelAndView tab1_sintetico(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("repasse_pre_relatorios_sintetico");
		mav.addObject("filtro", getMyFormFromCache(getClass()));
    	return mav;
	}
    
    /**
	 * A displaytag em sua paginação necessita submeter o form. Esse método é o handler para esse request.
	 * Se nada precisa mudar na tela o método retorna a mesma view.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @return Model and View de resultados da pesquisa já carregados.
	 * @throws Exception
	 */
    @RequestMapping(value="/tab1_apurado" , method = RequestMethod.GET)
	public ModelAndView tab1_apuradao(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("repasse_pre_relatorios_apurado");
    	mav.addObject("filtro", getMyFormFromCache(getClass()));
    	return mav;
	}
	
	/**
	 * Handler para todas as requisições desse controller. 
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatoriosRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {	
		cacheMyForm(getClass(), form);
		debug("Tratando request RelatoriosRepassePreController com operação "+form.getOperacao());
		ModelAndView mav = new ModelAndView();
		
		
		if (bindingResult.hasErrors()) {
			mav.setViewName("repasse_pre_relatorios_filtro");
		} else if (form.getOperacao().equals(OPERACAO_PESQUISAR)) {
		
			if (form.getCdTipoRelatorio().equals(TIPO_RELATORIOS_SINTETICO)) {
		
				mav = geraSintetico(request, response, form, bindingResult, model); 
			} else if (form.getCdTipoRelatorio().equals(TIPO_RELATORIOS_APURACAO)) {
		
				mav = geraResumoApurado(request, response, form, bindingResult, model);
			}
		} else if (form.getOperacao().equals(OPERACAO_NOVO)) {
		
			mav =  novaSolicitacao(request, response);
		} else if (form.getOperacao().equals(OPERACAO_EXCEL)) {
		
			RelatoriosRepassePreForm cachedForm = (RelatoriosRepassePreForm)getMyFormFromCache(getClass());
			if (cachedForm.getCdTipoRelatorio().equals(TIPO_RELATORIOS_SINTETICO)) {
				mav = exportaExcelSintetico(request, response, cachedForm, bindingResult, model);
			} else if (cachedForm.getCdTipoRelatorio().equals(TIPO_RELATORIOS_APURACAO)) {
				mav = exportaExcelApurado(request, response, cachedForm, bindingResult, model);
			}
		}
		
		return mav;
	}
	
	/**
	 * Gera relatório sintético baseado no filtro informado.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private ModelAndView geraSintetico(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatoriosRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Gerando relatório sintético de repasse pré-pago : "+form.toString());
		cacheMyForm(getClass(), form);
		ModelAndView mav = new ModelAndView("repasse_pre_relatorios_sintetico");
		
		Date dataInicial = calculaDataInicialPeriodo(form.getMesRelatorio(), form.getAnoRelatorio());
		Date dataFinal = calculaDataFinalPeriodo(form.getMesRelatorio(), form.getAnoRelatorio());
		
		List<RelSinteticoServicoPrePagoView> parte2 = getServiceManager().getRepassePreService().geraRelatorioSinteticoService(form.getCdProdutoPrepago(),form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdStatusFechamento(), dataInicial, dataFinal);
		List<RelSinteticoFechamentoPrePagoView> parte1 = getServiceManager().getRepassePreService().geraRelatorioSintetico(form.getCdProdutoPrepago(),form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdStatusFechamento(), dataInicial, dataFinal);
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, parte1, request);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, parte2, request);
		
		return mav;
	}
	
	private ModelAndView geraResumoApurado(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatoriosRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Gerando relatório repasse de repasse pré-pago : "+form.toString());
		cacheMyForm(getClass(), form);
		ModelAndView mav = new ModelAndView("repasse_pre_relatorios_apurado");
		Date dataInicial = calculaDataInicialPeriodo(form.getMesRelatorio(), form.getAnoRelatorio());
		Date dataFinal = calculaDataFinalPeriodo(form.getMesRelatorio(), form.getAnoRelatorio());
		List<RelApuracaoFechamentoPrePagoView> parte1 = getServiceManager().getRepassePreService().geraRelatorioApuracao(form.getCdProdutoPrepago(),form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdStatusFechamento(), dataInicial, dataFinal);
		List<RelApuracaoFechamentoPrePagoViewDecorator> decoratorList = new ArrayList<RelApuracaoFechamentoPrePagoViewDecorator>(parte1.size());
		for (int i=0;i<parte1.size();i++) {
			RelApuracaoFechamentoPrePagoViewDecorator decorator = new RelApuracaoFechamentoPrePagoViewDecorator(parte1.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	private ModelAndView exportaExcelApurado(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatoriosRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Exportando relatório apurado de repasse pré-pago : "+form.toString());
		return new ModelAndView("relatorio_resumo_apurado_repasse_pre_excel");
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute("filtro") BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		return new ModelAndView("relatorio_sintetico_repasse_pre_excel");
	}

	public ModelAndView exportaExcelSintetico(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatoriosRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Exportando relatório sintético de repasse pré-pago : "+form.toString());
		return new ModelAndView("relatorio_sintetico_repasse_pre_excel");
	}
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}
	
	@ModelAttribute("status")
	public List<BasicStringItem> populaStatus() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("C", "Confirmado"));
		comboList.add(new BasicStringItem("N", "Não Confirmado"));
		return comboList;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoPrepago> populaProdutos() throws Exception {
		return getServiceManager().getProdutoPrepagoService().getAll();
	}
	
	@ModelAttribute("tipos")
	public List<BasicStringItem> populaTipos() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(TIPO_RELATORIOS_SINTETICO, "Sintético"));
		comboList.add(new BasicStringItem(TIPO_RELATORIOS_APURACAO, "Resumo de Apuração"));
		return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
	/**
	  * Método usado através de AJAX para atualizar a lista de produtos após a seleção de operadora LD e Holding Claro.
	  * @param cdEOT
	  * @param request
	  * @param response
	  * @throws Exception
	  */
	@RequestMapping(value="/json/lista_produtos/{cdEOTHolding}/{cdEOTLD}" , method=RequestMethod.GET)
	public void atualizaProdutos(@PathVariable("cdEOTHolding") String cdEOTHolding,@PathVariable("cdEOTLD") String cdEOTLD,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(cdEOTHolding, cdEOTLD, false);
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(BasicDAO.GET_ALL_STRING,"Todos");
		for (int i=0;i<produtos.size();i++) {
			jsonResponse.put(produtos.get(i).getCdProdutoPrepago().toString(), produtos.get(i).getNoProdutoPrepago());
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}
	
	
	
}
