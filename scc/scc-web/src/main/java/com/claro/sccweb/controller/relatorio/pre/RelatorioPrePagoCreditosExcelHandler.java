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
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"Nome do Arquivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"EOT LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"EOT Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsCredito",	"Tipo de Cr�dito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsStatus",	"Status do Cr�dito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroA",	"Telefone", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDtCredito",	"Data do Cr�dito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getVlCredito",	"Valor do Cr�dito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidade",	"Quantidade de Chamadas Agrupadas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosQueimados",	"Minutos Queimados", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosAjustados",	"Minutos Ajustados", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Cr�ditos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Cobilling Pr� Pago - Relat�rio de Cr�ditos");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
}
