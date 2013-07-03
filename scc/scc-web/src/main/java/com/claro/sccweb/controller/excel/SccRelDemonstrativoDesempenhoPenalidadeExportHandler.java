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

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccRelDemonstrativoDesempenhoPenalidadeViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class SccRelDemonstrativoDesempenhoPenalidadeExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	
		List<SccRelDemonstrativoDesempenhoPenalidadeViewDecorator> decoratorList = (List<SccRelDemonstrativoDesempenhoPenalidadeViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getIndicador",	"Ref", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDescIndicador",	"Nome", style, 35));
		columnDefinitions.add(new ExcelColumnDefinition("getRespIndicador",	"Resp", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getVlOrigem",	"Real %", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMetaIndicador",	"Meta %", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getPesoIndicador",	"Peso %", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getAtingimento",	"Atingimento %", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getAcelerador",	"Acelerador", style, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Demonstrativo Desempenho");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Demonstrativo de Desempenho e Penalidade(SLA)");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		
		
		
	}
	
	

}
