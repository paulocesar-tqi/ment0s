package com.claro.sccweb.controller.relatorio.pre;

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
import com.claro.sccweb.decorator.rownum.entity.SccPreRelatorioCriticasViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class SccPreRelatorioCriticasExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccPreRelatorioCriticasViewDecorator> decoratorList = (List<SccPreRelatorioCriticasViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDtChamada",	"DATA CHAMADA", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraClaro",	"OPERADORA LD", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraExterna",	"OPERADORA CLARO", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusChamada",	"STATUS CHAMADA", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getMotivoRejeicao",	"MOTIVO REJEICAO", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCdDefeito",	"CODIGO DEFEITO", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCsp",	"CSP", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNroA",	"NUMERO A", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNroB",	"NUMERO B", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCdPais",	"CODIGO DO PAIS", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCnAreaVisitada",	"CN DA AREA VISITADA", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTipoChamada",	"TIPO CHAMADA", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoReal",	"DURACAO REAL", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifada",	"DURACAO TARIFADA", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto",	"VALOR BRUTO", style, 20));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatório de Críticas");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Críticas Pré-Pago");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

	}

}
