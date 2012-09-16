package com.claro.sccweb.controller.pagamento;

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
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.decorator.PagamentoSAPDecorator;
import com.claro.sccweb.decorator.PesquisaPagamentosDecorator;
import com.claro.sccweb.form.CadastroPagamentoForm;
import com.claro.sccweb.form.RelatorioPagamentoForm;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;
import com.claro.sccweb.service.to.ConsultaRepassePreTO;

@Controller
@RequestMapping(value="/user/pagamento/relatorio")
public class RelatorioPagamentoController extends BaseFormController {

	public static final String OPERACAO_PEQUISAR = "pesquisar";
	public static final String OPERACAO_EXCEL = "excel";

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
	@RequestMapping(value="/execute" , method = RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  RelatorioPagamentoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView("cadastro_pagamento_filtro");
		cacheMyForm(getClass(), form);
		List<PagamentoSAPDecorator> tabela = new ArrayList<PagamentoSAPDecorator>();
		if (form.getCdTipoContrato().equals(MODULO_POS_PAGO))
		{
			Date dtInicialPesquisa = getServiceManager().getPeriodicidadeService().calculaDataInicialPeriodo(form.getCdPeriodicidade(), form.getMesRepasse(), form.getAnoRepasse());
			Date dtFinalPesquisa = getServiceManager().getPeriodicidadeService().calculaDataFinalPeriodo(form.getCdPeriodicidade(), form.getMesRepasse(), form.getAnoRepasse());
			List<RepassePosPagoComposite> repassesPosPago = getServiceManager().getRepassePosService().pesquisaRepassesPosPago(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdProdutoCobilling(), "C", dtInicialPesquisa, dtFinalPesquisa);
			if (repassesPosPago != null)
			{
				for (int i=0;i<repassesPosPago.size();i++)
				{
					SccPagamentoRepasse pagamentoRepasse = getServiceManager().getPagamentoRepasseService().getPagamentoByRepasse(repassesPosPago.get(i).getNuRepasse(), form.getCdEOTClaro(), form.getCdEOTLD());
					PagamentoSAPDecorator decorator = new PagamentoSAPDecorator(pagamentoRepasse,repassesPosPago.get(i).getOperadoraClaro(),repassesPosPago.get(i).getOperadoraLD());
					tabela.add(decorator);
				}
			}
		}
		else if (form.getCdTipoContrato().equals(MODULO_PRE_PAGO))
		{
			ConsultaRepassePreTO argumentoPesquisa = new ConsultaRepassePreTO();
			argumentoPesquisa.setAno(form.getAnoRepasse());
			argumentoPesquisa.setMes(form.getMesRepasse());
			argumentoPesquisa.setCdEOTClaro(form.getCdEOTClaro());
			argumentoPesquisa.setCdEOTLD(form.getCdEOTLD());
			argumentoPesquisa.setHolding(false);
			argumentoPesquisa.setCdProdutoPrepago(null);
			List<RepassePrePagoComposite> repassesPrePago = getServiceManager().getRepassePreService().consultaRepassesPrePago(argumentoPesquisa);
			if (repassesPrePago != null)
			{
				for (int i=0;i<repassesPrePago.size();i++)
				{
					SccPagamentoRepasse pagamentoRepasse = getServiceManager().getPagamentoRepasseService().getPagamentoByRepasse(repassesPrePago.get(i).getSqPreFechamento(), form.getCdEOTClaro(), form.getCdEOTLD());
					PagamentoSAPDecorator decorator = new PagamentoSAPDecorator(pagamentoRepasse,repassesPrePago.get(i).getOperadoraClaro(),repassesPrePago.get(i).getOperadoraLD());
					tabela.add(decorator);
				}
			}
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, tabela, request);
		mav.addObject("filtro", new RelatorioPagamentoForm());
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
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setDsOperadora("Todas");
		nullValue.setCdEot(BasicDAO.GET_ALL_STRING);
		comboList.add(0, nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
		return comboList;
	}
	*/

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
	/*
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setDsOperadora("Todas");
		nullValue.setCdEot(BasicDAO.GET_ALL_STRING);
		comboList.add(0, nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	*/

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

}
