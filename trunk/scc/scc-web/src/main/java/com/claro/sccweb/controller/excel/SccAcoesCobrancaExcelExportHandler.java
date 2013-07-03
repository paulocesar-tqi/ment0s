package com.claro.sccweb.controller.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.SccAcoesCobrancaForm;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccAcoesCobrancaExcelExportHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		SccAcoesCobrancaForm form = (SccAcoesCobrancaForm) model.get("filtro");
		
		if(form != null && form.getListAcoesCobranca() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getBan", "BAN", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTerminal", "Terminal", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getFaturaLD", "Fatura Ld", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorFatura", "Valor Fatura", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataEmissao", "Data Emissao", dateStyle, 12));
		columnDefinitions.add(new ExcelColumnDefinition("getDataVencimento", "Data Vencimento", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataCarta", "Data Carta", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoCarta", "Arquivo Carta", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getDataConnectCarta", "Data Connect Arq Carta", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataBloqueio", "Data Bloqueio", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoBloqueio", "Arquivo Bloqueio", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getDataConnectBloqueio", "Data Connect Arq Bloqueio", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataPagamento", "Data Pagamento", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getArquivoPagamento", "Arquivo de Pagamento", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getDataConnectPagamento", "Data de Pagamento", dateStyle, 15));

		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Controle Ações de Cobranca");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Claro - Relatório de Controle Ações de Cobrança");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(form.getListAcoesCobranca());
		printer.writeData();
		
		
	}

	
}
