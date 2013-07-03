/**
 * 
 */
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
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoArquivosViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;

/**
 * @author vagner.souza
 *
 */
public class SccRelatorioBatimentoArquivosExcelHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<SccBatimentoArquivosViewDecorator> decoratorList = (List<SccBatimentoArquivosViewDecorator>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDtConnectClaro",	"Dt Connect", dateStyle, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getDtReferenciaClaro",	"Dt Referencia", dateStyle, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivoClaro",	"Remessa", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getDnsProtocoloClaro",	"DNS Protocolo", style, 35));
		columnDefinitions.add(new ExcelColumnDefinition("getDuracaoTarifadaClaro",	"Duração Tarifada", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeClaro",	"Quantidade", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquidoClaro",	"Valor Liquido", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getErroProtocoloClaro",	"Erro Protocolo", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getDescErroProtocoloClaro",	"Desc Erro Protocolo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNomeArquivoLD",	"Dsname", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeLD",	"Quantidade", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatusLD",	"Status", style, 10));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeBat",	"Quantidade", style, 15));
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Batimento de Arquivos");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Batimento de Arquivos");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();
		

		


	}

}
