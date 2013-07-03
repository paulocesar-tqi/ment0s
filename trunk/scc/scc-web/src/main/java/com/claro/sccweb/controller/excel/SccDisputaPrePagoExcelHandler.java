/**
 * 
 */
package com.claro.sccweb.controller.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.SccDisputaPrePagoForm;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaPrePagoExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String datam2 = dateFormat.format(new Date()); 
		
		//response.setHeader("Content-Disposition","attachment; filename=\"disputa"+ datam2 + ".xls\"");
		SccDisputaPrePagoForm form = (SccDisputaPrePagoForm) model.get("filtro");
		
		if (form.getListDisputaPrePago() == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getSqDisputaPrePago",	"Nr. Disputa Pr�-Pago", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getSccOperadora.getCdEot",	"Operadora Ld", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSccOperadora.getDsOperadora", "Descri��o Operadora", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDtRepasseDisputa",	"Data Dem Repasse Disputa", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtLiberacaoRepasse",	"Dt. Libera��o de Repasse", dateStyle, 15));
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Disputa Pre Pago");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relat�rio Disputa Pre Pago");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData((List) form.getListDisputaPrePago());
		printer.writeData();
		

		


	}

}
