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

public class ArquivoRetornoExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<RelEventosArquivoViewDecorator> decoratorList = (List<RelEventosArquivoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (decoratorList == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDataProcClaro", "Dt Proc Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataReferencia", "Dt Referencia", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivo", "Arquivo", style, 150));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCiclo", "Ciclo", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getMmCiclo", "Mês", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getAaCiclo", "Ano", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtCDR", "Qtde", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosTarifados", "Minutos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido", "Vlr Liq", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto", "Vlr Brt", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus", "Status", style, 80));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Arquivos Retorno");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Detalhamento de Arquivos de Retorno");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
			
		
	}

	
	
}
