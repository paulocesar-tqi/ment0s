package com.claro.sccweb.controller.repasse.pre;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.ConsolidadoProdutoPreView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelatorioApuracaoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelatorioApuracaoPreSumarizado;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.DemonstrativoRepassePrePagoValidator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.ParOperadorasRepassePreDecorator;
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator;
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator.Tipo;
import com.claro.sccweb.form.DemonstrativoRepassePrePagoForm;
import com.claro.sccweb.form.QuestionamentoArquivoForm;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

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
	public static final String OPERACAO_EXCEL3 = "OPERACAO_EXCEL3";
	public static final String OPERACAO_NOVO = "OPERACAO_NOVO";
	public static final String DEMONSTRATIVO_HOLDING = "DEMONSTRATIVO_HOLDING";
	public static final String DEMONSTRATIVO_OPERADORA = "DEMONSTRATIVO_OPERADORA";
	public static final String DEMONSTRATIVO_ASSINATURAS= "DEMONSTRATIVO_ASSINATURAS"; 
	
	public static final String DEMONSTRATIVO_COMPLETO = "DEMONSTRATIVO_COMPLETO";
	public static final String DEMONSTRATIVO_APURACAO = "DEMONSTRATIVO_APURACAO";
	public static final String DEMONSTRATIVO_CONS_PRODUTO = "DEMONSTRATIVO_CONS_PRODUTO";
	public static final String DEMONSTRATIVO_SINTETICO = "DEMONSTRATIVO_SINTETICO";
	public static final String DEMONSTRATIVO_SINTETICO_SERVICO_PRESTADO = "DEMONSTRATIVO_SINTETICO_SERVICO_PRESTADOs";
	public static final String DEMONSTRATIVO_ASSINATURA= "DEMONSTRATIVO_ASSINATURA"; 
	
	public static final String DEMONSTRATIVO_CONSOLIDADO_GERAL ="DEMONSTRATIVO_CONSOLIDADO_GERAL";
	
	public static final String FORM_NAME = "filtro";
	
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
		else if (form.getOperacao().equals(OPERACAO_EXCEL3))
			mav = geraExcelAssinatura(request, response, form, bindingResult, model);
		return mav;
	}
	
	
	
	private ModelAndView geraExcel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		boolean gerarApuracao = true;
		boolean gerarSintetico = true;
		boolean gerarConsolidado = false;
		boolean gerarAssinatura = false;
		boolean gerarConsolidadoTotal = true;
		
		cleanSession(getClass(), request, DEMONSTRATIVO_APURACAO, DEMONSTRATIVO_SINTETICO, DEMONSTRATIVO_ASSINATURA, DEMONSTRATIVO_CONS_PRODUTO);
		
		info("Exportando demonstrativo de repasse pré-pago para Excel com filtro : "+form.toString());
		
		Map<SccOperadora, DemonstrativoRepassePreDecorator> excelModel = new HashMap<SccOperadora, DemonstrativoRepassePreDecorator>();
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm)getMyFormFromCache(getClass());
		SccOperadora operadoraLD = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(cachedForm.getCdEOTLD());
		SccProdutoPrepago produtoPrepago = getServiceManager().getContratoPrePagoService().getProdutoById(cachedForm.getCdProdutoPrepago());		
		String cdEOTOperadoraOriginal = cachedForm.getCdEOTClaro();
		
		ModelAndView mav = new ModelAndView("repasse_demonstrativo_pre_excel");
		
		if (produtoPrepago.getCdProdutoPrepago().equals(CobillingConstants.CD_PRODUTO_PADRAO_PREPAGO)) {
			
		} else if (produtoPrepago.getCdProdutoPrepago().equals(CobillingConstants.CD_PRODUTO_TZLD_PREPAGO)) {
			gerarAssinatura = true;
			gerarConsolidado = true;
		}
		
		List<SccOperadora> listaHolding = getServiceManager().getPesquisaDominiosService().pesquisaHoldingClaro();		
		List<SccPreFechamentoAssinaturaDecorator> lstAssinatura = new ArrayList<SccPreFechamentoAssinaturaDecorator>();
		SccPreFechamentoAssinatura totalizador = new SccPreFechamentoAssinatura();
		for (SccOperadora holding : listaHolding) {
			List<SccOperadora> listaOperadorasHolding = getServiceManager().getOperadoraService().pesquisaOperarodasHolding(holding.getCdEot());
			cachedForm.setCdEOTClaro(holding.getCdEot());
			DemonstrativoRepassePreDecorator demonstrativoHolding = getServiceManager().getRepassePreService().carregaDemonstrativoHolding(cachedForm);
			demonstrativoHolding.setOperadora(holding);
			demonstrativoHolding.setProdutoPrepago(produtoPrepago);
			demonstrativoHolding.setOperadoraLD(operadoraLD);
			demonstrativoHolding.setPeriodo(cachedForm.getMesDemonstrativo()+"/"+cachedForm.getAnoDemonstrativo());
			for (SccOperadora sccOperadora : listaOperadorasHolding) {
				cachedForm.setCdEOTClaro(sccOperadora.getCdEot());
				DemonstrativoRepassePreDecorator demonstrativoOperadora = getServiceManager().getRepassePreService().carregaDemonstrativoOperadoras(cachedForm);
				demonstrativoOperadora.setOperadora(sccOperadora);
				demonstrativoOperadora.setProdutoPrepago(produtoPrepago);
				demonstrativoOperadora.setOperadoraLD(operadoraLD);
				demonstrativoOperadora.setPeriodo(cachedForm.getMesDemonstrativo()+"/"+cachedForm.getAnoDemonstrativo());
				demonstrativoHolding.getDemonstrativosOperadorasHolding().add(demonstrativoOperadora);
			}
			if (gerarAssinatura) {
				cachedForm.setCdEOTClaro(holding.getCdEot());
				List<SccPreFechamentoAssinaturaDecorator> lst = getServiceManager().getRepassePreService().carregaAssinaturasHolding(cachedForm);
				lstAssinatura.addAll(lst);
				if (lst.size() > 0)
					totalizador.append(lst.get(0).getEntity());
			}
			
			excelModel.put(holding, demonstrativoHolding);			
		}
		
		if (gerarApuracao) {
			List<RelatorioApuracaoFechamentoPrePagoView> lst = getServiceManager().getRepassePreService().gerarRelatorioApuracao(cachedForm.getCdProdutoPrepago(), cachedForm.getCdEOTLD(),
					BasicDAO.GET_ALL_STRING, BasicDAO.GET_ALL_STRING, cachedForm.getDtInicial(), cachedForm.getDtFinal());
			
			List<RelatorioApuracaoFechamentoPrePagoView> lstTotais = gerarTotais(lst);
			
			List<RelatorioApuracaoPreSumarizado> listFinal = formatarCampos(lstTotais);
			storeInSession(getClass(), DEMONSTRATIVO_APURACAO, listFinal, request);
		}
		
		if (gerarSintetico) {
			List<RelSinteticoFechamentoPrePagoView> lst = getServiceManager().getRepassePreService().geraRelatorioSintetico(cachedForm.getCdProdutoPrepago(), cachedForm.getCdEOTLD(),
					BasicDAO.GET_ALL_STRING, BasicDAO.GET_ALL_STRING, cachedForm.getDtInicial(), cachedForm.getDtFinal());
			List<RelSinteticoServicoPrePagoView> lstServicoPrestado = getServiceManager().getRepassePreService().geraRelatorioSinteticoService(cachedForm.getCdProdutoPrepago(),cachedForm.getCdEOTLD(), BasicDAO.GET_ALL_STRING, BasicDAO.GET_ALL_STRING, cachedForm.getDtInicial(), cachedForm.getDtFinal());

			storeInSession(getClass(), DEMONSTRATIVO_SINTETICO, lst, request);
			storeInSession(getClass(), DEMONSTRATIVO_SINTETICO_SERVICO_PRESTADO, lstServicoPrestado, request);
		}
		
		if (gerarAssinatura) {			

			if (lstAssinatura.size() > 1) {
				SccOperadora operadoraClaro = new SccOperadora();
				operadoraClaro.setDsOperadora("Consolidado");
				
				SccPreFechamentoAssinaturaDecorator decorator = new SccPreFechamentoAssinaturaDecorator(totalizador, operadoraClaro, null, Tipo.TOTAL);
				lstAssinatura.add(decorator);
				
				decorator = new SccPreFechamentoAssinaturaDecorator(totalizador, operadoraClaro, null, Tipo.MES_ANTERIOR);
				lstAssinatura.add(decorator);
				
				decorator = new SccPreFechamentoAssinaturaDecorator(totalizador, operadoraClaro, null, Tipo.OUTROS_MESES);
				lstAssinatura.add(decorator);
			}
			storeInSession(getClass(), DEMONSTRATIVO_ASSINATURA, lstAssinatura, request);
		}
		
		if (gerarConsolidado) {
			List<ConsolidadoProdutoPreView> lst = getServiceManager().getRepassePreService().gerarRelatorioConsolidadoProdutoPre(cachedForm.getCdEOTLD(), 
					BasicDAO.GET_ALL_STRING, cachedForm.getCdProdutoPrepago(), cachedForm.getDtInicial(), cachedForm.getDtFinal());
			
			storeInSession(getClass(), DEMONSTRATIVO_CONS_PRODUTO, lst, request);
		}
		
		if (gerarConsolidadoTotal){
			
			DemonstrativoRepassePreDecorator decorator = getServiceManager().getRepassePreService().carregarDemonstrativoConsolidado(cachedForm);
			
			//List<SccPreFechamento> listConsolidadoTotal = (List<SccPreFechamento>) getServiceManager().getRepassePreService().carregarDemonstrativoConsolidado(cachedForm);
			storeInSession(getClass(), DEMONSTRATIVO_CONSOLIDADO_GERAL, decorator, request);
			
		}
		
		storeInSession(getClass(), DEMONSTRATIVO_COMPLETO, excelModel, request);
		storeInSession(getClass(), BaseOperationController.FORM_NAME, cachedForm, request);
		cachedForm.setCdEOTClaro(cdEOTOperadoraOriginal);
		return mav;
	}
	
	private List<RelatorioApuracaoFechamentoPrePagoView> gerarTotais(
			List<RelatorioApuracaoFechamentoPrePagoView> lst) {
		
		DecimalFormat df = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		
		RelatorioApuracaoFechamentoPrePagoView total = new RelatorioApuracaoFechamentoPrePagoView();
		total.setOperadoraClaro("Total");
		total.setDsOperadora("Total");
		
		Double totalValorApuradoLiquido = 0D;
		Double totalPisCofins = 0D;
		Double totalValorIcmsRepassar = 0D;
		Double totalValorIcmsNaoRepassado = 0D;
		Double totalValorRepassar = 0D;
		Double totalServicoPrestadoLiquido = 0D;
		Double totalPisCofinsServicePrestado = 0D;
		Double totalIss = 0D;
		Double totalValorBrutoServico = 0D;
		Double totalCreditosAutorizados = 0D;
		Double totalCreditos226 = 0D;
		Double totalPenalidadesMinutosPerdidos = 0D;
		Double totalTotalMultasJuros = 0D;
		Double totalTotalAcertosConciliacoes = 0D;
		Double totalCpmfDescontar = 0D;
		Double totalIcmsDescontar = 0D;
		Double totalIcmsRepassar = 0D;
		Double totalValorFinalRepassar = 0D;
		Double totalValorNotaFiscal = 0D;
		Double totalDestaqueIcms = 0D;
		
		try {
			for(RelatorioApuracaoFechamentoPrePagoView item : lst) {
				totalValorApuradoLiquido += item.getValorApuradoLiquido() != null ? df.parse(item.getValorApuradoLiquidoStr()).doubleValue() : 0; 
				totalPisCofins += item.getPisCofins() != null ? df.parse(item.getPisCofinsStr()).doubleValue() : 0;
				totalValorIcmsRepassar += item.getValorIcmsRepassar() != null ? df.parse(item.getValorIcmsRepassarStr()).doubleValue() : 0;
				totalValorIcmsNaoRepassado += item.getValorIcmsNaoRepassado() != null ? df.parse(item.getValorIcmsNaoRepassadoStr()).doubleValue() : 0;
				totalValorRepassar += item.getValorRepassar() != null ? df.parse(item.getValorRepassarStr()).doubleValue() : 0;
				totalServicoPrestadoLiquido += item.getServicoPrestadoLiquido() != null ? df.parse(item.getServicoPrestadoLiquidoStr()).doubleValue() : 0;
				totalPisCofinsServicePrestado += item.getPisCofinsServicePrestado() != null ? df.parse(item.getpisCofinsServicePrestadoStr()).doubleValue() : 0;
				totalIss += item.getIss() != null ? df.parse(item.getIssStr()).doubleValue() : 0;
				totalValorBrutoServico += item.getValorBrutoServico() != null ? df.parse(item.getValorBrutoServicoStr()).doubleValue() : 0;
				totalCreditosAutorizados += item.getCreditosAutorizados() != null ? df.parse(item.getCreditosAutorizadosStr()).doubleValue() : 0;
				totalCreditos226 += item.getCreditos226() != null ? df.parse(item.getCreditos226Str()).doubleValue() : 0;
				totalPenalidadesMinutosPerdidos += item.getPenalidadesMinutosPerdidos() != null ? df.parse(item.getPenalidadeMinutosPerdidosStr()).doubleValue() : 0;
				totalTotalMultasJuros += item.getTotalMultasJuros() != null ? df.parse(item.getTotalMultasJurosStr()).doubleValue() : 0;
				totalTotalAcertosConciliacoes += item.getTotalAcertosConciliacoes() != null ? df.parse(item.getTotalAcertosConciliacoesStr()).doubleValue() : 0;
				totalCpmfDescontar += item.getCpmfDescontar() != null ? df.parse(item.getCpmDescontarStr()).doubleValue() : 0;
				totalIcmsDescontar += item.getIcmsDescontar() != null ? df.parse(item.getIcmsDescontarStr()).doubleValue() : 0;
				totalIcmsRepassar += item.getIcmsRepassar() != null ? df.parse(item.getIcmsRepassarStr()).doubleValue() : 0;
				totalValorFinalRepassar += item.getValorFinalRepassar() != null ? df.parse(item.getValorFinalRepassarStr()).doubleValue() : 0;
				totalValorNotaFiscal += item.getValorNotaFiscal() != null ? df.parse(item.getValorNotaFiscalStr()).doubleValue() : 0;
				totalDestaqueIcms += item.getDestaqueIcms() != null ? df.parse(item.getDestaqueIcmsStr()).doubleValue() : 0;
			}
		} catch (Exception e) {	}		
		
		total.setValorApuradoLiquido(totalValorApuradoLiquido);
		total.setPisCofins(totalPisCofins);
		total.setValorIcmsRepassar(totalValorIcmsRepassar);
		total.setValorIcmsNaoRepassado(totalValorIcmsNaoRepassado);
		total.setValorRepassar(totalValorRepassar);
		total.setServicoPrestadoLiquido(totalServicoPrestadoLiquido);
		total.setPisCofinsServicePrestado(totalPisCofinsServicePrestado);
		total.setIss(totalIss);
		total.setValorBrutoServico(totalValorBrutoServico);
		total.setCreditosAutorizados(totalCreditosAutorizados);
		total.setCreditos226(totalCreditos226);
		total.setPenalidadesMinutosPerdidos(totalPenalidadesMinutosPerdidos);
		total.setTotalMultasJuros(totalTotalMultasJuros);
		total.setTotalAcertosConciliacoes(totalTotalAcertosConciliacoes);
		total.setCpmfDescontar(totalCpmfDescontar);
		total.setIcmsDescontar(totalIcmsDescontar);
		total.setIcmsRepassar(totalIcmsRepassar);
		total.setValorFinalRepassar(totalValorFinalRepassar);
		total.setValorNotaFiscal(totalValorNotaFiscal);
		total.setDestaqueIcms(totalDestaqueIcms);
		
		lst.add(total);
		
		return lst;
	}

	private List<RelatorioApuracaoPreSumarizado> formatarCampos(List<RelatorioApuracaoFechamentoPrePagoView> listOld){
		List<RelatorioApuracaoPreSumarizado> listFormatada = new ArrayList<RelatorioApuracaoPreSumarizado>();
		for (RelatorioApuracaoFechamentoPrePagoView relatorioApuracaoFechamentoPrePagoView : listOld) {
			RelatorioApuracaoPreSumarizado relatorioApuracaoPreSumarizado = new RelatorioApuracaoPreSumarizado(relatorioApuracaoFechamentoPrePagoView);
			listFormatada.add(relatorioApuracaoPreSumarizado);
			
		}
		return listFormatada;
	}
	public ModelAndView geraExcelAssinatura(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Exportando demonstrativo de assinatura pré-pago para Excel com filtro : "+form.toString());
		return new ModelAndView("repasse_demonstrativo_assinatura_pre_excel");
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
	
	public ModelAndView gerarDemonstrativoConsolidado(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("filtro")  DemonstrativoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception{
	
		info("Gerando demonstrativo de repasse-pré-pago com filtro "+form.toString());
		ModelAndView mav = new ModelAndView(getViewName());
		cleanSession(getClass(), request, DEMONSTRATIVO_CONSOLIDADO_GERAL);

		 
		DemonstrativoRepassePrePagoForm cachedForm =  (DemonstrativoRepassePrePagoForm)getMyFormFromCache(getClass());		
		
		storeInSession(getClass(), DEMONSTRATIVO_CONSOLIDADO_GERAL, getServiceManager().getRepassePreService().carregaDemonstrativoHolding(cachedForm).getItens(),request);
		form.setOperacao(OPERACAO_SOLICITAR);
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
		//TreeSet<ParOperadorasRepassePreDecorator> listaParesOperadoras = new TreeSet<ParOperadorasRepassePreDecorator>();
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
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
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
			 response.setContentType("application/json;charset=UTF-8");
		     response.getWriter().print(jsonResponse.toString());
	  }
	  @RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		Object form = getMyFormFromCache(getClass());
		if (form != null) {
			mav.addObject(FORM_NAME, form);
		} else {
			mav.addObject(FORM_NAME, getForm());
		}
		return mav;
	}		
		
		protected QuestionamentoArquivoForm getForm() {
			
			return new QuestionamentoArquivoForm();
		}


}
