package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.view.RelatorioApuracaoPreSumarizado;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioResumoApuradoPreExcelHandler extends BasicExcelHandler{

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<RelatorioApuracaoPreSumarizado> decoratorList = (List<RelatorioApuracaoPreSumarizado>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		List<RelatorioApuracaoPreSumarizado> decoratorListTotal = (List<RelatorioApuracaoPreSumarizado>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_2, request);
		decoratorList.add(decoratorListTotal.get(0));
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDsOperadora",	"UF - OP Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorApuradoLiquido",	"Vlr Apurado Liquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofins",	"Pis Cofins", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorIcmsRepassar",	"ICMS A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorIcmsNaoRepassado",	"ICMS Não Repassado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassar",	"Vlr A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getServicoPrestadoLiquido",	"Serv Prest Liquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofinsServicePrestado",	"Pis Cofins Serv Prest", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIss",	"Iss", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBrutoServico",	"Vlr Bruto Serv Prest", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCreditosAutorizados",	"Créditos Autorizados", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCreditos226",	"Créditos 226", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPenalidadesMinutosPerdidos",	"Penalidades Minutos Perdidos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalMultasJuros",	"Total Multas e Juros", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalAcertosConciliacoes",	"Total Acertos Conciliações", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCpmfDescontar",	"CPMF A Descontar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsDescontar",	"ICMS A Descontar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassar",	"ICMS A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorFinalRepassar",	"Vlr Final A Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorNotaFiscal", "Valor Nota Fiscal", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDestaqueIcms", "Destaque ICMS", style, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Resumo Apuração");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Cobilling Pré Pago - Relatório Resumo de Apuração");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}

}
