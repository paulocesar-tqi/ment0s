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
import com.claro.sccweb.decorator.RelApuracaoFechamentoPrePagoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RelatorioResumoApuradoPreExcelHandler extends BasicExcelHandler{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<RelApuracaoFechamentoPrePagoViewDecorator> decoratorList = (List<RelApuracaoFechamentoPrePagoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorApuradoLiquido",	"Vl. Apurado Liq.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofins",	"Pis/Cofins", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorIcmsRepassar",	"ICMS a Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorIcmsNaoRepassado",	"ICMS Não Repassado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassar",	"Valor Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getServicoPrestadoLiquido",	"Ser. Prest. Líquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofinsServicePrestado",	"Pis/Cofins Ser. Prestado", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIss",	"ISS", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBrutoServico",	"Vlr. Bruto Serv.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCreditosAutorizados",	"Créd. Autorizados", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCreditos226",	"Créd. 226", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPenalidadesMinutosPerdidos",	"Penalidades Min. Perd.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalMultasJuros",	"Total Jutos e Multas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotalAcertosConciliacoes",	"Total Acertos e Conc.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCpmfDescontar",	"CPMF Descontar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsDescontar",	"ICMS Descontar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassar",	"ICMS Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorFinalRepassar",	"Vlr. Final Repassar", style, 30));
		
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
		printer.generateFile("Resumo_Apuração.xls");

		
	}

}
