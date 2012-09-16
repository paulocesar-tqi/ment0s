package com.claro.sccweb.controller.saldo;

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
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.DemonstrativoSaldoLotesForm;
import com.claro.sccweb.service.data.saldos.TabelaDemonstrativoSaldo;

public class DemonstrativoSaldoLotesExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<TabelaDemonstrativoSaldo> tabela  = (List<TabelaDemonstrativoSaldo>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (tabela == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		DemonstrativoSaldoLotesForm form = (DemonstrativoSaldoLotesForm)getFormFromCache(DemonstrativoSaldoLotesController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getEvento", "Evento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCodigo", "Cod", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMotivo", "Motivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtCdrs", "CDRs", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPerCdrs", "% CDRs", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtMinutos", "Minutos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPerMinutos", "% Minutos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValor", "Valor", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPerValor", "% Valor", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Demonstrativo");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Demonstrativo de Saldo de Lotes");
		linhasCabecalho.add("Data do Demonstrativo: "+dateFormat.format(new Date()));
		linhasCabecalho.add("Operadora Claro: "+form.getCdEOTClaro());
		linhasCabecalho.add("Operadora LD: "+form.getCdEOTLD());
		linhasCabecalho.add("Produto: "+form.getCdProdutoCobilling());
		linhasCabecalho.add("Tipo Arquivo: "+form.getCdTipoArquivo());
		linhasCabecalho.add("Data Inicial: "+form.getMesInicio()+"/"+form.getAnoInicio());
		linhasCabecalho.add("Data Final: "+form.getMesFinal()+"/"+form.getAnoFinal());
		
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
	}

	
	
}
