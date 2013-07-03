/**
 * 
 */
package com.claro.sccweb.controller.relatorio.pos;

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
import com.claro.sccweb.decorator.RelatorioPrestacaoServicoPosDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author vagner.souza
 *
 */
public class RelatorioServicoPosExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelatorioPrestacaoServicoPosDecorator> decoratorList = (List<RelatorioPrestacaoServicoPosDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getEmbratel",	"Embratel", style, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getIntelig",	"Intelig", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getBrasil_telecom",	"Brasil Telecom", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getTelefonica",	"Telefonica", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getTnl",	"TNL", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getGvt",	"GVT", currencyStyle, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getSercontel",	"Sercomtel", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getTim",	"Tim", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getCtbc",	"CTBC", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getTelemar",	"Telemar", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getIpCorp",	"IPCORP", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getNexus",	"Nexus", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getTelecom65",	"Telecom65", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getCambridge",	"Cambridge", style, 10));		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Prestação de Serviço");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Prestação de Serviço");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

		


	}

}
