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
import com.claro.sccweb.decorator.view.SccFaturasViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class SccJurosMultasExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<SccFaturasViewDecorator> decoratorList = (List<SccFaturasViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCsp",	"CSP", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getUf",	"UF", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroFatura",	"Numero Fatura", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataEmissao",	"Data Emissão", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataVencimento",	"Data de Vencimento", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getJuros",	"Juros", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMulta",	"Multa", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor",	"Total", style, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Juros e Multas");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Juros e Multas");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
	
	

}
