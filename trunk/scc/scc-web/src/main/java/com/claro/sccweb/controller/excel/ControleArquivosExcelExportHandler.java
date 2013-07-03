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
import com.claro.sccweb.form.ControleArquivoForm;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class ControleArquivosExcelExportHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ControleArquivoForm form = (ControleArquivoForm) model.get("filtro");
		
		if(form != null && form.getListControlConnectFiles() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getPk.getPROC_NUMB", "ID", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getORIG_FILE", "Nome do Arquivo", style, 50));
		columnDefinitions.add(new ExcelColumnDefinition("getEXIT_CODE", "Código de Saída", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getPk.getSTRT_DATE", "Data de Inicio", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSTOP_DATE", "Data Fim", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getEXIT_DESC", "Info", style, 50));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Controle de Arquivos");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Claro - Controle de Arquivos.");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(form.getListControlConnectFiles());
		printer.writeData();
		
		
	}

	
	
	
	
}
