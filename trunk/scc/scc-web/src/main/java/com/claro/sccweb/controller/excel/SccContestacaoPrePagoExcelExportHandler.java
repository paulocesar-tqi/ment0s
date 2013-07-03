package com.claro.sccweb.controller.excel;

import java.text.SimpleDateFormat;
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
import com.claro.sccweb.form.SccContestacaoPrePagoForm;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccContestacaoPrePagoExcelExportHandler extends BasicExcelHandler {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String datam2 = dateFormat.format(new Date()); 
		
		response.setHeader("Content-Disposition","attachment; filename=\"contestacaoprepago"+ datam2 + ".xls\"");

		
		SccContestacaoPrePagoForm form = (SccContestacaoPrePagoForm) model.get("filtro");
		
		if(form != null && form.getListSccContestacaoPrePago() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getSqContestacaoPrePago", "ID", style, 5));
		columnDefinitions.add(new ExcelColumnDefinition("getSccOperadora.getCdEot", "Operadora Ld", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDtProtocoloLd", "Dt. Carta Ld Protocolo", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtRepasseContestacao", "Data Dem Repasse Contestação", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtRepasseScc", "Data Repasse Liberação", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtLiberacaoRepasse", "Data Repasse Liberação", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdStatusContestacao", "Status", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getFgRateioManual", "Rel. Manual", style, 5));
		
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Contestação Pre Pago");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData((List) form.getListSccContestacaoPrePago());
		printer.writeData();
		
		
	}

	
}
