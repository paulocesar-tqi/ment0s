/**
 * 
 */
package com.claro.sccweb.controller.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.view.SccAcordoParcelamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.SccAcordoParcelamentoForm;
import com.claro.sccweb.form.SccDisputaPrePagoForm;

/**
 * @author vagner.souza
 *
 */
public class SccAcordoParcelamentoExcelHandler extends BasicExcelHandler {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String datam2 = dateFormat.format(new Date()); 
		
		//response.setHeader("Content-Disposition","attachment; filename=\"disputa"+ datam2 + ".xls\"");
		SccAcordoParcelamentoForm form = (SccAcordoParcelamentoForm) model.get("filtro");
		String relatorio = "Sintetico";
		List <SccAcordoParcelamentoViewDecorator> list = null;
		if(form.getIsSintetico()){
			relatorio = "Sintetico";
			list = form.getListSintetico();
		}else{
			relatorio = "Analitico";
			list = form.getListAnalitico();
		}
			
		
		if (list == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		
		if(form.getIsSintetico()){
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora Ld", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getDataAcordo", "Data de Acordo", style, 12));
			columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", dateStyle, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getQtdParcelas",	"Quantidade de Parcelas", dateStyle, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getValorAcordado",	"Valor Acordado", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getVlParcelaOperadora",	"Valor Operadora Ld", style, 15));
			
		}else{
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora Ld", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getDataAcordo", "Data de Acordo", style, 12));
			columnDefinitions.add(new ExcelColumnDefinition("getNumConta",	"Status", dateStyle, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getQtdParcelas",	"Numero da Conta", dateStyle, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getCodAcordo",	"Codigo Acordo", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getNumFatura",	"Numero da Fatura", style, 20));
			columnDefinitions.add(new ExcelColumnDefinition("getValorAcordado",	"Valor Acordado", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getNumAcordoParcelamento",	"Quantidade de Parcelas", style, 10));
			
		}
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Parcelamento Acordo");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Parcelamento Acordo" + relatorio);
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(list);
		printer.writeData();
		

		


	}

}
