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
import com.claro.sccweb.decorator.rownum.entity.SccAssexuadosViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author vagner.souza
 *
 */
public class RelatorioAssexuadosExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<SccAssexuadosViewDecorator> decoratorList = (List<SccAssexuadosViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroDeA",	"N�mero de A", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtChamadas",	"Quantidade de Chamadas", style, 25));
		columnDefinitions.add(new ExcelColumnDefinition("getQtMinutoTarifados",	"Quantidade de Minutos Tarifados", style, 25));
		columnDefinitions.add(new ExcelColumnDefinition("getVlLiquido",	"Valor L�quido Total das Chamadas", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdErroReciclagem",	"C�digo de Erro de Reciclagem", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtRelatorio",	"Data de Emiss�o do Relat�rio", dateStyle, 12));
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Assexuados");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relat�rio Assexuado");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

		


	}

}
