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
import com.claro.sccweb.decorator.rownum.entity.SccContingenciaFiscalViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioContingencialFiscalExcelExportHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<SccContingenciaFiscalViewDecorator> decoratorList = (List<SccContingenciaFiscalViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNomeEmpresa",	"Empresa", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdCSP",	"CSP", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getSgUF",	"UF", style, 2));
		columnDefinitions.add(new ExcelColumnDefinition("getValorFaturado",	"Valor Faturado", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBaseCalculo",	"Valor Base de Calculo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorICMS",	"Valor do ICMS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorInsencao",	"Valor de Isenção", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNfInicial",	"NF Inicial", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNfFinal",	"NF Final", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getRazaoSocial",	"Razão Social", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroCNPJ",	"Numero do CNPJ", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getInscricaoEstadual",	"Inscrição Social", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSerieNF",	"Serie/SubSerie", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Contigencia");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Contigencia Fiscal");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
	

}
