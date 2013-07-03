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
import com.claro.sccweb.decorator.rownum.view.RelEventosArquivoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class EventosArquivoRetornoExcelHandler extends BasicExcelHandler{

	 
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<RelEventosArquivoViewDecorator> decoratorList = (List<RelEventosArquivoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (decoratorList == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraExterna", "Operadora LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataEvento", "Data Evento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataReferencia", "Data Referência", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeChamadas", "Qtq. Chamadas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDurcaoTarifada", "Duração Tarigada", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido", "Vlr. Líquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto", "Vlr. Bruto", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getEvento", "Evento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getInfo", "Informação Adcional", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Eventos");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}

	

	
	
}
