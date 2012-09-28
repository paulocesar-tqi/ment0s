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
import com.claro.sccweb.decorator.rownum.entity.RelatorioConciliacaoPreDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.RelatorioConciliacaoPreForm;

public class RelatorioConciliacaoPreExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<RelatorioConciliacaoPreDecorator> tabela = (List<RelatorioConciliacaoPreDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (tabela == null)
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		RelatorioConciliacaoPreForm form = (RelatorioConciliacaoPreForm)getFormFromCache(RelatorioConciliacaoPreController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navega��o inv�lida. Form � nulo!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDataLancamento", "Data de Lan�amento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao", "Descri��o", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getContaCredito", "Conta Cont�bil Cr�dito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getContaDebito", "Conta Cont�bil D�bito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLd", "Operadora LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getLocalNegocio", "Local de Neg�cio", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getEmpresaContabil", "Empresa Cont�bil", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto", "Valor Bruto", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Concilia��o");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
	}

	
	
}
