package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.claro.cobillingweb.persistence.view.SccRetornoRepasseView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoInterfaceViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;

public class ConsultaRetornoRepassePosExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccRetornoRepasseView> rows = (List<SccRetornoRepasseView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (rows == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
 
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCsp",	"CSP", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getUf", "UF", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getMes",	"Mes Conta", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getValor",	"Valor", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNumRepasse",	"Demonstrativo de Repasse", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivo",	"Nome do Arquivo", style, 40));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Retorno de Repasse Pos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Retorno de Repasse Pós");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		
		printer.generateColumnsTitle();
		printer.addData(rows);
		printer.writeData();
		

	}

}
