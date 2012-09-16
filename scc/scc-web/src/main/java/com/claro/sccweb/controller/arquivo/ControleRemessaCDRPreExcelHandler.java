package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class ControleRemessaCDRPreExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<RelCDRPrePagoView> view = (List<RelCDRPrePagoView>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (view == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD", "Operadora LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getUf", "UF", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDataChamada", "Data da Chamada", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataCarga", "Data da Apuração", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataFechamento", "Data de Fechamento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnAssinante", "CN do Assinante", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOrigemChamada", "Origem da Chamada", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraOrigem", "Operadora de Origem", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCodigoDefeito", "Código de Defeito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getGrupoHorario", "Grupo Horário", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTipoChamada", "Tipo de Chamada", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusRegistro", "Status do Registro", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("CDRs");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(view);
		printer.writeData();
	}

	
	
}
