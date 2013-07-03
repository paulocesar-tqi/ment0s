package com.claro.sccweb.controller.pagamento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroPagamentoValidator;
import com.claro.sccweb.decorator.PagamentoSAPDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroPagamentoForm;
import com.claro.sccweb.form.RelatorioPagamentoForm;
import com.claro.sccweb.service.PagamentoRepasseService;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;
import com.claro.sccweb.service.to.ConsultaRepassePreTO;

@Controller
@RequestMapping(value="/user/pagamento/relatorio")
public class RelatorioPagamentoController extends BaseFormController {

	public static final String OPERACAO_PEQUISAR = "pesquisar";
	public static final String OPERACAO_EXCEL = "excel";
	public static final String OPERACAO_ALTERAR_STATUS = "status";
	public static final String OPERACAO_UPDATE = "atualizar_status";

	
	private static final String FWD_REL_PAGAMENTO = "pagamento_relatorio_filtro";
	
	@Autowired
	private PagamentoRepasseService pagamentoRepasseService;
	

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
		mav.addObject("filtro", new CadastroPagamentoForm());
		mav.setViewName("cadastro_pagamento_filtro");
		return mav;
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
/*	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatorioPagamentoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = null;
		cacheMyForm(getClass(), form);
		List<PagamentoSAPDecorator> tabela = new ArrayList<PagamentoSAPDecorator>();
		if (form.getCdTipoContrato().equals(MODULO_POS_PAGO))
		{
			Date dtInicialPesquisa = getServiceManager().getPeriodicidadeService().calculaDataInicialPeriodo(form.getCdPeriodicidade(), form.getMesRepasse(), form.getAnoRepasse());
			Date dtFinalPesquisa = getServiceManager().getPeriodicidadeService().calculaDataFinalPeriodo(form.getCdPeriodicidade(), form.getMesRepasse(), form.getAnoRepasse());
			List<RepassePosPagoComposite> repassesPosPago = getServiceManager().getRepassePosService().pesquisaRepassesPosPago(form.getCdEOTLD(), form.getCdEOT(), form.getCdProdutoCobilling(), "C", dtInicialPesquisa, dtFinalPesquisa);
			if (repassesPosPago != null)
			{
				for (int i=0;i<repassesPosPago.size();i++)
				{
					SccPagamentoRepasse pagamentoRepasse = getServiceManager().getPagamentoRepasseService().getPagamentoByRepasse(repassesPosPago.get(i).getNuRepasse(), form.getCdEOT(), form.getCdEOTLD());
					PagamentoSAPDecorator decorator = new PagamentoSAPDecorator(pagamentoRepasse,repassesPosPago.get(i).getOperadoraClaro(),repassesPosPago.get(i).getOperadoraLD(), i);
					tabela.add(decorator);
				}
			}
		}
		else if (form.getCdTipoContrato().equals(MODULO_PRE_PAGO))
		{
			ConsultaRepassePreTO argumentoPesquisa = new ConsultaRepassePreTO();
			argumentoPesquisa.setAno(form.getAnoRepasse());
			argumentoPesquisa.setMes(form.getMesRepasse());
			argumentoPesquisa.setCdEOTClaro(form.getCdEOT());
			argumentoPesquisa.setCdEOTLD(form.getCdEOTLD());
			argumentoPesquisa.setHolding(false);
			argumentoPesquisa.setCdProdutoPrepago(null);
			List<RepassePrePagoComposite> repassesPrePago = getServiceManager().getRepassePreService().consultaRepassesPrePago(argumentoPesquisa);
			if (repassesPrePago != null)
			{
				for (int i=0;i<repassesPrePago.size();i++)
				{
					SccPagamentoRepasse pagamentoRepasse = getServiceManager().getPagamentoRepasseService().getPagamentoByRepasse(repassesPrePago.get(i).getSqPreFechamento(), form.getCdEOT(), form.getCdEOTLD());
					PagamentoSAPDecorator decorator = new PagamentoSAPDecorator(pagamentoRepasse,repassesPrePago.get(i).getOperadoraClaro(),repassesPrePago.get(i).getOperadoraLD(), i);
					tabela.add(decorator);
				}
			}
		}
		//storeInSession(getClass(), DISPLAY_TAG_SPACE_1, tabela, request);
		form.setListPagamentoSAPDecorator(tabela);
		mav = new ModelAndView(FWD_REL_PAGAMENTO, "filtro", form);
		
		return mav;
	}
*/	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("filtro")  RelatorioPagamentoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = null;
		cacheMyForm(getClass(), form);
		List<PagamentoSAPDecorator> tabela = new ArrayList<PagamentoSAPDecorator>();
		if (form.getCdTipoContrato().equals(MODULO_POS_PAGO))
		{
			Date dtInicialPesquisa = getServiceManager().getPeriodicidadeService().calculaDataInicialPeriodo(form.getCdPeriodicidade(), form.getMesRepasse(), form.getAnoRepasse());
			Date dtFinalPesquisa = getServiceManager().getPeriodicidadeService().calculaDataFinalPeriodo(form.getCdPeriodicidade(), form.getMesRepasse(), form.getAnoRepasse());
			List<RepassePosPagoComposite> repassesPosPago = getServiceManager().getRepassePosService().pesquisaRepassesPosPago(form.getCdEOTLD(), form.getCdEOT(), form.getCdProdutoCobilling(), "C", dtInicialPesquisa, dtFinalPesquisa);
			if (repassesPosPago != null)
			{
				for (int i=0;i<repassesPosPago.size();i++)
				{
					SccPagamentoRepasse pagamentoRepasse = getServiceManager().getPagamentoRepasseService().getPagamentoByRepasse(repassesPosPago.get(i).getNuRepasse(), form.getCdEOT(), form.getCdEOTLD());
					
					try {
						PagamentoSAPDecorator decorator = new PagamentoSAPDecorator(pagamentoRepasse,repassesPosPago.get(i).getOperadoraClaro(),repassesPosPago.get(i).getOperadoraLD(), i);
						
						tabela.add(decorator);
						
					} catch(Exception e){
						
						e.printStackTrace();
					}
					
				}
			}
		}
		else if (form.getCdTipoContrato().equals(MODULO_PRE_PAGO))
		{
			ConsultaRepassePreTO argumentoPesquisa = new ConsultaRepassePreTO();
			argumentoPesquisa.setAno(form.getAnoRepasse());
			argumentoPesquisa.setMes(form.getMesRepasse());
			argumentoPesquisa.setCdEOTClaro(form.getCdEOT());
			argumentoPesquisa.setCdEOTLD(form.getCdEOTLD());
			argumentoPesquisa.setHolding(false);
			argumentoPesquisa.setCdProdutoPrepago(null);
			List<RepassePrePagoComposite> repassesPrePago = getServiceManager().getRepassePreService().consultaRepassesPrePago(argumentoPesquisa);
			if (repassesPrePago != null)
			{
				for (int i=0;i<repassesPrePago.size();i++)
				{
					SccPagamentoRepasse pagamentoRepasse = getServiceManager().getPagamentoRepasseService().getPagamentoByRepasse(repassesPrePago.get(i).getSqPreFechamento(), form.getCdEOT(), form.getCdEOTLD());
					PagamentoSAPDecorator decorator = new PagamentoSAPDecorator(pagamentoRepasse,repassesPrePago.get(i).getOperadoraClaro(),repassesPrePago.get(i).getOperadoraLD(), i);
					tabela.add(decorator);
				}
			}
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, tabela, request);
		form.setListPagamentoSAPDecorator(tabela);
		mav = new ModelAndView(FWD_REL_PAGAMENTO, "filtro", form);
		
		return mav;
	}
	
	
	@RequestMapping(value="/execute" , method=RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("filtro")  RelatorioPagamentoForm form,BindingResult bindingResult,Model model) throws Exception {
		
		ModelAndView mav = null;
		
		String operacao = form.getOperacao();
		if (bindingResult.hasErrors()) {
			mav = new ModelAndView(FWD_REL_PAGAMENTO);
		} else if (operacao.equalsIgnoreCase(OPERACAO_PEQUISAR)) {
			mav = pesquisar(request, response, form, bindingResult, model);
		} else if (operacao.equalsIgnoreCase(OPERACAO_UPDATE)) {
			mav = atualizarDados(request, response, form, bindingResult, model); 
		}
		return mav;		  
	}
	
	
	//@RequestMapping(value="/updateEntity", method=RequestMethod.POST)
	private ModelAndView atualizarDados(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("filtro")  RelatorioPagamentoForm form,BindingResult bindingResult,Model model) throws Exception {

		List<SccPagamentoRepasse> pagamentoRepasseList = new ArrayList<SccPagamentoRepasse>();
		
		Long nuRepasse[] = form.getNuRepasse();
		
		List lancadosSelecionados = new ArrayList();
		if(form.getLancadosSelecionados() != null){
			lancadosSelecionados = Arrays.asList(form.getLancadosSelecionados());
		}
		
		
		for (int i = 0; i < nuRepasse.length; i++) {
			SccPagamentoRepasse pagamentoRepasse = new SccPagamentoRepasse();
			
			pagamentoRepasse.getId().setNuRepasse(nuRepasse[i]);
			
			if(lancadosSelecionados.contains(pagamentoRepasse.getId().getNuRepasse())){
				pagamentoRepasse.setFgContabAutomatica("S");
				
			} else {
				pagamentoRepasse.setFgContabAutomatica("N");
			}
			
			pagamentoRepasseList.add(pagamentoRepasse);
		}		
		
		for (SccPagamentoRepasse pagamentoRepasse : pagamentoRepasseList) {		
			
			this.pagamentoRepasseService.updatePagamentoRepasse(pagamentoRepasse.getId().getNuRepasse(), pagamentoRepasse.getFgContabAutomatica());
		}
		ModelAndView mav = new ModelAndView(FWD_REL_PAGAMENTO, "filtro", form);			
		return mav;
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

	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	@ModelAttribute("tiposContrato")
	public List<BasicStringItem> populaTiposContrato() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(MODULO_POS_PAGO, "Pós Pago"));
		comboList.add(new BasicStringItem(MODULO_PRE_PAGO, "Pré Pago"));
		return comboList;
	}


	/**
	 * Inicializa o combo com produtos para pré-pago.
	 * Os valores desse combo são populados dinamicamente dependendo da operadora LD selecionada.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("produtosPrePago")
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
	@RequestMapping(value="/json/lista_produtos_pos/{cdEOTLD}" , method=RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOTLD") String cdEOTLD,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") CadastroPagamentoForm form) throws Exception
	{		
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOTLD);		
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
	 * Método usado através de AJAX para atualizar a lista de produtos após a seleção de operadora LD.
	 * @param cdEOT
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/json/lista_produtos_pre/{cdEOT}" , method=RequestMethod.GET)
	public void atualizaProdutos(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response)
			throws Exception
			{
		List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(BasicDAO.GET_ALL_STRING, cdEOT, false);			  		
		JSONObject jsonResponse = new JSONObject();					
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<produtos.size();i++)
		{			
			jsonResponse.put(produtos.get(i).getCdProdutoPrepago().toString(), produtos.get(i).getNoProdutoPrepago());			
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

	public void setPagamentoRepasseService(
			PagamentoRepasseService pagamentoRepasseService) {
		this.pagamentoRepasseService = pagamentoRepasseService;
	}



	
	

}
