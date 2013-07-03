package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreItemDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.form.DemonstrativoRepassePrePagoForm;

public class RelatorioPreConsolidadoExcelHandler extends BasicExcelHandler{

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		DemonstrativoRepassePreDecorator decorator = (DemonstrativoRepassePreDecorator) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_CONSOLIDADO_GERAL, request);
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);
		if((decorator != null && decorator.getItens() != null) || cachedForm == null){
			return;
		}
		gerarPlanilha(cachedForm, workbook, request, decorator.getItens());
	}
		
		
	public void gerarPlanilha(DemonstrativoRepassePrePagoForm form, HSSFWorkbook workbook, HttpServletRequest request, List<DemonstrativoRepassePreItemDecorator> tabela) throws Exception {

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
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions, workbook);

		printer.addSheet("Consolidado");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		String prestadoraLd = "Todas";
		if (!form.getCdEOTLD().equals("*")) {
			prestadoraLd = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTLD()).getDsOperadora();
		}
		String produto = "PRODUTO PADRÃO";
		if (!form.getCdProdutoPrepago().equals("0")) {
			produto = getServiceManager(request).getProdutoPrepagoService().findByPk(form.getCdProdutoPrepago()).getNoProdutoPrepago();
		}
		
		linhasCabecalho.add("RELATÓRIO CONSOLIDADO APURAÇÃO PRÉ-PAGO");
		linhasCabecalho.add("PRESTADORA LD: " + prestadoraLd);
		
		linhasCabecalho.add("MÊS DE REFERÊNCIA: "+form.getMesDemonstrativo()+"/"+form.getAnoDemonstrativo());		
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: "+dateFormat.format(new Date()));
		linhasCabecalho.add("PRODUTO: "+produto);		
		

		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
	}
	
	public void gerarPlanilhaDemonstrativo(DemonstrativoRepassePrePagoForm form, ExcelPrinter printer, HttpServletRequest request, List<DemonstrativoRepassePreItemDecorator> tabela) throws Exception {

		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao", "DESCRICAO", style, 80));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoChamadas", "QTE CHAMADAS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoMinutos", "QTE MINUTOS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoValorBruto", "VLR BRUTO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoIcmsNaoRepassado", "ICMS NAO REPASSADO", style, 20));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoIcmsRepassar", "ICMS A REPASSAR", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoPisCofins", "PIS / COFINS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoIss", "ISS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoValorLiquido", "VLR LIQUIDO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCampoValorRepassar", "VLR REPASSAR", style, 15));
		printer.setNewDefinition(columnDefinitions, false);
				
		String prestadoraLd = "Todas";
		if (!form.getCdEOTLD().equals("*")) {
			prestadoraLd = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTLD()).getDsOperadora();
		}
		String produto = "PRODUTO PADRÃO";
		if (!form.getCdProdutoPrepago().equals("0")) {
			produto = getServiceManager(request).getProdutoPrepagoService().findByPk(form.getCdProdutoPrepago()).getNoProdutoPrepago();
		}		

		printer.addData(tabela);
		printer.generateColumnsTitleAtColumn(4,6);
		printer.writeDataAtColumn(4,7);
	}	

}
