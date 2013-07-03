package com.claro.sccweb.controller.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.sccweb.controller.BaseExcelFormHandler;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.RelSinteticoFechamentoPrePagoViewDecorator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class RecepcaoTransmissaoExcelExportHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<SccArquivoCobillingDecorator> decoratorList = (List<SccArquivoCobillingDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDataProcClaro",	"Dt Proc Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDataCarga",	"Dt Proc PPC", style, 14));
		columnDefinitions.add(new ExcelColumnDefinition("getDataConnect",	"Dt Connect", style, 14));
		columnDefinitions.add(new ExcelColumnDefinition("getDataReferencia",	"Dt Referencia", style, 14));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivo",	"Nome", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoRemessa",	"Remessa", style, 50));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivoRetorno",	"Retorno", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getArqProtocolo",	"Protocolo", style, 45));
		columnDefinitions.add(new ExcelColumnDefinition("getDummy",	"Dummy", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQtDuracaoTarifada",	"Duração Tarifada", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtCDR",	"Quantidade", style, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido",	"Valor Liquido", style, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto",	"Valor Bruto", style, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getErroProtocolo",	"Erro Protocolo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDescErroProtocolo2",	"Desc Erro Protocolo", style, 45));
	
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Resultados");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Relatório de Recepção e Transmissão");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();

	}
		
	}


