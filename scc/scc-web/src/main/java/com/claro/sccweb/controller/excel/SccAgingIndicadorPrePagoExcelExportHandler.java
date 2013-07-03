package com.claro.sccweb.controller.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.SccIndicadorForm;

/**
 * Handler para convers�o da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccAgingIndicadorPrePagoExcelExportHandler extends BasicExcelHandler {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SccIndicadorForm form = (SccIndicadorForm) model.get("filtro");
		
		if (form.getListAgingIndicador() == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}
		
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getId.getCdIndicador", "Codigo Indicador", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getId.getSqAgingIndicador", "Codigo Aging", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getVlMinimoAging", "Valor M�nimo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlMaximoAging", "Valor M�ximo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtCriacao", "Data Cria��o", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtAtualizacao", "Data Atualiza��o", dateStyle, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getCdUsuarioManut", "Usuario", style, 40));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Aging Indicador Pre Pago");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Claro - Gest�o do Aging do Indicador Pre Pago");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData((List) form.getListAgingIndicador());
		printer.writeData();
		
		
	}

	
}
