package com.claro.sccweb.controller.relatorio.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.view.RelAlarmeOperacionalView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioAlarmeOperacionalExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<RelAlarmeOperacionalView> rows = (List<RelAlarmeOperacionalView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (rows == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNumA", "Número de A", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdeChamadas", "Qtde de Chamadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdeMinutosTarifados", "Qtde de Minutos Tarifados", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorTotalChamadas", "Valor Líquido Total das Chamadas", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNroFatura",	"Número da Fatura", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNroNf",	"Número Nota Fiscal", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeOperadoraLD", "Operadora LD", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataReferencia", "Data de Referência", style, 40));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Alarme Operacional");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Alarme Operacional");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		
		printer.generateColumnsTitle();
		printer.addData(rows);
		printer.writeData();
		

	}

}
