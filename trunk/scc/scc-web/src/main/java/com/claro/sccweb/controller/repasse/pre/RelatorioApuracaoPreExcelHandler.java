package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.view.RelatorioApuracaoPreSumarizado;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.RelApuracaoFechamentoPrePagoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

public class RelatorioApuracaoPreExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelatorioApuracaoPreSumarizado> tabela = (List<RelatorioApuracaoPreSumarizado>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (tabela == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		RelatoriosRepassePreForm form = (RelatoriosRepassePreForm)getFormFromCache(RelatoriosRepassePreController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");
		
		gerarPlanilha(form, workbook, request, tabela);
	}

	public void gerarPlanilha(RelatoriosRepassePreForm form, HSSFWorkbook workbook, HttpServletRequest request, List<RelatorioApuracaoPreSumarizado> tabela) throws Exception {
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "UF - OP Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorApuradoLiquido", "Vlr Apurado Liquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofins", "Pis Cofins", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorIcmsRepassar", "ICMS A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorIcmsNaoRepassado", "ICMS Não Repassado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassar", "Vlr A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getServicoPrestadoLiquido", "Serv Prest Liquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofinsServicePrestado", "Pis/Cofins Ser. Prestado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIss", "Serv Prest Iss", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBrutoServico", "Vlr Bruto Serv Prest", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCreditosAutorizados", "Créditos Autorizados", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCreditos226", "Créditos 226", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPenalidadesMinutosPerdidos", "Penalidades Minutos Perdidos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalMultasJuros", "Total Multas e Juros", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalAcertosConciliacoes", "Total Acertos Conciliações", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCpmfDescontar", "CPMF A Descontar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsDescontar", "ICMS A Descontar (Prox Mes)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassar", "ICMS A Repassar (Mes Ant)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorFinalRepassar", "Vlr Final A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorNotaFiscal", "Valor Nota Fiscal", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDestaqueIcms", "Destaque ICMS", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Resumo Apuração");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		String prestadoraLd = "Todas";
		if (!form.getCdEOTLD().equals("*")) {
			prestadoraLd = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTLD()).getDsOperadora();
		}
		
		String filial = "Todas";
		if (!form.getCdEOTClaro().equals("*")) {
			filial = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTClaro()).getDsOperadora();
		}
		
		String produto = "PRODUTO PADRÃO";
		if (!form.getCdProdutoPrepago().equals("0")) {
			produto = getServiceManager(request).getProdutoPrepagoService().findByPk(form.getCdProdutoPrepago()).getNoProdutoPrepago();
		}
		
		linhasCabecalho.add("RELATÓRIO RESUMO APURAÇÃO PRÉ-PAGO");
		linhasCabecalho.add("PRESTADORA LD: " + prestadoraLd);
		linhasCabecalho.add("FILIAL CLARO: "+filial);
		linhasCabecalho.add("MÊS DE REFERÊNCIA: "+form.getMesRelatorio()+"/"+form.getAnoRelatorio());		
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: "+dateFormat.format(new Date()));
		linhasCabecalho.add("PRODUTO: "+produto);		
		

		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
	}
	
	
}
