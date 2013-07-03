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
import com.claro.sccweb.decorator.view.SccAcordoParcelamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class SccAcordoParcelamentoExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	
		List<SccAcordoParcelamentoViewDecorator> decoratorList = (List<SccAcordoParcelamentoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora Ld", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro.", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataAcordo",	"Data de Acordo", style, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdParcelas",	"Qtde. Parcelas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorAcordado",	"Vlr. total do Acordo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlParcelaOperadora",	"Valor Operadora LD", style, 15));
		
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Acordo Parcelamento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relat�rio de Acordo de Parcelamento");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		
		
		
	}
	
	

}
