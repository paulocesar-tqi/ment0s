/**
 * 
 */
package com.claro.sccweb.controller.cdr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.DistribuicaoCDRValidator;
import com.claro.sccweb.form.DistribuicaoCDRForm;
import com.claro.sccweb.service.CDRService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Controller
@RequestMapping(value="/user/cdr/distribuicao")
public class SccDistribuicaoCDRController extends BaseOperationController<DistribuicaoCDRForm>{
	
	private static final String FWD_VIEW_DISTRIBUICAO_CDR = "distribuicao_cdr_filtro";
	private static final String FWD_GRAFICO_DISTRIBUICAO_CDR= "distribuicao_cdr_imagem";

	@Autowired
	private CDRService cdrService;
	
	private final DistribuicaoCDRValidator validator = new DistribuicaoCDRValidator();
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, DistribuicaoCDRForm form) throws Exception {
		
		
		ModelAndView mav = null;
		info("Pesquisando arquivo sumarizado para relatório de Distribuição de CDRs.");
		List<SccArquivoSumarizado> lstSumarizado = (List<SccArquivoSumarizado>) this.cdrService.findSumarizadoPeriodo(form.getCdEOTClaro(), form.getCdEOTLD(), form.getDataInicial(), form.getDataFinal(), form.getProduto(), form.getTipoOperadora().equals("H"));
		form.setLstSumarizado(this.cdrService.findSumarizadoByPeriodoAgrupado(form.getCdEOTClaro(), form.getCdEOTLD(), form.getDataInicial(), form.getDataFinal(), form.getProduto(), form.getTipoOperadora().equals("H")));
		String valor = (form.getLstSumarizado()!=null && form.getLstSumarizado().size() > 0 ? "SIM": "Não");
		SccArquivoSumarizado entity  =   this.cdrService.gerarBackLog(lstSumarizado);
		form.getLstSumarizado().add(entity);
		form.setLstSumarizado(gerarListaAgrupadaComMetrica(form, lstSumarizado));
		
		gerarGrafico(request,lstSumarizado, form);
		form.setLstAlocados(gerarTabelaAcumulado(lstSumarizado));
		
		form.setLstFaturados(gerarTabelaFaturados(form));
		
		request.setAttribute("confirmacao", valor);
		
		mav = new ModelAndView(FWD_VIEW_DISTRIBUICAO_CDR, "filtro", form);
		return mav;
	}
	
	private List<SccArquivoSumarizado> gerarListaAgrupadaComMetrica(DistribuicaoCDRForm form, List<SccArquivoSumarizado> lstSumarizado) throws ServiceException{
		
		Long totalRejeitado = calcularRejeitados(lstSumarizado);
		return this.cdrService.gerarMetrica(form.getLstSumarizado(), totalRejeitado);
		
	}
	
	private Long calcularRejeitados(List<SccArquivoSumarizado> lstSumarizado) throws ServiceException{
		
		return this.cdrService.calculaTotalRejeitados(lstSumarizado);
	}
	
	private List<SccArquivoSumarizado> gerarTabelaAcumulado(List<SccArquivoSumarizado> lstSumarizado) throws ServiceException{
		
		return this.cdrService.gerarAlocados(lstSumarizado);
				
				
	}
	
	private List<SccArquivoSumarizado> gerarTabelaFaturados(DistribuicaoCDRForm form) throws ServiceException{
		return this.cdrService.gerarFaturado(form.getLstSumarizado());
	}
	
	
	private Long calcularTotais(List<SccArquivoSumarizado> lstSumarizado) throws ServiceException{
		return this.cdrService.calcularTotais(lstSumarizado);
	}
	
	private void gerarGrafico(HttpServletRequest request,List<SccArquivoSumarizado> lstSumarizado, DistribuicaoCDRForm form) throws ServiceException{
		
		info("Iniciando geração do gráfico para distribuição de CDRs");
		
		Long total = calcularTotais(lstSumarizado);
		form.setLstItemGrafico(this.cdrService.gerarItensGrafico(lstSumarizado, total));
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, form.getLstItemGrafico(), request);	
		
	}
	
	@RequestMapping(value="/grafico" , method=RequestMethod.GET) 
	public ModelAndView grafico(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return new ModelAndView(FWD_GRAFICO_DISTRIBUICAO_CDR);
	}

	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("O", "Operadora"));
		comboList.add(new BasicStringItem("H", "Holding"));
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
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadoras() throws Exception
	{
		return super.populaOperadorasClaro(false);
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
		return super.populaOperadorasExternas(false);
	}
	*/
	
	/**
	 * Carrega uma lista vazia de produtos de cobilling. 
	 * Essa lista só será populada após seleção da Operadora LD.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception {
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(BasicDAO.GET_ALL.toString());
		SccProdutoCobilling nullValue = new SccProdutoCobilling();
		nullValue.setNoProdutoCobilling("Todos");
		nullValue.setCdProdutoCobilling(BasicDAO.GET_ALL);
		produtos.add(0,nullValue);
		return produtos;
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
	public void pesquisaProdutosLD(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") DistribuicaoCDRForm form) throws Exception {		
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOT);		
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put("0","Todos");		
		for (int i=0;i<produtos.size();i++) {			
			jsonResponse.put(produtos.get(i).getCdProdutoCobilling().toString(), produtos.get(i).getNoProdutoCobilling());			
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}

	
	@Override
	protected String getViewName() {
		
		return "distribuicao_cdr_filtro";
	}

	@Override
	protected DistribuicaoCDRForm getForm() {
		
		return new DistribuicaoCDRForm();
	}

	@Override
	protected Validator getValidator() {

		return this.validator;
	}
	
	public void setCdrService(CDRService cdrService) {
		this.cdrService = cdrService;
	}
	
	
	

}
