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
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccIndicadorPrePagoExcelExportHandler extends BasicExcelHandler {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SccIndicadorForm form = (SccIndicadorForm) model.get("filtro");
		
		if(form != null && form.getListIndicador() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCdIndicador", "Indicador", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDsIndicador", "Descrição", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdDwlind", "DsWlind", style, 5));
		columnDefinitions.add(new ExcelColumnDefinition("getDsPeriodicidade", "Periodicidade", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDsFormatoIndicador", "Formato", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdTipoIndicador", "Tipo Indicador", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdStatusIndicador", "Status", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDtReferencia", "Data Referencia", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtCriacao", "Data Criação", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtAtualizacao", "Data Atualização", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdUsuarioManut", "Usuario", dateStyle, 15));
		
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Indicador Pre Pago");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData((List) form.getListIndicador());
		printer.writeData();
		
		
	}

	
}
