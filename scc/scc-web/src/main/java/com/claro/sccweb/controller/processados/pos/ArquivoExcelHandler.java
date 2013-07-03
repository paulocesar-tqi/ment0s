package com.claro.sccweb.controller.processados.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

@Controller
public class ArquivoExcelHandler extends BasicExcelHandler {

	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<SccArquivoCobillingDecorator> decoratorList = (List<SccArquivoCobillingDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		if (decoratorList == null)
			throw new ControllerExecutionException("Navegação inválida. Pesquisa de arquivos não encontrada no cache.");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoRemessa", "Arquivo de Remessa", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getArqProtocolo", "Protocolo", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivo", "Nome do Arquivo", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraExterna", "Operadora Externa", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataProcClaro", "Data Proc. Claro", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataReferencia", "Data Referência", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataInicioTrafego", "Data Início", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataFinalTrafego", "Data Final", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtCDR", "Qtd. CDRs", style, 15));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Arquivos Processados");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Claro - Relatório de Descrição de Arquivo");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();					
	}

	
	
}
