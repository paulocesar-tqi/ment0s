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
import com.claro.sccweb.decorator.rownum.entity.SccAssinaturaPrePagoDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioDisponibilizacaoPacotesPreExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccAssinaturaPrePagoDecorator> decoratorList = (List<SccAssinaturaPrePagoDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		/*
		 * 							<display:column property="dtReferencia" title="Data Refer�ncia"/>
							<display:column property="operadoraClaro" title="Operadora Claro"/>
							<display:column property="operadoraLD" title="Operadora LD"/>
							<display:column property="terminal" title="Terminal"/>
							<display:column property="status" title="Status"/>
							<display:column property="descricaoPacote" title="Descri��o dos Pacotes"/>
							<display:column property="produto" title="Produtos"/>
							<display:column property="qtdChamadas" title="Qtd CDRs"/>
							<display:column property="qtdPacotes" title="Qtd Pacotes"/>
							<display:column property="duracaoReal" title="Dura��o Real"/>
							<display:column property="qtdConsumida" title="Dura��o Tarifada"/>
							<display:column property="vlBruto" title="Valor Bruto"/>
		 */
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDtReferencia",	"Data Refer�ncia", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTerminal",	"Terminal", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDescricaoPacote",	"Descri��o dos Pacotes", style, 35));
		columnDefinitions.add(new ExcelColumnDefinition("getProduto",	"Produtos", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdChamadas",	"Qtd CDRs", integerStyle, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdPacotes",	"Qtd Pacotes", integerStyle, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoReal",	"Dura��o Real", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdConsumida",	"Dura��o Tarifada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlBruto",	"Valor Bruto", currencyStyle, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Disponibiliza��o Pacotes");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Disponibiliza��o Pacotes");
		linhasCabecalho.add("Data Gera��o: "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

	}

}
