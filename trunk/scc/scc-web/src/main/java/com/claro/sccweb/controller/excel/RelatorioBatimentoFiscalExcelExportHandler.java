package com.claro.sccweb.controller.excel;

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
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoFiscalViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioBatimentoFiscalExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<SccBatimentoFiscalViewDecorator> decoratorList = (List<SccBatimentoFiscalViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNomeOperadora",	"Nome Operadora", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCSP",	"Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeEmpresa",	"Nome Empresa", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCdEOTClaro",	"Operadora", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getSgUF",	"UF", style, 5));
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"Dsname", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getCdStatusArquivo",	"Status", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCiclo",	"Ciclo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlTotalNF",	"Valor Fiscal", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtConnect",	"Data da Transmissão", style, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Batimento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Batimento Fiscal");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
	

}
