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
import com.claro.sccweb.decorator.rownum.entity.SccPreChamadasCreditoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class SccPreChamadasCreditoExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<SccPreChamadasCreditoViewDecorator> decoratorList = (List<SccPreChamadasCreditoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoCredito",	"Nome do Arquivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraClaro",	"Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraExterna",	"Operadora Externa", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCsp",	"Csp", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusCredito",	"Status Crédito", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtCredito",	"Data de Credito", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorCredito",	"Valor de Credito", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNuCDR",	"Numero do Cdr", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCredito",	"Codigo de Credito", style, 8));
		columnDefinitions.add(new ExcelColumnDefinition("getNuAssA",	"Numero de A", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNuAssB",	"Numero de B", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtChamada",	"Data Chamada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getInicioChamada",	"Hora Chamada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifada",	"Duração Tarifada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBrutoChamada",	"Valor Chamada", style, 15));		
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Chamada de Credito 226");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Chamada de Credito 226");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
	
	

}
