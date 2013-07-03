package com.claro.sccweb.controller.relatorio.pos;

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
import com.claro.sccweb.decorator.rownum.entity.SccChamadasRefaturamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioChamadasRefaturamentoExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<SccChamadasRefaturamentoViewDecorator> decoratorList = (List<SccChamadasRefaturamentoViewDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getMesReferencia",	"M�s Refer�ncia", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTipoRefaturamento",	"Tipo de Reenvio", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusChamada",	"Status da Chamada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidade",	"Quantidade", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutagem",	"Minutagem", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalBruto",	"Total Bruto", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalLiquido",	"Total L�quido", style, 20));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Chamadas Refaturamento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Cobilling Pr� Pago - Relat�rio de Chamadas de Refaturamento");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
}
