package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
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
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.SolicitacaoRepassePosPagoValidator;
import com.claro.sccweb.form.SolicitacaoRepassePosPagoForm;

/**
 * Controller para solicitação de relatório de repasse pós-pago.
 * Com esse controller é possível obter a lista de relatórios de repasse já executados , a executar e executando.
 * No início do processamento três queires são executadas para montar as tables com esses três estados (TO LOAD , LOADING , LOADED).
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pos/solicitacao")
public class SolicitacaoRepassePosPagoController extends BaseFormController {
	
	private SolicitacaoRepassePosPagoValidator validator = new SolicitacaoRepassePosPagoValidator();
	
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		super.initBinder(binder);
	}
	
	/**
	 * Carrega as três listas de relatórios de repasse pós-pago.
	 * @return Model and View da tela de solicitação de relatórios de repasse.
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaSolicitacao(HttpServletRequest request, HttpServletResponse response) throws Exception {
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("repasse_pos_solicitacao");
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_1, getServiceManager().getRepasseService().pesquisaRepassesAgendados("REPASSE"));
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_2, getServiceManager().getRepasseService().pesquisaRepassesProcessando("REPASSE",50));
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_3, getServiceManager().getRepasseService().pesquisaRepassesProcessados("REPASSE",50));
		mav.addObject("form", new SolicitacaoRepassePosPagoForm());
		return mav;
	}
	
	@RequestMapping(value="/delete/{noreq}" , method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("noreq") String noreq,HttpServletRequest request, HttpServletResponse response) throws Exception {
		getServiceManager().getRepasseService().deleteSolicitacaoRepasse(noreq);
		return novaSolicitacao(request, response);
	}
	
	/**
	 * Processa a requisição para criação de um novo relatório de repasse pós-pago.
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insere" , method = RequestMethod.POST)
	public ModelAndView insere(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("form") SolicitacaoRepassePosPagoForm form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (form.getCdProdutoCobilling() == null || form.getCdProdutoCobilling() == -999 || form.getCdPeriodicidadeRepasse() == -999 || form.getAnoRelatorio() == null) {
			mav.setViewName("repasse_pos_solicitacao");
		} else {
			debug("Processando requisição para criação de novo relatório de repasse pós-pago.");
			if (bindingResult.hasErrors()) {
				debug("Impossível processar requisição para criação de novo relatório de repasse pós-pago : Erros de validação (binding).");
				mav.setViewName("repasse_pos_solicitacao");
			} else {
				form.setUsuarioManutencao(getSessionDataManager().getUserPrincipal());
				int criados = getServiceManager().getRepasseService().insereSolicitacaoRepassePos(form);
				SolicitacaoRepassePosPagoForm responseForm = new SolicitacaoRepassePosPagoForm();
				responseForm.setCriados(criados);
				form.setOperacao("CRIADOS");
				mav.setViewName("repasse_pos_solicitacao");
				request.getSession().setAttribute(DISPLAY_TAG_SPACE_1, getServiceManager().getRepasseService().pesquisaRepassesAgendados("REPASSE"));
				request.getSession().setAttribute(DISPLAY_TAG_SPACE_2, getServiceManager().getRepasseService().pesquisaRepassesProcessando("REPASSE",50));
				request.getSession().setAttribute(DISPLAY_TAG_SPACE_3, getServiceManager().getRepasseService().pesquisaRepassesProcessados("REPASSE",50));
				mav.addObject("form", responseForm);
			}
		}
		return mav;
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
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
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
	public void pesquisaProdutosLD(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") SolicitacaoRepassePosPagoForm form) throws Exception {		
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
	
}
