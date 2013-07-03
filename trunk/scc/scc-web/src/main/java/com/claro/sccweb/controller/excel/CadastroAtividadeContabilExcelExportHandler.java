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
import com.claro.sccweb.decorator.rownum.entity.SccAtividadeContabilDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class CadastroAtividadeContabilExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<SccAtividadeContabilDecorator> decoratorList = (List<SccAtividadeContabilDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDsAtividadeContabil",	"Descri��o", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDominioContabil",	"Atividade (Dom�nio)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getContaCredito",	"Conta Cont�bil Cr�dito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getContaDebito",	"Conta Cont�bil D�bito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLDEOT",	"Operadora LD", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getTxHistoricoAtividade",	"Hist�rico", style, 30));

		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Atividades Cont�beis");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relat�rio de Atividades Cont�beis.");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
	
	

}
