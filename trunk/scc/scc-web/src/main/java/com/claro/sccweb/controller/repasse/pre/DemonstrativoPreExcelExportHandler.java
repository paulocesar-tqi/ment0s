package com.claro.sccweb.controller.repasse.pre;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.CurrencyStyle;
import com.claro.sccweb.excel.style.DefaultStyle;
import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.excel.style.IntegerStyle;

public class DemonstrativoPreExcelExportHandler extends BasicExcelHandler {

SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	ExcelStyle style = new DefaultStyle();
	ExcelStyle currencyStyle = new CurrencyStyle();
	ExcelStyle integerStyle = new IntegerStyle();
	
	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) 
			throws Exception {		
		Map<SccOperadora, DemonstrativoRepassePreDecorator> excelModel = (Map<SccOperadora, DemonstrativoRepassePreDecorator>)getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_COMPLETO, request);
		if (excelModel == null)
			throw new ControllerExecutionException("Navegação inválida. Modelo de demonstrativo não encontrado na sessão");
		String dataDemonstrativo = dateFormat.format(new Date());
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao", "DESCRICAO", style, 80));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoChamadas", "QTE CHAMADAS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoMinutos", "QTE MINUTOS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoValorBruto", "VLR BRUTO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoIcmsNaoRepassado", "ICMS NAO REPASSADO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoIcmsRepassar", "ICMS A REPASSAR", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoPisCofins", "PIS / COFINS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoIss", "ISS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoValorLiquido", "VLR LIQUIDO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoValorRepassar", "VLR REPASSAR", style, 15));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		List<String> linhasCabecalho = new ArrayList<String>();		
		Iterator<SccOperadora> itr =  excelModel.keySet().iterator();
		while (itr.hasNext())
			{
			linhasCabecalho = new ArrayList<String>();
			SccOperadora holding = itr.next();
			printer.addSheet(holding.getDsOperadora().replaceAll("/", " "));
			DemonstrativoRepassePreDecorator demonstrativoHolding = excelModel.get(holding);
			linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PRÉ PAGO");
			linhasCabecalho.add("PRESTADORA LD: "+demonstrativoHolding.getOperadoraLD().getDsOperadora()+"("+demonstrativoHolding.getOperadoraLD().getCdEot()+")");
			linhasCabecalho.add("FILIAL CLARO: "+holding.getDsOperadora()+"(Holding)");
			linhasCabecalho.add("MÊS DE REFERÊNCIA: "+demonstrativoHolding.getPeriodo());
			linhasCabecalho.add("DATA DO DEMONSTRATIVO: "+dataDemonstrativo);			
			linhasCabecalho.add("PRODUTO: "+demonstrativoHolding.getProdutoPrepago().getNoProdutoPrepago());
			printer.setHeaderLines(linhasCabecalho);
			printer.generateHeader();
			printer.addBlankLines(1);
			printer.generateColumnsTitle();
			printer.addData(demonstrativoHolding.getItens());
			printer.writeData();		
			List<DemonstrativoRepassePreDecorator> demonstrativosOperadoras= demonstrativoHolding.getDemonstrativosOperadorasHolding();
			for (int o=0;o<demonstrativosOperadoras.size();o++)
				{				 
				DemonstrativoRepassePreDecorator demonstrativoOperadora = demonstrativosOperadoras.get(o);
				SccOperadora operadora = demonstrativoOperadora.getOperadora();
				printer.addBlankLines(1);
				linhasCabecalho = new ArrayList<String>();
				linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PRÉ PAGO");
				linhasCabecalho.add("PRESTADORA LD: "+demonstrativoOperadora.getOperadoraLD().getDsOperadora()+"("+demonstrativoOperadora.getOperadoraLD().getCdEot()+")");
				linhasCabecalho.add("FILIAL CLARO: "+operadora.getDsOperadora()+"("+operadora.getCdEot()+")");
				linhasCabecalho.add("MÊS DE REFERÊNCIA: "+demonstrativoOperadora.getPeriodo());
				linhasCabecalho.add("DATA DO DEMONSTRATIVO: "+dataDemonstrativo);			
				linhasCabecalho.add("PRODUTO: "+demonstrativoOperadora.getProdutoPrepago().getNoProdutoPrepago());
				printer.setHeaderLines(linhasCabecalho);
				printer.generateHeader();
				printer.addBlankLines(1);
				printer.generateColumnsTitle();
				printer.addData(demonstrativoOperadora.getItens());
				printer.writeData();		
				}
			}
		
	}

}
