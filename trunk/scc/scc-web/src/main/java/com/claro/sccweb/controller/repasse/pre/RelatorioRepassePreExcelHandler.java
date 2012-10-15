package com.claro.sccweb.controller.repasse.pre;

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
import com.claro.sccweb.decorator.RelSinteticoFechamentoPrePagoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioRepassePreExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<RelSinteticoFechamentoPrePagoViewDecorator> decoratorList = (List<RelSinteticoFechamentoPrePagoViewDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navega��o inv�lida. Tabela � nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getUf",	"UF.", style, 3));
		columnDefinitions.add(new ExcelColumnDefinition("getTrafego",	"Traf�go", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnAssinante",	"CN Ass.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnOrigem",	"CN Orig.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdEotOrigem",	"EOT Orig.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTipo",	"Tipo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPeriodo",	"Per�odo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadas",	"Chamadas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutos",	"Minutos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido",	"Vlr. L�quido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofins",	"Pis/Cofins", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassar",	"ICMS Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassar",	"Vlr. Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsNaoRepassado",	"ICMS N�o Repassado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto",	"Vlr. Bruto", style, 30));
	
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Sintetico");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Cobilling Pr� Pago - Relat�rio Sint�tico de Chamadas");
		linhasCabecalho.add("Data Gera��o "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();

	}

}
