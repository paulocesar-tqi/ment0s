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
public class SccAcompanhamentoParcelamentoExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	
		List<SccAcordoParcelamentoViewDecorator> decoratorList = (List<SccAcordoParcelamentoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro.", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdParcelas",	"Qtd. Parcelas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorAcordado",	"Valor Total do Acordo", style, 18));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Acompanhamento Parcelamento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Acompanhamento Parcelamento");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		
		
		
	}
	
	

}
