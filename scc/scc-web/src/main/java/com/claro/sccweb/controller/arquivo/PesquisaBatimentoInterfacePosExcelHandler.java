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
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoInterfaceViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;

public class PesquisaBatimentoInterfacePosExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccBatimentoInterfaceViewDecorator> decoratorList = (List<SccBatimentoInterfaceViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivo",	"Nome do Arquivo", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora LD", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataMovimentacao",	"Data de Movimentação", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataTransferencia",	"Data de Transferência", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeRegistrosMobile",	"Quantidade de Registros", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataProcessamento",	"Data de Processamento", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeRegistrosScc",	"Quantidade de Registros", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDiferenca",	"Diferença", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 8));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Batimento Interface Pos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Batimento Interface Pós");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		
		List<String> linha = new ArrayList<String>();
		linha.add(" ");
		linha.add(" ");
		linha.add(" ");
		linha.add("MOBILE");
		linha.add(" ");
		linha.add(" ");
		linha.add("SCC");
		linha.add(" ");
		linha.add(" ");
		linha.add(" ");
		List<Short> mergeRanges = new ArrayList<Short>();
		mergeRanges.add((short) 3);
		mergeRanges.add((short) 5);
		mergeRanges.add((short) 6);
		mergeRanges.add((short) 7);
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
				return HSSFCellStyle.ALIGN_CENTER;
			}

			public boolean getWrapText() {
				return true;
			}

			public String getFormat() {		
				return null;
			}

		};
		
		printer.addLine(linha, mergeRanges, myStyle);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

	}

}
