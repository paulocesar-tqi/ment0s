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
import com.claro.sccweb.decorator.rownum.entity.SccEncaminhadoMobileViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author vagner.souza
 *
 */
public class RelatorioEncaminhadoMobileExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<SccEncaminhadoMobileViewDecorator> decoratorList = (List<SccEncaminhadoMobileViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivoReferencia",	"Nome do Arquivo", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getQtChamadas",	"Quantidade de Chamadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtMinutoTarifados","Quantidade de Minutos Tarifados", style, 25));
		columnDefinitions.add(new ExcelColumnDefinition("getVlLiquido",	"Valor L�quido Total das Chamadas", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtRelatorio",	"Data de Emiss�o do Relat�rio", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtProcClaro",	"Data de Refer�ncia", dateStyle, 15));
		
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Encaminhado ao Mobile");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relat�rio Encaminhado ao Mobile");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

		


	}

}
