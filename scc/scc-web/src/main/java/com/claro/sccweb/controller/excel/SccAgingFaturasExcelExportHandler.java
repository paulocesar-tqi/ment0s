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
import com.claro.sccweb.form.SccAgingFaturasForm;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccAgingFaturasExcelExportHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

/*		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String datam2 = dateFormat.format(new Date()); 
		
		response.setHeader("Content-Disposition","attachment; filename=\"agingfaturas"+ datam2 + ".xls\"");

*/		
		SccAgingFaturasForm form = (SccAgingFaturasForm) model.get("filtro");
		
		if(form != null && form.getListAgingFaturas() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD", "Operadora LD ", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVencer", "A vencer", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor1a10Dias", "1 a 10 dias", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor11a20Dias", "11 a 20 dias", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor21a30Dias", "21 a 30 dias", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor31a60Dias", "31 a 60 dias", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor61a90Dias", "61 a 90 dias", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMaior90Dias", " > 90 dias", currencyStyle, 15));

		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatório de Aging Faturas");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(form.getListAgingFaturas());
		printer.writeData();
		
		
	}

	
}
