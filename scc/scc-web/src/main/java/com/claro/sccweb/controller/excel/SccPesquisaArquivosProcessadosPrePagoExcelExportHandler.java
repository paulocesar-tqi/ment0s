package com.claro.sccweb.controller.excel;

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
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccPesquisaArquivosProcessadosPrePagoExcelExportHandler extends BasicExcelHandler {

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<SccArquivoCobillingDecorator> decoratorList = (List<SccArquivoCobillingDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivo", "NOME DO ARQUIVO", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtCDR", "QTDE REGISTRO", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataReferencia", "DATA PROCESSAMENTO", dateStyle, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataInicioTrafego", "DATA INICIAL", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataFinalTrafego", "DATA FINAL", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus", "STATUS CARGA", style, 25));
		columnDefinitions.add(new ExcelColumnDefinition("getTipoAquivo", "TIPO ARQUIVO", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtDuracaoReal", "QTDE MIN REAIS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtDuracaoTarifada", "QTDE MIN ARRED", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto", "VALOR BRUTO", style, 15));

		Map<String,Object> totais = (Map<String,Object>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_2, request);
		List<String> footer = new ArrayList<String>();
		footer.add("Total:");
		footer.add((String) totais.get("totalRegistros"));
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add((String) totais.get("totalMinReais"));
		footer.add((String) totais.get("totalMinTarifados"));
		footer.add((String) totais.get("totalValor"));
		
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
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Processados Pre Pago");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Claro - Arquivos Processados Pré Pago");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		printer.addLine(footer, null, myStyle);
		
		
	}

	
}
