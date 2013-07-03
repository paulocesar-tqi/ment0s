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
import com.claro.sccweb.decorator.rownum.entity.SccExtracaoCDRsViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class RelatorioExtracaoCDRsExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	
		List<SccExtracaoCDRsViewDecorator> decoratorList = (List<SccExtracaoCDRsViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroDeA",	"Numero do Terminal de A", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroDeB",	"Numero do Terminal de B", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifada",	"Duração Tarifada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtChamada",	"Data da Chamada", style, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusChamada",	"Status da Chamada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMotivoRejeicao",	"Motivo de Rejeição", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Extração de CDRS");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Extração de CDRS");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		
		
		
	}
	
	

}
