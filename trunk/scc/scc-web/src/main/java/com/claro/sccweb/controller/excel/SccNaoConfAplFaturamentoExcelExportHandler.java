/**
 * 
 */
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
import com.claro.sccweb.decorator.view.FaturamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class SccNaoConfAplFaturamentoExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<FaturamentoViewDecorator> decoratorList = (List<FaturamentoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLd",	"Operadora Ld", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getUf",	"UF", style, 2));
		columnDefinitions.add(new ExcelColumnDefinition("getCiclo",	"Ciclo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroNota",	"Numero Nf", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSerieNotaFiscal",	"Serie Nf", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSubSerieNotaFiscal",	"Sub Serie Nf", style, 15));
				
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Faturamento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Faturamento Fiscal");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
	
	

}
