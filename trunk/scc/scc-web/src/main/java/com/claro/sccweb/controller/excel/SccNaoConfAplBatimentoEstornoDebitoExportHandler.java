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
import com.claro.sccweb.decorator.view.BatimentoEstornoDebitoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class SccNaoConfAplBatimentoEstornoDebitoExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	
		List<BatimentoEstornoDebitoViewDecorator> decoratorList = (List<BatimentoEstornoDebitoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Externa.", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getUf",	"UF", style, 2));
		columnDefinitions.add(new ExcelColumnDefinition("getCiclo",	"Ciclo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNotaFiscal",	"Nota Fiscal", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getEstornoDebitoToAjustado",	"Estorno de Debito", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadasToAjustado",	"Chamadas Ajustadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDiferencaTOajustado",	"Diferen�a Ajustada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getEstornoDebitoCrediCMS",	"Estorno de Debito CMS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadasCrediCMS",	"Chamadas CrediCMS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDiferencaCrediCMS",	"Diferen�a CrediCMS", style, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Batimento Estorno de Debito");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relat�rio de N�o Conformidade de Aplica��o de Batimento Estorno Debito");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		
		
		
	}
	
	

}
