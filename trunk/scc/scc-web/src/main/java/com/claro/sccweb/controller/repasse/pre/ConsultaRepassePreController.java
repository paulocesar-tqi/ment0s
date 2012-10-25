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
import com.claro.sccweb.service.composite.RepassePrePagoComposite;

/**
 * Controller para consulta e efetivação de repasses pré-pago.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pre/consulta")
public class ConsultaRepassePreController extends BaseOperationController<ConsultaRepassePreForm> {

	private final ConsultaRepassePreValidator validator = new ConsultaRepassePreValidator();

	/**
	 * Após preencher o filtro , o usuário terá somente essa operação disponível.
	 */
	public static final String OPERACAO_PESQUISAR = "pesquisar";


	/**
	 * Cada repasse não cancelado ou confirmado (cdStatusRepasse = null) permite configurar seu status (cdStatusRepasse).
	 */
	public static final String OPERACAO_MUDAR_STATUS = "status";

	/**
	 * Operação disponível na tela de resultados da pesquisa.
	 */
	public static final String OPERACAO_NOVO = "new";

	/**
	 * Operação realizada quando o usuário retorna do ajuste de valores/demonstrativo para a tela de resultados de pesquisa.
	 */
	public static final String OPERACAO_VOLTAR = "voltar";

	/**
	 * Operação disponível na tela de resultados e que confirma as modificações
	 * feitas na coluna "Confirmação".
	 */
	public static final String OPERACAO_EFETIVAR = "efetivar";

	/**
	 * Operação executada quando o usuário clica no link de uma linha da tabela de resultados.
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
	 * Consulta na base de dados os repasses de pré-pago conforme filtro informado pelo usuário.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("filtro") BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		cleanSession(getClass(), request, "DEMONSTRATIVO_OPERADORA");
		ConsultaRepassePreForm form = (ConsultaRepassePreForm)_form;
	
		if (form.getTo().getCdEOTLD() == null || form.getTo().getCdEOTLD().equals(BasicDAO.NULL_STRING) || form.getTo().getCdProdutoPrepago() == null || form.getTo().getCdProdutoPrepago().equals(BasicDAO.NULL_STRING) || form.getTo().getAno() == null) {
			System.out.println("");
		} else{
			form.setEfetivarLigado("N");
			List<ConsultaRepassePrePagoDecorator> resultados = new ArrayList<ConsultaRepassePrePagoDecorator>();		  
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
		}
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView selecionar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  BaseForm _form,BindingResult bindingResult,Model model) throws Exception {
		cleanSession(getClass(), request, "DEMONSTRATIVO_OPERADORA");
		ModelAndView mav = new ModelAndView(getViewName());
		ConsultaRepassePreForm form = (ConsultaRepassePreForm)_form;
		List<ConsultaRepassePrePagoDecorator> tabela = (List<ConsultaRepassePrePagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		List<DemonstrativoRepassePreItemDecorator> demonstrativo = getServiceManager().getRepassePreService().geraLinhasDemonstrativo(tabela.get(form.getItemSelecionado()).getEntity());
		storeInSession(getClass(),"DEMONSTRATIVO_OPERADORA", demonstrativo, request);
		_form.setOperacao(OPERACAO_EXIBIR_DEMONSTRATIVO);
		mav.addObject(FORM_NAME, _form);
		return mav;
	}
	
	/**
	 * Popula o combo de status de repasse pré-pago.
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
	 * Inicializa o combo com produtos para pré-pago.
	 * Os valores desse combo são populados dinamicamente dependendo da operadora LD selecionada.
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
	 * Método usado através de AJAX para atualizar a lista de produtos após a seleção de operadora LD.
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/json/mudaStatus/{index}" , method=RequestMethod.GET)
	public void mudaStatus(@PathVariable("index") String index,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		List<ConsultaRepassePrePagoDecorator> items = (List<ConsultaRepassePrePagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		String novoStatus = index.substring(0, 1);		  
		items.get(Integer.parseInt(index.substring(1))).setNovoStatus(novoStatus);
		items.get(Integer.parseInt(index.substring(1))).setModificado(true);		  
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
	@SuppressWarnings("unchecked")
	public ModelAndView efetivar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		List<ConsultaRepassePrePagoDecorator> tabelaRepasses = (List<ConsultaRepassePrePagoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		if (tabelaRepasses != null) {
			String usuario = getSessionDataManager().getUserPrincipal();
			for (int i=0;i<tabelaRepasses.size();i++) {
				if (tabelaRepasses.get(i).isModificado()) {
					String novoStatus = tabelaRepasses.get(i).getNovoStatus();
					if (novoStatus.equals(SccRepasse.STATUS_CONFIRMADO)) {
						getServiceManager().getPagamentoRepasseService().realizaPagamentoRepasse(tabelaRepasses.get(i).getRow(), usuario);
					} else if (novoStatus.equals(SccRepasse.STATUS_CANCELADO)) {
						getServiceManager().getPagamentoRepasseService().cancelaPagamentoRepasse(tabelaRepasses.get(i).getRow());
					}
				}
			}
		}
		return pesquisar(request, response,(ConsultaRepassePreForm)getMyFormFromCache(getClass()) , bindingResult, model);
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
