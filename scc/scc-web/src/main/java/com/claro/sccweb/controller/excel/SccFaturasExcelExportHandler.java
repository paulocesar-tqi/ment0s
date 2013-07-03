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
import com.claro.sccweb.form.SccFaturasForm;

/**
 * Handler para conversão da pesquisa de arquivos do Connect em Excel.
 *
 */
public class SccFaturasExcelExportHandler extends BasicExcelHandler {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

/*		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String datam2 = dateFormat.format(new Date()); 
		
		response.setHeader("Content-Disposition","attachment; filename=\"agingfaturas"+ datam2 + ".xls\"");

*/		
		SccFaturasForm form = (SccFaturasForm) model.get("filtro");
		
		if(form != null && form.getListFaturas() == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
			
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		columnDefinitions.add(new ExcelColumnDefinition("getEotClaro", "Operadora Claro ", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCsp", "Csp", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD", "Operadora Ld", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getUf", "UF", style, 3));
		columnDefinitions.add(new ExcelColumnDefinition("getCicloMesAno", "Ciclo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroFatura", "Numero da Fatura", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataEmissao", "Dt. Emissão", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getDataVencimento", "Vencimento", dateStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValor", "Valor", currencyStyle, 15));

		columnDefinitions.add(new ExcelColumnDefinition("getValorOriginal", "Valor Original", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorICMS", "Valor ICMS", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getStatus", "Status", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSituacaoEvento", "Situação/Evento", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getAging", "Aging", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getAjuste", "Ajuste", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getNumeroNotaFiscal", "Numero Nota Fiscal", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSerie", "Serie", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSubSerie", " SubSerie", style, 15));

		columnDefinitions.add(new ExcelColumnDefinition("getTotalCreditos", "Total de Creditos ", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorOfertasLD", "Ofertas Ld", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorDescontosLD", "Descontos Ld", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorCreditosLD", "Creditos Ld", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getJuros", "Juros", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorPago", "Valor Pago", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQuantidadeEventos", "Qtde. Eventos", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getJuros", "Juros", currencyStyle, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMultas", "Multa", currencyStyle, 15));
		
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatório de Faturas");
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(form.getListFaturas());
		printer.writeData();
		
		
	}

	
}
