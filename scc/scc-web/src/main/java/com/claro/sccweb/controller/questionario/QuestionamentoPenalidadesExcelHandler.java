/**
 * 
 */
package com.claro.sccweb.controller.questionario;

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
import com.claro.sccweb.decorator.view.SccQuestionamentoPenalidadeViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author vagner.souza
 *
 */
public class QuestionamentoPenalidadesExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<SccQuestionamentoPenalidadeViewDecorator> decoratorList = (List<SccQuestionamentoPenalidadeViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao",	"Descrição", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdChamadas",	"Qtd. Chamadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdMinutos",	"Qtd. Minutos", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlrLiquido",	"Vlr. Liquido", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlrBruto",	"Vlr. Bruto R$", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdChamadasRefaturadas",	"Qtd. Chamadas Refaturadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdMinutosRefaturadas",	"qtd. Minutos", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdChamadasNaoRefaturadas",	"Qtd. Chamadas Não Refaturadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdMinutosNaoRefaturadas",	"Qtd. Minutos Não Refaturadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlrPenalidade",	"Vlr. Penalidade R$", currencyStyle, 15));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Questionamento Penalidade");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Questionamento Penalidade");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

		


	}

}
