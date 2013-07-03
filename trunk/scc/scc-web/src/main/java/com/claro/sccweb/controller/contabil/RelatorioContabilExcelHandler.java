package com.claro.sccweb.controller.contabil;

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
import com.claro.sccweb.decorator.rownum.entity.RelContabilViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.RelatorioContabilForm;

public class RelatorioContabilExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<RelContabilViewDecorator> tabela = (List<RelContabilViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (tabela == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		RelatorioContabilForm form = (RelatorioContabilForm)getFormFromCache(RelatorioContabilController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCdEOTLD", "Longa Distância", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdEOTClaro", "Empresa Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataFechamento", "Mês/Ano", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao", "Descrição", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdContabil", "Código Contábil", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtCdrs.", "Qtde.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getVlLiquido", "Valor Líquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getVlBruto", "Valor Bruto", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Demonstrativo");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Contábil");
		linhasCabecalho.add("Data do Demonstrativo: "+dateFormat.format(new Date()));
		linhasCabecalho.add("Operadora Claro: "+form.getCdEOTClaro());
		linhasCabecalho.add("Operadora LD: "+form.getCdEOTLD());
		linhasCabecalho.add("Motivo: "+form.getCdMotivoRejeicao());		
		linhasCabecalho.add("Período: "+form.getMes()+"/"+form.getAno());		
		
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
		
	}

	
	
}
