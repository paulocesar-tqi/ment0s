/**
 * 
 */
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
import com.claro.sccweb.decorator.view.SccAcordoParcelamentoViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.SccAcordoParcelamentoForm;

/**
 * @author vagner.souza
 *
 */
public class SccAcompanhamentoParcelamentoExcelHandler extends BasicExcelHandler {

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
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
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro.", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLd",	"Operadora Ld", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getDataAcordo",	"Data de Acordo", style, 12));
			columnDefinitions.add(new ExcelColumnDefinition("getQtdParcelas",	"Qtde. Parcelas", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getValorAcordado",	"Vlr. total do Acordo", style, 15));
			
		}else{
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD",	"Operadora Ld", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro",	"Operadora Claro", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getStatus",	"Status", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getNumConta",	"Status", dateStyle, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getNumAcordoParcelamento",	"Numero de Acordo", dateStyle, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getNumeroParcela",	"Quantidade de Parcelas", style, 10));
			columnDefinitions.add(new ExcelColumnDefinition("getVlParcelaOperadora",	"Valor Ooperadora Ld", style, 15));
			columnDefinitions.add(new ExcelColumnDefinition("getValorAcordado",	"Valor total Acordo", style, 15));
			
			
		}
		

		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Parcelamento Acompanhamento");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório Parcelamento Acompanhamento " + relatorio);
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(list);
		printer.writeData();
		

		


	}

}
