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
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class AmostragemCriticaExcelHandler  extends BasicExcelHandler  {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {		
		List<SccCdrCobillingDecorator> decoratorList = (List<SccCdrCobillingDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (decoratorList == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getEvento",	"Evt.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDestinoChamada",	"Reg.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNaturezaCobranca",	"Nat.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Longa", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroA",	"Assinante A", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPaisDestino",	"País", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroB",	"Assinante B", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataChamada",	"Dt. Chamada", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getHoraChamada",	"Hr. Chamada", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracao",	"Dur. Tar.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido",	"Vl. Líq.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getLocalidadeOrigem",	"CnlOrigem", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCSP",	"CSP", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDestinoChamada",	"CnlDest", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoReal",	"Dur. Real", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getGrupoHorario",	"Grp. H.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDegrau",	"Degrau", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getAreaRoaming",	"CN", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Críticas");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Críticas");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();

	}

	
}
