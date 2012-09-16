package com.claro.sccweb.controller.arquivo;

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
import com.claro.sccweb.decorator.rownum.entity.SccArquivoSumarizadoDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class ControleRemessaEventoExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccArquivoSumarizadoDecorator> decoratorList = (List<SccArquivoSumarizadoDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDtProcExterna",	"Data Referncia", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadora",	"Operadora Claro.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraLd",	"Operadora Externa.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsProduto",	"Produto", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsStatusCdr",	"Evento", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getInfoCiclo",	"extraInfo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidade",	"Quantidade", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido",	"VL Liq", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto",	"VL Bruto", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifada",	"Duração Tarifada (min)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCiclo",	"Ciclo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMesCiclo",	"Mês.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getAnoCiclo",	"Ano", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Evento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Controle de Remessa por Evento");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

	}

}
