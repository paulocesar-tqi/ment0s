package com.claro.sccweb.controller.repasse.pre;

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
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.CurrencyStyle;
import com.claro.sccweb.excel.style.DefaultStyle;
import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.excel.style.IntegerStyle;

public class DemonstrativoAssinaturaPreExcelExportHandler extends BasicExcelHandler {

SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	ExcelStyle style = new DefaultStyle();
	ExcelStyle currencyStyle = new CurrencyStyle();
	ExcelStyle integerStyle = new IntegerStyle();
	
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		@SuppressWarnings("unchecked")
		List<SccPreFechamentoAssinaturaDecorator> decoratorList = (List<SccPreFechamentoAssinaturaDecorator>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_ASSINATURAS, request);
		
		if (decoratorList == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaroDs", "Operadora Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getQtAssinaturas", "QTD Pacotes/Assinaturas", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getQtChamadas", "QTD Chamadas", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getQtMinutos", "QTE MINUTOS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosQueimados", "Minutos Queimados", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosExpirados", "Minutos Expirados", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBruto", "Valor Bruto", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquido", "Valor Liquido", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofins", "PIS e COFINS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassar", "ICMS a Repassar", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsNaoRepassado", "ICMS Nao Repassado", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassar", "Valor a Repassar", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCusto", "Custo", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsDescontarMesAnt", "ICMS A DESCONTAR (Repasse Próximo mês)", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassarMesAnt", "ICMS A REPASSAR (Descontado Mês anterior)", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getRepasse", "Repasse", style, 15));
	
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Demonstrativo de Assinaturas");
		List<String> linhasCabecalho = new ArrayList<String>();
		//SccPreFechamentoAssinaturaDecorator demonstrativoAssinatura = (SccPreFechamentoAssinaturaDecorator) decoratorList;
		linhasCabecalho.add("DEMONSTRATIVO DE ASSINATURAS PRÉ PAGO");
		//linhasCabecalho.add("PRESTADORA LD: "+demonstrativoAssinatura.getOperadoraLD().getDsOperadora()+"("+demonstrativoAssinatura.getOperadoraLD().getCdEot()+")");
		//linhasCabecalho.add("FILIAL CLARO: "+demonstrativoAssinatura.getOperadoraClaroDs()+"("+demonstrativoAssinatura.getOperadoraClaro().getCdEot()+")");
		//linhasCabecalho.add("MÊS DE REFERÊNCIA: "+demonstrativoAssinatura.getPeriodo());
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: "+dateFormat.format(new Date()));			
		//linhasCabecalho.add("PRODUTO: "+demonstrativoAssinatura.getProdutoPrepago().getNoProdutoPrepago());
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(decoratorList);
		printer.writeData();

	}


}
