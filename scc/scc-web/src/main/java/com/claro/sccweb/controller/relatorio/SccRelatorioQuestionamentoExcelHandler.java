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
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioQuestionamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
	
public class SccRelatorioQuestionamentoExcelHandler extends BasicExcelHandler {

		@SuppressWarnings("unchecked")
		@Override
		protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<SccRelatorioQuestionamentoViewDecorator> decoratorList = (List<SccRelatorioQuestionamentoViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
			
			if (decoratorList == null){
				throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			}
			List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
			columnDefinitions.add(new ExcelColumnDefinition("getSqQuestionamento",	"Questionamento", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getCdEotLD",	"Eot LD", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getCdStatusQuestionamento",	"Status", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getDtCredito",	"Dt Evento", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getDtProtocoloClaro",	"Dt ProtocoloClaro", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getDtProtocoloLD",	"Dt ProtocoloLD", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getDtAnalise",	"Dt Analise", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getDtCorrecao",	"Dt Correcao", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getTxComentarioClaro",	"Tx Comentario Claro", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getTxComentarioLD",	"Tx Comentario LD", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getTxAnalise",	"Tx Analise", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getTxCorrecao",	"Tx Correcao", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getTxMotivosRejeicao",	"Tx MotivosRejeicao", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getTxArquivos",	"Tx Arquivos", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getCdIdentificacaoCartaClaro",	"Ident Carta Claro", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getCdIdentificacaoCartaLD",	"Ident Carta LD", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getNoResponsavelClaro",	"Resp Claro", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getNoResponsavelLD",	"Resp LD", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getCdProcesso",	"Processo", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getCriacaoDt",	"Dt Criacao", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getAtualizacaoDt",	"Dt Atualizacao", style, 25));
			columnDefinitions.add(new ExcelColumnDefinition("getUsuarioManutCd",	"Login Usuario", style, 25));
			ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
			printer.addSheet("Questionamento");
			List<String> linhasCabecalho = new ArrayList<String>();
			
			linhasCabecalho.add("Claro - Controle do Questionamento");
			linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
			printer.setHeaderLines(linhasCabecalho);
			printer.generateHeader();
			printer.addBlankLines(1);
			printer.generateColumnsTitle();
			printer.addData(decoratorList);
			printer.writeData();
			
		}

}
