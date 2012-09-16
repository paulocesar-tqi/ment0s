package com.claro.sccweb.controller.arquivo;

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
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCreditoDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class ArquivosCreditoExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<SccArquivoCreditoDecorator> view = (List<SccArquivoCreditoDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (view == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getRow.getNoArquivo", "Nome do Arquivo", style, 100));
		columnDefinitions.add(new ExcelColumnDefinition("getRow.getSqArquivoCredito", "Arquivo Crédito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOrigem", "Origem", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus", "Status", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataProcessamento", "Data Processamento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getRow.getQtRegistros", "Registros", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Arquivos");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(view);
		printer.writeData();
	}

	
}
