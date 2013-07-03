/**
 * 
 */
package com.claro.sccweb.controller.relatorio;

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
import com.claro.sccweb.decorator.rownum.entity.SccParamProcessoDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;


/**
 * @author vagner.souza
 *
 */
public class ProcessoParametrosExcelHandler extends BasicExcelHandler{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<SccParamProcessoDecorator> decoratorList = (List<SccParamProcessoDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getProcesso", "Processo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDtExercicio", "Dt. Exercicio", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getParametro", "Parametro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTipo", "Tipo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValor", "valor", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatorio de Parametros");
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
