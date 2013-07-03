package com.claro.sccweb.controller.arquivo;

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

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCreditoDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;

public class ArquivosCreditoExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<SccArquivoCreditoDecorator> view = (List<SccArquivoCreditoDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (view == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		
		Map<String,Object> totais = (Map<String,Object>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_2, request);
		List<String> footer = new ArrayList<String>();
		footer.add("Total:");
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add((String) totais.get("totalRegistros"));
		
		ExcelStyle myStyle = new ExcelStyle() {
			public String getFontName() {
				return "Arial";
			}
			public short getFontColor() {
				return HSSFColor.BLUE.index;
			}
			public short getFontHeight() {
				return 8;
			}
			public short getBoldweight() {
				return HSSFFont.BOLDWEIGHT_BOLD;
			}
			public short getAlignment() {
				return HSSFCellStyle.ALIGN_LEFT;
			}
			public boolean getWrapText() {
				return true;
			}
			public String getFormat() {		
				return null;
			}
		};

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getRow.getNoArquivo", "Nome do Arquivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getRow.getSqArquivoCredito", "Arquivo Crédito", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getOrigem", "Origem", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus", "Status", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataProcessamento", "Data Processamento", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getRow.getQtRegistros", "Registros", style, 20));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Arquivos");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(view);
		printer.writeData();
		printer.addLine(footer, null, myStyle);
	}

	
}
