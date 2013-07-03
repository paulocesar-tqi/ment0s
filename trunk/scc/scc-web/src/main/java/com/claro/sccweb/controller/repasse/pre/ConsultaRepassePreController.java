package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ConsultaRepassePreValidator;
import com.claro.sccweb.decorator.ConsultaRepassePrePagoDecorator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreItemDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ConsultaRepassePreForm;
import com.claro.sccweb.service.RelatorioRepasseService;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;

/**
 * Controller para consulta e efetiva��o de repasses pr�-pago.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pre/consulta")
public class ConsultaRepassePreController extends BaseOperationController<ConsultaRepassePreForm> {

	private final ConsultaRepassePreValidator validator = new ConsultaRepassePreValidator();

	/**
	 * Ap�s preencher o filtro , o usu�rio ter� somente essa opera��o dispon�vel.
	 */
	public static final String OPERACAO_PESQUISAR = "pesquisar";


	/**
	 * Cada repasse n�o cancelado ou confirmado (cdStatusRepasse = null) permite configurar seu status (cdStatusRepasse).
	 */
	public static final String OPERACAO_MUDAR_STATUS = "status";

	/**
	 * Opera��o dispon�vel na tela de resultados da pesquisa.
	 */
	public static final String OPERACAO_NOVO = "new";

	/**
	 * Opera��o realizada quando o usu�rio retorna do ajuste de valores/demonstrativo para a tela de resultados de pesquisa.
	 */
	public static final String OPERACAO_VOLTAR = "voltar";

	/**
	 * Opera��o dispon�vel na tela de resultados e que confirma as modifica��es
	 * feitas na coluna "Confirma��o".
	 */
	public static final String OPERACAO_EFETIVAR = "efetivar";

	/**
	 * Opera��o executada quando o usu�rio clica no link de uma linha da tabela de resultados.
	 */
	public static final String OPERACAO_SELECIONAR_LINHA = "selecionar";

	public static final String OPERACAO_EXIBIR_DEMONSTRATIVO =  "DEMONSTRATIVO";
	
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		super.initBinder(binder);
	}
	
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		cleanSession(getClass(), request, "DEMONSTRATIVO_OPERADORA");
		return super.iniciar(request, response);
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
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
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
		}else if(operacao.equalsIgnoreCase(OPERACAO_SELECIONAR_LINHA)){
			mav = selecionar(request, response, form, bindingResult, model);
		}
		return mav;		  
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
	private ModelAndView consultaRepasses(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
		List<ConsultaRepassePrePagoDecorator> resultados = new ArrayList<ConsultaRepassePrePagoDecorator>();
		ModelAndView mav = new ModelAndView(getViewName());
		cleanSession(getClass(), request, "DEMONSTRATIVO_OPERADORA");

		form.setEfetivarLigado("N");
		int index = 0;		  
		List<RepassePrePagoComposite> repasses = getServiceManager().getRepassePreService().consultaRepassesPrePago(form.getTo());
		for (int i=0;i<repasses.size();i++) {
			if ((form.getEfetivarLigado().equals("N")) && (repasses.get(i).getStatus() != null) && (!repasses.get(i).getStatus().equalsIgnoreCase("S"))) {
				form.setEfetivarLigado("S");
			}				  
			ConsultaRepassePrePagoDecorator decorator = new ConsultaRepassePrePagoDecorator(repasses.get(i), index);
			resultados.add(decorator);
			index++;
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, resultados, request);
		cacheMyForm(getClass(), form);
	
		return mav;
	}
	
	
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
		mav.addObject("filtro", new ConsultaRepassePreForm());
		mav.setViewName("repasse_pre_consulta_filtro");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	private ModelAndView selecionar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePreForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanSession(getClass(), request, "DEMONSTRATIVO_OPERADORA");
		ModelAndView mav = new ModelAndView(getViewName());
		
		List<ConsultaRepassePrePagoDecorator> tabela = (List<ConsultaRepassePrePagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		List<DemonstrativoRepassePreItemDecorator> demonstrativo = getServiceManager().getRepassePreService().geraLinhasDemonstrativo(tabela.get(form.getItemSelecionado()).getEntity());
		storeInSession(getClass(),"DEMONSTRATIVO_OPERADORA", demonstrativo, request);
		form.setOperacao(OPERACAO_EXIBIR_DEMONSTRATIVO);
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	/**
	 * Popula o combo de status de repasse pr�-pago.
	 * @return
	 */
	@ModelAttribute("status")
	public List<BasicStringItem> populaStatus() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("C", "Confirmado"));
		comboList.add(new BasicStringItem("N", "Cancelado"));
		return comboList;
	}
	
	/**
	 * Popula o combo com os meses do ano.
	 * @return
	 */
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
	/**
	 * Inicializa o combo com produtos para pr�-pago.
	 * Os valores desse combo s�o populados dinamicamente dependendo da operadora LD selecionada.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("produtos")
	public List<SccProdutoPrepago> populaProdutosCobilling() throws Exception {
		List<SccProdutoPrepago> comboList = new ArrayList<SccProdutoPrepago>();
		SccProdutoPrepago nullValue = new SccProdutoPrepago();
		nullValue.setNoProdutoPrepago("Selecione...");
		nullValue.setCdProdutoPrepago(BasicDAO.NULL_STRING);
		comboList.add(0,nullValue);
		return comboList;
	}
	
	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setCdEot(BasicDAO.NULL_STRING);
		nullValue.setDsOperadora("Selecione...");
		comboList.add(0,nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	/**
	 * M�todo usado atrav�s de AJAX para atualizar a lista de produtos ap�s a sele��o de operadora LD.
	 * @param cdEOT
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/json/lista_produtos/{cdEOT}" , method=RequestMethod.GET)
	public void atualizaProdutos(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(BasicDAO.GET_ALL_STRING, cdEOT, false);			  		
		JSONObject jsonResponse = new JSONObject();					
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<produtos.size();i++) {			
			jsonResponse.put(produtos.get(i).getCdProdutoPrepago().toString(), produtos.get(i).getNoProdutoPrepago());			
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonResponse.toString());
	}
	
	/**
	 * Handler para quando o usu�rio muda o valor "Confirma��o" na tela de resultados de consulta de repasses.
	 * Ao click do usu�rio , uma solicita��o AJAX � enviada a esse m�todo.
	 * O item modificado (Confirmado ou Cancelado) � marcado como modificado e o atributo novoStatus recebe o valor 
	 * selecionado pelo usu�rio ("C" ou "N").
	 * O item modificado e o novo valor chegam como par�mtro {index} no formato $V$I em que $V = Novo valor e $I a posi��o do item modificado 
	 * dentro da List  DISPLAY_TAG_SPACE_1.
	 * 
	 * Para que o usu�rio possa manter o status nulo em caso de ter selecionado por engano um repasse , adicionei o status 'E' para indicar nulo.
	 * 
	 * Exemplo: C4 = Item 4 da lista recebe status 'C'.
	 * 		  E5 = Item 5 da lista recebe status 'E' (nulo)
	 * @param index
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/json/mudaStatus/{rownum}" , method=RequestMethod.GET)
	public void mudaStatus(@PathVariable("rownum") String rownum,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		List<ConsultaRepassePrePagoDecorator> items = (List<ConsultaRepassePrePagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		String novoStatus = rownum.substring(0, 1);		  
		items.get(Integer.parseInt(rownum.substring(1))).setNovoStatus(novoStatus);
		items.get(Integer.parseInt(rownum.substring(1))).setModificado(true);		  
	}
	
	/**
	 * Realizar a modifica��o no status dos repasses conforme valores dos radio buttons. Cada item da lista de resultados
	 * que est� marcardo como modificado=true ser� agora persistido na base de dados e o pagamento estar� confirmado.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form
	 * @param bindingResult Resultado do binding/validator.
	 * @param model Model
	 * @return Model and View com refresh na tela de resultados.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView efetivar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		List<ConsultaRepassePrePagoDecorator> tabelaRepasses = (List<ConsultaRepassePrePagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		if (tabelaRepasses != null) {
			String usuario = getSessionDataManager().getUserPrincipal();
			for (int i=0;i<tabelaRepasses.size();i++) {
				if (tabelaRepasses.get(i).isModificado()) {
					String novoStatus = tabelaRepasses.get(i).getNovoStatus();
					if (novoStatus.equals(SccRepasse.STATUS_CONFIRMADO)) {
						getServiceManager().getPagamentoRepasseService().realizaPagamentoRepassePre(tabelaRepasses.get(i).getRow(), usuario);
					} else if (novoStatus.equals(SccRepasse.STATUS_CANCELADO)) {
						getServiceManager().getPagamentoRepasseService().cancelaPagamentoRepasse(tabelaRepasses.get(i).getRow());
					}
				}
			}
		}
		return consultaRepasses(request, response,(ConsultaRepassePreForm)getMyFormFromCache(getClass()) , bindingResult, model);
	}
	
	public String getViewName() {
		return "repasse_pre_consulta_filtro";
	}
	
	protected ConsultaRepassePreForm getForm() {
		return new ConsultaRepassePreForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
}
