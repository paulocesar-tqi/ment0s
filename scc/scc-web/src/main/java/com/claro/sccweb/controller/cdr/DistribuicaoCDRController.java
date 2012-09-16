package com.claro.sccweb.controller.cdr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.graficos.distribuicao.ItemGraficoDistribuicao;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.DistribuicaoCDRValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoSumarizadoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.DistribuicaoCDRForm;

@Controller
@RequestMapping(value="/user/cdr/distribuicao")
public class DistribuicaoCDRController extends BaseOperationController<DistribuicaoCDRForm>{

	private final DistribuicaoCDRValidator validator = new DistribuicaoCDRValidator();
	
	public ModelAndView gerar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		DistribuicaoCDRForm form = (DistribuicaoCDRForm)_form;
		info("Pesquisando arquivo sumarizado para relatório de Distribuição de CDRs.");
		List<SccArquivoSumarizado> rows = getServiceManager().getCdrService().geraSumarizadoPeriodo(form.getCdEOTClaro(), form.getCdEOTLD(), form.getDataInicial(), form.getDataFinal(), form.getTipoOperadora().equals("H"));
		
		
		String valor = "NAO";
		if (rows.size() > 0){
			valor = "SIM";
			info("Gerando sumarizado com dados agrupados por ciclo");
			List<SccArquivoSumarizado> rowsCiclo = getServiceManager().getCdrService().geraSumarizadoPeriodoCiclo(form.getCdEOTClaro(), form.getCdEOTLD(), form.getDataInicial(), form.getDataFinal(), form.getTipoOperadora().equals("H"));
			List<SccArquivoSumarizadoDecorator> decoratorList = new ArrayList<SccArquivoSumarizadoDecorator>(rows.size());
			Map<String, List<SccArquivoSumarizado>> sumarioPorCiclo = getServiceManager().getCdrService().agrupaPorCiclos(rowsCiclo);
			Long totalRejeitado = getServiceManager().getCdrService().calculaTotalRejeitados(rows);
			Long total = getServiceManager().getCdrService().calculaTotal(rows);
			
			for (int i=0;i<rows.size();i++)
			{
				SccArquivoSumarizadoDecorator decorator = new SccArquivoSumarizadoDecorator(rows.get(i), total, totalRejeitado, i);
				decoratorList.add(decorator);
			}	
			if (decoratorList.size() > 0)
			{
				SccArquivoSumarizadoDecorator backLogDecorator = getServiceManager().getCdrService().geraBacklog(rows);
				decoratorList.add(backLogDecorator);
			}
			
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);		
			
			geraGrafico(request, sumarioPorCiclo);		
			geraTabelaAcumulado(request, rowsCiclo);
			geraTabelaFaturados(request,rowsCiclo);
		}
		
		request.setAttribute("confirmacao", valor);
		
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	private void geraTabelaFaturados(HttpServletRequest request,List<SccArquivoSumarizado> rows) throws Exception
	{
		long total = 0L;
		info("Gerando tabela com dados de Faturado");
		for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
			if (sccArquivoSumarizado.getQtCdrs() != null)
				total = total + sccArquivoSumarizado.getQtCdrs();
		}
		List<SccArquivoSumarizado> tabelaAlocado = getServiceManager().getCdrService().agrupaFaturadosPorCiclo(rows);
		List<SccArquivoSumarizadoDecorator> decoratorList = new ArrayList<SccArquivoSumarizadoDecorator>(tabelaAlocado.size());
		for (int i=0;i<tabelaAlocado.size();i++)
			{
			SccArquivoSumarizadoDecorator decorator  = new SccArquivoSumarizadoDecorator(tabelaAlocado.get(i),total,0L,i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_4, decoratorList, request);
	}
	
	/**
	 * Agrupa os CDRs já sumarizados por ciclos por status que indicam que eles estão acumulados.
	 * @param request
	 * @param rows
	 * @throws Exception
	 */
	private void geraTabelaAcumulado(HttpServletRequest request,List<SccArquivoSumarizado> rows) throws Exception
	{
		info("Gerando tabela com dados de Acumulado");
		List<SccArquivoSumarizado> tabelaAlocado = getServiceManager().getCdrService().agrupaAlocadosPorCiclo(rows);
		List<SccArquivoSumarizadoDecorator> decoratorList = new ArrayList<SccArquivoSumarizadoDecorator>(tabelaAlocado.size());
		for (int i=0;i<tabelaAlocado.size();i++)
			{
			SccArquivoSumarizadoDecorator decorator  = new SccArquivoSumarizadoDecorator(tabelaAlocado.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_3, decoratorList, request);
	}
	
	private void geraGrafico(HttpServletRequest request,Map<String, List<SccArquivoSumarizado>>  sumarioPorCiclo)
	throws Exception
	{
		info("Iniciando geração do gráfico para distribuição de CDRs");
		List<ItemGraficoDistribuicao> itemsGrafico = new ArrayList<ItemGraficoDistribuicao>();
		if (sumarioPorCiclo != null)
			{
			Iterator<String> itr = sumarioPorCiclo.keySet().iterator();
			while (itr.hasNext())
				{
				if (sumarioPorCiclo.size() > 0)
					{					
					ItemGraficoDistribuicao itemGrafico = new ItemGraficoDistribuicao();
					List<SccArquivoSumarizado> sumarioCiclo = sumarioPorCiclo.get(itr.next());
					debug("Criando item gráfico para ciclo de "+sumarioCiclo.get(0).getMmCiclo()+"/"+sumarioCiclo.get(0).getAaCiclo());
					Long valorAlocado = getServiceManager().getCdrService().calculaTotalAlocado(sumarioCiclo);
					Long valorEncaminhado = getServiceManager().getCdrService().calculaTotalEncaminhado(sumarioCiclo);
					Long valorFaturado = getServiceManager().getCdrService().calculaTotalFaturado(sumarioCiclo);
					Long total = valorAlocado+valorEncaminhado+valorFaturado;
					itemGrafico.setAaCiclo(sumarioCiclo.get(0).getAaCiclo());
					itemGrafico.setMmCiclo(sumarioCiclo.get(0).getMmCiclo());
					itemGrafico.setDescricao(sumarioCiclo.get(0).getMmCiclo()+"/"+sumarioCiclo.get(0).getAaCiclo());
					double b = total;
					
					double a = valorAlocado;					
					itemGrafico.setValorAlocado(a/b);
					
					a = valorFaturado;					
					itemGrafico.setValorFaturado(a/b);
					
					a = valorEncaminhado;					
					itemGrafico.setValorEncaminhado(a/b);
					itemsGrafico.add(itemGrafico);
					}				
				}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_2, itemsGrafico, request);		
			info("Gráfico foi gerado com sucesso e armazenado em DISPLAY_TAG_SPACE_2");
	 		}
	}
	
	@RequestMapping(value="/grafico" , method=RequestMethod.GET) 
	public ModelAndView grafico(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return new ModelAndView("distribuicao_cdr_imagem");
	}
	
	protected String getViewName() {		
		return "distribuicao_cdr_filtro";
	}
	
	protected DistribuicaoCDRForm getForm() {		
		return new DistribuicaoCDRForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
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
	
}
