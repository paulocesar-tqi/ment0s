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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccArquivosFiscaisViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author 93046251
 *
 */
public class RelatorioArquivosFiscaisExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	
		List<SccArquivosFiscaisViewDecorator> decoratorList = (List<SccArquivosFiscaisViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getCdCSP",	"Operadora LD", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getSgUF",	"UF", style, 2));
		columnDefinitions.add(new ExcelColumnDefinition("getAnoCiclo",	"Ano", style, 4));
		columnDefinitions.add(new ExcelColumnDefinition("getMesCiclo",	"Mês", style, 2));
		columnDefinitions.add(new ExcelColumnDefinition("getCodigoCiclo",	"Ciclo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"Nome do Arquivo", style, 35));
		columnDefinitions.add(new ExcelColumnDefinition("getNoDiretorioArquivo",	"Diretório do Arquivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDtStatusArquivo",	"Data de Status", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCdStatusArquivo",	"Status do Arquivo", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getCdMotivoRejeicaoArquivo",	"Motivo Rejeição", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtRegistros",	"Quantidade Registros", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getVlBrutoArquivo",	"Valor Bruto", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getVlICMS",	"Valor ICMS", style, 15));

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatórios Arquivos Fiscais");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Arquivos Fiscais");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		
		
		
	}
	
	

}
