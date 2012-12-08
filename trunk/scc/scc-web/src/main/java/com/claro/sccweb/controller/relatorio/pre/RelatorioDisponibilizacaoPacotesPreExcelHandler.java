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
import com.claro.sccweb.decorator.rownum.view.DisponibilizacaoPacotePrePagoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioDisponibilizacaoPacotesPreExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DisponibilizacaoPacotePrePagoViewDecorator> decoratorList = (List<DisponibilizacaoPacotePrePagoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDtReferencia",	"Data Referência", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getTerminal",	"Terminal", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDescricaoPacote",	"Descrição dos Pacotes", style, 35));
		columnDefinitions.add(new ExcelColumnDefinition("getProduto",	"Produto", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdPacotes",	"Qtd Pacotes", integerStyle, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getVlBruto",	"Valor Bruto", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdChamadas",	"Qtd CDRs", integerStyle, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoReal",	"Duração Real", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdConsumida",	"Duração Tarifada", style, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Disponibilização Pacotes");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Disponibilização Pacotes");
		linhasCabecalho.add("Data Geração: "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		
		printer.generateColumnsTitle();
		
		List<DisponibilizacaoPacotePrePagoViewDecorator> lst = new ArrayList<DisponibilizacaoPacotePrePagoViewDecorator>();
		lst.addAll(decoratorList);
		
		printer.addData(lst);
		printer.writeData();
		

	}

}
