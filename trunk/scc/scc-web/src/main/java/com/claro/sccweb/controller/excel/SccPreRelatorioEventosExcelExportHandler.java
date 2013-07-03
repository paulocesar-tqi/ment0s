/**
 * 
 */
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
import com.claro.sccweb.decorator.rownum.entity.SccPreRelatorioEventosViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;

/**
 * @author 93046251
 *
 */
public class SccPreRelatorioEventosExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<SccPreRelatorioEventosViewDecorator> decoratorList = (List<SccPreRelatorioEventosViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDtReferencia",	"Data Referência", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraClaro",	"Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadoraExterna",	"Operadora Ld", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusChamada",	"Status da Chamada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMotivoRejeicao",	"Cd Descrição Rejeição", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDsDefeito",	"Cd Descrição Defeito", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeCdrs",	"Quantidade", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoReal",	"Duração Real", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifada",	"Duração Tarifada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto",	"Valor Bruto", style, 15));

		Map<String,Object> totais = (Map<String,Object>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_2, request);
		List<String> footer = new ArrayList<String>();
		footer.add("Total:");
		footer.add((String) totais.get("totalRegistros"));
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add(" ");
		footer.add((String) totais.get("totalQuantidade"));
		footer.add((String) totais.get("totalDuracaoReal"));
		footer.add((String) totais.get("totalDuracaoTarifada"));
		footer.add((String) totais.get("totalValorBruto"));

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
		printer.addSheet("Eventos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Eventos");
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
