package com.claro.sccweb.controller.relatorio.pos;

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
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioChamadasRefaturamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

public class SccRelatorioChamadasRefaturamentoExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<SccRelatorioChamadasRefaturamentoViewDecorator> decoratorList = (List<SccRelatorioChamadasRefaturamentoViewDecorator>) getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroA",	"Número de A", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroB",	"Número de B", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getDataHora",	"Data/Hora", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getValor",	"Valor", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutoTarifado",	"Minuto Tarifado", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCodigoCriticaInicial",	"Código da Crítica Inicial", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoRemessa",	"Arq. Rem. Original", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoRetorno",	"Arq. Retorno", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoRemessaRefaturamento",	"Arq. Rem. Refaturamento", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Chamadas Refaturamento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Cobilling Pré Pago - Relatório de Chamadas de Refaturamento");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
	}
}
