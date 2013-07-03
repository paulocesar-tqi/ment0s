/**
 * 
 */
package com.claro.sccweb.controller.relatorio.pos;

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
import com.claro.sccweb.form.RelatorioAlocadasMobileForm;

/**
 * @author vagner.souza
 *
 */
public class RelatorioAlocadasMobileExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		RelatorioAlocadasMobileForm form = (RelatorioAlocadasMobileForm) model.get("filtro");
		
		if (form.getLstAlocadasMobile() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivoReferencia",	"Nome do Arquivo", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCiclo",	"Ciclo", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtChamadas",	"Quantidade de Chamadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtMinutoTarifadosStr",	"Total de Minutos Tarifados", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlLiquidoStr",	"Valor Líquido Total das Chamadas", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDtRelatorio",	"Data de Emissão do Relatório", dateStyle, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getDtChamada",	"Data da Chamada", dateStyle, 12));

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Alocadas no Mobile");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Alocadas no Mobile");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(form.getLstAlocadasMobile());
		printer.writeData();

	}

}
