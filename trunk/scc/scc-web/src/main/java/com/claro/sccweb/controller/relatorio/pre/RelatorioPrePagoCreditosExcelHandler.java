package com.claro.sccweb.controller.relatorio.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccPrePagoCreditosViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioPrePagoCreditosExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<SccPrePagoCreditosViewDecorator> decoratorList = (List<SccPrePagoCreditosViewDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"Nome do Arquivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"EOT LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"EOT Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsCredito",	"Tipo de Crédito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsStatus",	"Status do Crédito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroA",	"Telefone", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDtCredito",	"Data do Crédito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getVlCredito",	"Valor do Crédito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidade",	"Quantidade de Chamadas Agrupadas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosQueimados",	"Minutos Queimados", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosAjustados",	"Minutos Ajustados", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Créditos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Cobilling Pré Pago - Relatório de Créditos");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
}
