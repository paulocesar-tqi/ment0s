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

import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.SccDisputaForm;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		SccDisputaForm form = (SccDisputaForm) model.get("filtro");
		
		if (form.getListDisputa() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getSqDisputa",	"Nr(D)", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getSccOperadora.getCdEot",	"Eot Ld", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdStatusDisputa",	"Status", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getInRiscoDisputa",	"Risco", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtCriacao",	"Criado", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtAtualizacao",	"Alterado", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdUsuarioManut",	"Usuario", style, 15));
		
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Disputa Pos Pago");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Disputa Pos Pago");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData((List) form.getListDisputa());
		printer.writeData();
		

		


	}

}
