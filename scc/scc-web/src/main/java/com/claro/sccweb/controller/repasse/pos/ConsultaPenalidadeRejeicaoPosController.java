package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.view.PenalidadeRejeicaoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ConsultaPenalidadeRejeicaoPosValidator;
import com.claro.sccweb.form.ConsultaRepassePosForm;


/**
 * Controller para a tela de consultas de penalidades por rejeicao.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pos/penalidade_rejeicao")
public class ConsultaPenalidadeRejeicaoPosController extends BaseFormController {
	
	private ConsultaPenalidadeRejeicaoPosValidator validator = new ConsultaPenalidadeRejeicaoPosValidator();
	
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);		
		super.initBinder(binder);
	}
	
	/**
	 * Após preencher o filtro , o usuário terá somente essa operação disponível.
	 */
	public static final String OPERACAO_PESQUISAR = "pesquisar";
	
	
	public static final String OPERACAO_EXCEL = "excel";
	
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
		mav.setViewName("repasse_pos_penalidade_rejeicao_filtro");
		return mav;
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
		ConsultaRepassePosForm cachedForm = (ConsultaRepassePosForm) getMyFormFromCache(this.getClass());
		if(cachedForm != null) {
			form.setListProdutos(cachedForm.getListProdutos());
		}
		String operacao = form.getOperacao();
		if (bindingResult.hasErrors()) {
			mav = new ModelAndView("repasse_pos_penalidade_rejeicao_filtro");
		} else if (operacao.equalsIgnoreCase(OPERACAO_PESQUISAR)) {
			mav = consultaPenalidadeRejeicao(request, response, form, bindingResult, model);
		}  else if (operacao.equalsIgnoreCase(OPERACAO_EXCEL)) {
			mav = excel(request, response, form, bindingResult, model);
		} 
		return mav;		  
	}
	
	
	/**
	 * Realiza a consulta das penalidades por rejeicao para a operadora LD / produto.
	 * @param request HTTP Request
	 * @param response HTTP Response
	 * @param form Form
	 * @param bindingResult Resultado do binding/validator.
	 * @param model Model
	 * @return Model and View com o resultado da pesquisa.
	 * @throws Exception
	 */
	private ModelAndView consultaPenalidadeRejeicao(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePosForm form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,form.getAnoRelatorio().intValue());
		calendar.set(Calendar.MONTH, form.getMesRelatorio().intValue()-1);
		calendar.set(Calendar.DAY_OF_MONTH, 16);
		form.setDtInicialRepasse(calendar.getTime());
		mav.addObject("filtro", form);

		List<PenalidadeRejeicaoView> rows = new ArrayList<PenalidadeRejeicaoView>();
		rows = getServiceManager().getRepasseService().pesquisaPenalidadeRejeicao(form);

		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, rows, request);
		cacheMyForm(getClass(), form);
		mav.setViewName("repasse_pos_penalidade_rejeicao_filtro");
		return mav;
	}
	
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  ConsultaRepassePosForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel para pesquisa penalidade rejeicao");
		ModelAndView mav = new ModelAndView("repasse_pos_penalidade_rejeicao_excel");
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
		return new ModelAndView("repasse_pos_penalidade_rejeicao_resultados");  
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
		SccProdutoCobilling nullValue = new SccProdutoCobilling();
		nullValue.setNoProdutoCobilling("Selecione...");
		nullValue.setCdProdutoCobilling(0L);
		produtos.add(0,nullValue);

		ConsultaRepassePosForm form = (ConsultaRepassePosForm) getMyFormFromCache(this.getClass());
		if(form == null)
			form = new ConsultaRepassePosForm();
		
		form.setListProdutos(produtos);
		cacheMyForm(getClass(), form);
		JSONObject jsonResponse = new JSONObject();					
		for (int i=0;i<produtos.size();i++) {			
			jsonResponse.put(produtos.get(i).getCdProdutoCobilling().toString(), produtos.get(i).getNoProdutoCobilling());			
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
		List<SccProdutoCobilling> comboList = null;
		
		ConsultaRepassePosForm form = (ConsultaRepassePosForm) getMyFormFromCache(this.getClass());
		if(form != null && form.getListProdutos() != null)
			comboList = form.getListProdutos();
		else {
			comboList = new ArrayList<SccProdutoCobilling>();
			SccProdutoCobilling nullValue = new SccProdutoCobilling();
			nullValue.setNoProdutoCobilling("Selecione...");
			nullValue.setCdProdutoCobilling(BasicDAO.NULL);
			comboList.add(0,nullValue);
		}
		return comboList;
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("OP", "Operadora"));
		comboList.add(new BasicStringItem("HO", "Holding"));
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


}
