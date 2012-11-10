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

import com.claro.cobillingweb.persistence.view.PenalidadeRejeicaoView;
import com.claro.cobillingweb.persistence.view.SccRetornoRepasseView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoInterfaceViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;

public class ConsultaPenalidadeRejeicaoPosExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PenalidadeRejeicaoView> rows = (List<PenalidadeRejeicaoView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (rows == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMotivoRejeicao",	"Motivo Rejeição", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdCdrs",	"Qtde. CDRs", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtdeMinutos",	"Qtde. Minutos", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getValor",	"Valor", style, 20));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Retorno de Repasse Pos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Penalidade por Rejeição Pós");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		
		printer.generateColumnsTitle();
		printer.addData(rows);
		printer.writeData();
		

	}

}
