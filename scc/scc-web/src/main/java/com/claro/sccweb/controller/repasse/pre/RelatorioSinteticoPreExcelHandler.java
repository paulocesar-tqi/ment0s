package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

public class RelatorioSinteticoPreExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelSinteticoFechamentoPrePagoView> tabela = (List<RelSinteticoFechamentoPrePagoView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (tabela == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		RelatoriosRepassePreForm form = (RelatoriosRepassePreForm)getFormFromCache(RelatoriosRepassePreController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");
		
		gerarPlanilha(form, workbook, request, tabela);
	}

	public void gerarPlanilha(RelatoriosRepassePreForm form, HSSFWorkbook workbook, HttpServletRequest request, List<RelSinteticoFechamentoPrePagoView> tabela) throws Exception {
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Op. Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getUf", "UF", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTrafego", "Tráfego", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnAssinante", "CN Ass.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnOrigem", "CN Orig.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdEotOrigem", "EOT Orig.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTipo", "Tipo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPeriodo", "Período", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadas", "Chamadas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosString", "Minutos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosString", "Vlr. Líquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofinsString", "Pis/Cofins", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassarString", "ICMS Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassarString", "Vlr. Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsNaoRepassadoString", "ICMS Não Repassado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBrutoString", "Vlr. Bruto", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatório Sintético");
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
		
		linhasCabecalho.add("RELATÓRIO SINTÉTICO DE CHAMADAS");
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
