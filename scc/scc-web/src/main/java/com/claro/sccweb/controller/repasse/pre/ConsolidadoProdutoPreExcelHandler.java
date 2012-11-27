package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.view.ConsolidadoProdutoPreViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.ConsolidadoProdutoPreForm;

public class ConsolidadoProdutoPreExcelHandler extends BasicExcelHandler {

	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<ConsolidadoProdutoPreViewDecorator> tabela = (List<ConsolidadoProdutoPreViewDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (tabela == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		ConsolidadoProdutoPreForm form = (ConsolidadoProdutoPreForm) getFormFromCache(ConsolidadoProdutoPreController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");

		gerarPlanilha(form, workbook, request, tabela);
	}

	public void gerarPlanilha(ConsolidadoProdutoPreForm form, HSSFWorkbook workbook, HttpServletRequest request, List<ConsolidadoProdutoPreViewDecorator> tabela) throws Exception {

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCspLD", "CSP LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Op. Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCdProdutoPrepago", "Cd. Produto Pré-Pago", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCdTipoEvento", "Tipo Evento", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataChamada", "Data Chamada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getInChamadaRepassada", "Chamada Repassada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataRepasse", "Data Repasse", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalDuracaoFaturada", "Total Duração Faturada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalValor", "Valor Total", currencyStyle, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifada", "Duração Tarifada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto", "Valor Bruto", currencyStyle, 20));

		for(int i = 1; i <= 10; ++i) {
			String pId = (i < 10) ? "0" + i : i+"";
			
			columnDefinitions.add(new ExcelColumnDefinition("getPacote" + pId, "Pacote " + i, style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getMinutosPacote" + pId, "Min. Pacote " + i, style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getValorPacote" + pId, "Vlr. Pacote " + i, currencyStyle, 20));
		}

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions, workbook);
		printer.addSheet("Consolidado Produtos");
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
		if (form.getCdProdutoPrepago() == null || form.getCdProdutoPrepago().equals(BasicDAO.GET_ALL_STRING))
			produto = "TODOS";
		else if (!form.getCdProdutoPrepago().equals("0")) {
			produto = getServiceManager(request).getProdutoPrepagoService().findByPk(form.getCdProdutoPrepago().toString()).getNoProdutoPrepago();
		}

		linhasCabecalho.add("RELATÓRIO CONSOLIDADO DE PRODUTOS");
		linhasCabecalho.add("PRESTADORA LD: " + prestadoraLd);
		linhasCabecalho.add("FILIAL CLARO: " + filial);
		linhasCabecalho.add("DATA INÍCIO : " + (form.getDataInicial() != null ? dateFormat.format(form.getDataInicial()) : ""));
		linhasCabecalho.add("DATA FIM : " + (form.getDataFinal() != null ? dateFormat.format(form.getDataFinal()) : ""));
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: " + dateFormat.format(new Date()));
		linhasCabecalho.add("PRODUTO: " + produto);

		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
	}

}
