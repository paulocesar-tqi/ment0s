package com.claro.sccweb.controller.processados.pos;

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
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class ListaCDRsExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<SccCdrCobillingDecorator> decoratorList = (List<SccCdrCobillingDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_4, request);
		if (decoratorList == null)
			throw new ControllerExecutionException("Navegação inválida. Pesquisa de arquivos não encontrada no cache.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCdMotivo", "Código Motivo", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus", "Status", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataStatus", "Data Status", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCSP", "CSP", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroA", "A#", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroB", "B#", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getEOTOrigem", "EOT Origem", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getEOTExterna", "EOT Externa", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataChamada", "Data Chamada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getHoraChamada", "Hora Chamada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracao", "Duração", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido", "Valor Líquido", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoRetorno", "Arquivo Retorno", style, 45));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("CDRs");
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
