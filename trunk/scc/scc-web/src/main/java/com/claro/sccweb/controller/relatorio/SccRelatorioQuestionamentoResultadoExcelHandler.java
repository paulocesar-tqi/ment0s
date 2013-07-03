package com.claro.sccweb.controller.relatorio;

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
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioQuestionamentoResultadoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
	
public class SccRelatorioQuestionamentoResultadoExcelHandler extends BasicExcelHandler {

		@SuppressWarnings("unchecked")
		@Override
		protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<SccRelatorioQuestionamentoResultadoViewDecorator> decoratorList = (List<SccRelatorioQuestionamentoResultadoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
			
			if (decoratorList == null){
				throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			}
			List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
			columnDefinitions.add(new ExcelColumnDefinition("getSqQuestionamento",	"Questionamento", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getSqArquivo",	"Arquivo", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"Arquivo", style, 32));
			columnDefinitions.add(new ExcelColumnDefinition("getCdEotLD",	"Eot LD", style, 12));
			columnDefinitions.add(new ExcelColumnDefinition("getCdEotClaro",	"Eot Claro", style, 12));
			columnDefinitions.add(new ExcelColumnDefinition("getDtProtocoloLD",	"Dt ProtocoloLD", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getDtProtocoloClaro",	"Dt ProtocoloClaro", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getDtCorrecao",	"Dt Correcao", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getDtAnalise",	"Dt Analise", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getVlPotencialLD",	"Vlr PotencialLD", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getVlPotencialClaro",	"Vlr PotencialClaro", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getVlPenalidadeLD",	"Vlr PenalidadeLD", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getVlPenalidadeClaro",	"Vlr PenalidadeClaro", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getPeProcedente",	"Procedente (%)", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getPeSemAnalise",	"Sem Analise (%)", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getQtCdrs",	"Qtde Cdrs", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getMiTarifados",	"Min Tarifados", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getDtRepasse",	"Dt Repasse", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getCriacaoDt",	"Dt Criacao", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getAtualizacaoDt",	"Dt Atualizacao", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getUsuarioManutCd",	"Login Usuario", style, 20));
			ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
			printer.addSheet("Resultados do Questionamento");
			List<String> linhasCabecalho = new ArrayList<String>();
			
			linhasCabecalho.add("Claro - Resultados do Questionamento");
			linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
			printer.setHeaderLines(linhasCabecalho);
			printer.generateHeader();
			printer.addBlankLines(1);
			printer.generateColumnsTitle();
			printer.addData(decoratorList);
			printer.writeData();
			
		}

}
