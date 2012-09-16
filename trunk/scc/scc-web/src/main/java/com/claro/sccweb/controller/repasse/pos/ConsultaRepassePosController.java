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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.ConsultaRepassePosValidator;
import com.claro.sccweb.decorator.ConsultaRepassePosPagoDecorator;
import com.claro.sccweb.form.ConsultaRepassePosForm;
import com.claro.sccweb.form.DemonstrativoRepassePosPagoForm;
import com.claro.sccweb.service.RelatorioRepasseService;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;


/**
 * Controller para a tela de consultas de repaase e mudança de status.
 * Essa tela é também o início da seleção de um repasse para edição de valores nos itens do repasse.
 *
 * O filtro dessa tela basea-se na operadora LD e não na operadora Claro como o filtro do Demonstrativo de Repasse {@link DemonstrativoRepassePosPagoForm}.
 * Após a seleção da LD / Produto e Período será mostrada uma tabela com a lista de todos os repasses envolvendo operadoras Claro dentro desse filtro. 
 */
@Controller
@RequestMapping(value="/user/repasse/pos/consulta")
public class ConsultaRepassePosController extends BaseFormController {
	
	private ConsultaRepassePosValidator validator = new ConsultaRepassePosValidator();
	
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);		
		super.initBinder(binder);
	}
	
	/**
	 * Após preencher o filtro , o usuário terá somente essa operação disponível.
	 */
	public static final String OPERACAO_PESQUISAR = "pesquisar";
	
	/**
	 * Cada repasse não cancelado ou confirmado (cdStatusRepasse = null) permite ainda ajustes.
	 */
	public static final String OPERACAO_AJUSTAR = "ajustar";
	
	/**
	 * Cada repasse não cancelado ou confirmado (cdStatusRepasse = null) permite configurar seu status (cdStatusRepasse).
	 */
	public static final String OPERACAO_MUDAR_STATUS = "status";
	
	/**
	 * Operação disponível na tela de resultados da pesquisa.
	 */
	public static final String OPERACAO_VOLTAR = "new";
	
	/**
	 * Operação disponível na tela de resultados e que confirma as modificações
	 * feitas na coluna "Confirmação".
	 */
	public static final String OPERACAO_EFETIVAR = "efetivar";
	
	/**
	 * Handler de entrada na funcionaldidade. 
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @return Model ane View com o filtro da consulta.
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaConsulta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		cleanDisplayTag(request);
		cleanMyFormCache(getClass());
		ModelAndView mav = new ModelAndView();
		mav.addObject("filtro", new ConsultaRepassePosForm());
		mav.setViewName("repasse_pos_consulta_filtro");
		return mav;
	}
	
	/**
	 * Método handler para quando o usuário retorna à tela de resultados usando o botão Voltar.
	 * Esse retorno ocorre a partir da tela de ajuste de repasses.
	 * os repasses devem ser todos recarregados da base já que pode ter havido modificações.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/back" , method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		return consultaRepasses(request, response,(ConsultaRepassePosForm)getMyFormFromCache(getClass()) , null, null);
	}
	
	/**
	 * Método handler para esse controller.
	 * @param request HTTP Request.
	 * @param response HTTp Response.
	 * @param form Form.
	 * @param bindingResult Resultado do binding/validator.
	 * @param model Model
	 * @return Model and View.
	 * @throws Exception
	 */
	@RequestMapping(value="/execute" , method=RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePosForm form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = null;
		String operacao = form.getOperacao();
		if (bindingResult.hasErrors()) {
			mav = new ModelAndView("repasse_pos_consulta_filtro");
		} else if (operacao.equalsIgnoreCase(OPERACAO_PESQUISAR)) {
			mav = consultaRepasses(request, response, form, bindingResult, model);
		} else if (operacao.equalsIgnoreCase(OPERACAO_VOLTAR)) {
			mav = novaConsulta(request, response); 
		} else if (operacao.equalsIgnoreCase(OPERACAO_EFETIVAR)) {
			mav = efetivar(request, response, form, bindingResult, model);
		}
		return mav;		  
	}
	
	/**
	 * Realizar a modificação no status dos repasses conforme valores dos radio buttons. Cada item da lista de resultados
	 * que está marcardo como modificado=true será agora persistido na base de dados e o pagamento estará confirmado.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form
	 * @param bindingResult Resultado do binding/validator.
	 * @param model Model
	 * @return Model and View com refresh na tela de resultados.
	 * @throws Exception
	 */
	private ModelAndView efetivar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePosForm form,BindingResult bindingResult,Model model) throws Exception {
		List<ConsultaRepassePosPagoDecorator> tabelaRepasses = (List<ConsultaRepassePosPagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		if (tabelaRepasses != null) {
			for (int i=0;i<tabelaRepasses.size();i++) {
				if (tabelaRepasses.get(i).isModificado()) {
					String novoStatus = tabelaRepasses.get(i).getNovoStatus();
					if (novoStatus.equals(SccRepasse.STATUS_CONFIRMADO)) {
						getServiceManager().getPagamentoRepasseService().realizaPagamentoRepasse(tabelaRepasses.get(i).getRow());
					} else if (novoStatus.equals(SccRepasse.STATUS_CANCELADO)) {
						getServiceManager().getPagamentoRepasseService().cancelaPagamentoRepasse(tabelaRepasses.get(i).getRow(), getSessionDataManager().getUserPrincipal());
					}
				}
			}
		}
		return consultaRepasses(request, response,(ConsultaRepassePosForm)getMyFormFromCache(getClass()) , bindingResult, model);
	}
	
	/**
	 * Realiza a consulta dos repasses para a operadora LD / produto / preíodo selecionados.
	 * Para cada operadora Claro será executado o método do serviço {@link RelatorioRepasseService}
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form
	 * @param bindingResult Resultado do binding/validator.
	 * @param model Model
	 * @return Model and View com o resultado da pesquisa.
	 * @throws Exception
	 */
	private ModelAndView consultaRepasses(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePosForm form,BindingResult bindingResult,Model model) throws Exception {
		List<ConsultaRepassePosPagoDecorator> resultados = new ArrayList<ConsultaRepassePosPagoDecorator>();
		ModelAndView mav = new ModelAndView();
		List<SccOperadora> operadorasClaro = getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro();
		Date dtInicialPesquisa = getServiceManager().getPeriodicidadeService().calculaDataInicialPeriodo(form.getCdPeriodicidade(), form.getMesRelatorio(), form.getAnoRelatorio());
		Date dtFinalPesquisa = getServiceManager().getPeriodicidadeService().calculaDataFinalPeriodo(form.getCdPeriodicidade(), form.getMesRelatorio(), form.getAnoRelatorio());
		form.setDtInicialRepasse(dtInicialPesquisa);
		form.setDtFinalRepasse(dtFinalPesquisa);
		int index = 0;
		mav.addObject("filtro", form);
		for (int i=0;i<operadorasClaro.size();i++) {
			form.setCdEOTClaro(operadorasClaro.get(i).getCdEot());
			RepassePosPagoComposite composite = getServiceManager().getRepasseService().carregaDadosRepasse(form);
			if (composite != null) {				  
				ConsultaRepassePosPagoDecorator decorator = new ConsultaRepassePosPagoDecorator(composite, index);
				decorator.setAnoMes(form.getAnoRelatorio()+"/"+form.getMesRelatorio());
				resultados.add(decorator);  
				index++;
			}			  
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, resultados, request);
		cacheMyForm(getClass(), form);
		mav.setViewName("repasse_pos_consulta_resultados");
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
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("repasse_pos_consulta_resultados");  
	}
	
	/**
	 * Método usado através de AJAX para atualizar a lista de produtos após a seleção de operadora LD.
	 * @param cdEOT
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/json/lista_produtos/{cdEOT}" , method=RequestMethod.GET)
	public void atualizaProdutos(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOT);		
		JSONObject jsonResponse = new JSONObject();					
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<produtos.size();i++) {			
			jsonResponse.put(produtos.get(i).getCdProdutoCobilling().toString(), produtos.get(i).getNoProdutoCobilling());			
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}
	
	/**
	 * Handler para quando o usuário muda o valor "Confirmação" na tela de resultados de consulta de repasses.
	 * Ao click do usuário , uma solicitação AJAX é enviada a esse método.
	 * O item modificado (Confirmado ou Cancelado) é marcado como modificado e o atributo novoStatus recebe o valor 
	 * selecionado pelo usuário ("C" ou "N").
	 * O item modificado e o novo valor chegam como parêmtro {index} no formato $V$I em que $V = Novo valor e $I a posição do item modificado 
	 * dentro da List  DISPLAY_TAG_SPACE_1.
	 * 
	 * Para que o usuário possa manter o status nulo em caso de ter selecionado por engano um repasse , adicionei o status 'E' para indicar nulo.
	 * 
	 * Exemplo: C4 = Item 4 da lista recebe status 'C'.
	 * 		  E5 = Item 5 da lista recebe status 'E' (nulo)
	 * @param index
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/json/mudaStatus/{index}" , method=RequestMethod.GET)
	public void mudaStatus(@PathVariable("index") String index,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		List<ConsultaRepassePosPagoDecorator> items = (List<ConsultaRepassePosPagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		String novoStatus = index.substring(0, 1);		  
		items.get(Integer.parseInt(index.substring(1))).setNovoStatus(novoStatus);
		items.get(Integer.parseInt(index.substring(1))).setModificado(true);		  
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
	public void pesquisaPeriodos(@PathVariable("cdProduto") Long cdProduto,@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response) throws Exception {			
		List<SccPeriodicidadeRepasse> repasses = getServiceManager().getContratoService().pesquisaPeriodicidadeRepasse(cdEOT, cdProduto);
		JSONObject jsonResponse = new JSONObject();					
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<repasses.size();i++) {			
			jsonResponse.put(repasses.get(i).getCdPeriodicidadeRepasse().toString(), repasses.get(i).getNoPeriodicidadeRepasse());			
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}
	
	/**
	 * Popula o combo de meses da tela de filtro.
	 */
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
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
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception {
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
	public List<SccPeriodicidadeRepasse> populaPeriodosCobilling() throws Exception {
		List<SccPeriodicidadeRepasse> comboList = new ArrayList<SccPeriodicidadeRepasse>();
		SccPeriodicidadeRepasse nullValue = new SccPeriodicidadeRepasse();
		nullValue.setCdPeriodicidadeRepasse(BasicDAO.NULL);
		nullValue.setNoPeriodicidadeRepasse("Selecione...");
		comboList.add(0,nullValue);
		return comboList;
	}
	
}
