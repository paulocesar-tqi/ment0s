package com.claro.sccweb.controller.fiscal;

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
import com.claro.sccweb.decorator.rownum.entity.SccRelBatimentoEstornoDebitoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
	
public class SccRelBatimentoEstornoDebitoExcelHandler extends BasicExcelHandler {

		@SuppressWarnings("unchecked")
		@Override
		protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<SccRelBatimentoEstornoDebitoViewDecorator> decoratorList = (List<SccRelBatimentoEstornoDebitoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
			
			if (decoratorList == null){
				throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			}
			List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora Externa", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getSistema",	"Sistema", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getUf",	"UF", style, 5));
			columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"DSName", style, 35));
			columnDefinitions.add(new ExcelColumnDefinition("getDsStatus",	"Status", style, 30));
			columnDefinitions.add(new ExcelColumnDefinition("getMesAno",	"Período Referência", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getValorTotalNf",	"Valor da NFST", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getValorTotalImpugnado",	"Valor do TOTAL item impugnado", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getDtConnect",	"Data Transmissão", style, 15));
			ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
			printer.addSheet("Batimento Estorno Debito");
			List<String> linhasCabecalho = new ArrayList<String>();
			
			linhasCabecalho.add("Claro - Relatório de Batimento Estorno de Debito");
			linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
			printer.setHeaderLines(linhasCabecalho);
			printer.generateHeader();
			printer.addBlankLines(1);
			printer.generateColumnsTitle();
			printer.addData(decoratorList);
			printer.writeData();
			
		}

}
