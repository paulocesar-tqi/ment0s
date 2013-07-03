/**
 * 
 */
package com.claro.sccweb.controller.relatorio.pre;

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
import com.claro.sccweb.form.RelatorioConfirmacaoRepasseForm;

/**
 * @author vagner.souza
 *
 */
public class SccRelatorioConfirmacaoRepasseExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		RelatorioConfirmacaoRepasseForm form = (RelatorioConfirmacaoRepasseForm) model.get("filtro");
		
		
		if (form.getLstConfirmacaoRepasse() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getAnoMesRepasse",	"Ano / Mês", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 25));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro / UF", style, 25));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepasse",	"Valor do Repasse", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusRepasse",	"Status do Repasse", style, 15));
		
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Confirmação de Repasse");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Confirmação de Repasse");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(form.getLstConfirmacaoRepasse());
		printer.writeData();
		

		


	}

}
