package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.DemonstrativoRepassePosDecorator;
import com.claro.sccweb.decorator.TotaisRepasseDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.form.DemonstrativoRepassePosPagoForm;

/**
 * Gera arquivo Excel com demonstrativo de repasse pós pago de acordo com o filtro aplicado na
 * tela de Demonstrativo.
 *
 */
@Controller
public class DemonstrativoPosExcelExportHandler extends BasicExcelHandler {

	
	
	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<TotaisRepasseDecorator> totaisRepasse = new ArrayList<TotaisRepasseDecorator>();
		
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)getFormFromCache(DemonstrativoRepassePosPagoController.class,request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. DemonstrativoRepassePosPagoForm não encontrado no cache.");
		List<SccOperadora> listaHolding = getServiceManager(request).getPesquisaDominiosService().pesquisaHoldingClaro();
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao", "DESCRIÇÃO", style, 80));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadas", "CHAMADAS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutos", "MINUTOS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getLiquido", "LÍQUIDO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getPis", "PIS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCofins", "COFINS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIcms", "ICMS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIss", "ISS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getBruto", "VALOR BRUTO", style, 15));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		List<String> linhasCabecalho = new ArrayList<String>();
		SccProdutoCobilling produtoCobilling = getServiceManager(request).getContratoService().pesquisaProdutoByPk(form.getCdProdutoCobilling());
		SccOperadora operadoraLD = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOT());
		SccOperadora operadoraClaro;
		
		for (int h=0;h<listaHolding.size();h++)
			{			
			TotaisRepasseDecorator totaisRepasseDecorator = new TotaisRepasseDecorator();
			totaisRepasseDecorator.setOperadoraClaro(listaHolding.get(h).getOperadoraClaroSemUF().replaceAll("/", " ")  + "(" + listaHolding.get(h).getCdOperadoraHolding() + ")");
			totaisRepasseDecorator.setOperadoraLD(operadoraLD.getDsOperadora());			
			List<DemonstrativoRepassePosDecorator> data = getServiceManager(request).getRepasseService().carregaDemonstrativoRepasse(listaHolding.get(h).getCdEot(), form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo(), form.getDtFinalPeriodo(), true);
			for (int d=0;d<data.size();d++)
				{
				DemonstrativoRepassePosDecorator toCheck = data.get(d);
				if (toCheck.isValorBrutoRepasse())
					{
					totaisRepasseDecorator.setValorRepasse(toCheck.getBrutoAsDouble());
					}
				}
			totaisRepasse.add(totaisRepasseDecorator);
			operadoraClaro = listaHolding.get(h);
			printer.addSheet(operadoraClaro.getOperadoraClaroSemUF().replaceAll("/", " ") + "(" + operadoraClaro.getCdOperadoraHolding() + ")");
			linhasCabecalho = new ArrayList<String>();			 
			linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PÓS PAGO");
			linhasCabecalho.add("PRESTADORA LD: "+operadoraLD.getDsOperadora());
			linhasCabecalho.add("FILIAL CLARO: "+operadoraClaro.getDsOperadora()+"(Holding)");
			linhasCabecalho.add("PRODUTO: "+produtoCobilling.getNoProdutoCobilling());
			linhasCabecalho.add("PERÍODO DO REPASSE:"+dateFormat.format(form.getDtInicialPeriodo())+" a "+dateFormat.format(form.getDtFinalPeriodo()));
			printer.setHeaderLines(linhasCabecalho);
			printer.generateHeader();
			printer.addBlankLines(1);
			printer.generateColumnsTitle();
			printer.addData(data);
			printer.writeData();			
			List<SccOperadora> operadorasHolding = getServiceManager(request).getOperadoraService().pesquisaOperarodasHolding(listaHolding.get(h).getCdEot());
			if (operadorasHolding != null)
				{
				for (int o=0;o<operadorasHolding.size();o++)
					{			
					operadoraClaro = operadorasHolding.get(o);
					printer.addBlankLines(1);
					linhasCabecalho = new ArrayList<String>();			 
					linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PÓS PAGO");
					linhasCabecalho.add("PRESTADORA LD: "+operadoraLD.getDsOperadora());
					linhasCabecalho.add("FILIAL CLARO: "+operadoraClaro.getDsOperadora()+" - "+operadoraClaro.getSgUf()+" ("+operadoraClaro.getCdEot()+")");
					linhasCabecalho.add("PRODUTO: "+produtoCobilling.getNoProdutoCobilling());
					linhasCabecalho.add("PERÍODO DO REPASSE:"+dateFormat.format(form.getDtInicialPeriodo())+" a "+dateFormat.format(form.getDtFinalPeriodo()));
					printer.setHeaderLines(linhasCabecalho);
					printer.addBlankLines(1);
					printer.generateHeader();
					data = getServiceManager(request).getRepasseService().carregaDemonstrativoRepasse(operadoraClaro.getCdEot(), form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo(), form.getDtFinalPeriodo(), false);
					printer.addData(data);
					printer.generateColumnsTitle();
					printer.writeData();
					}
				}
			}
		geraTotaisRepasse(printer,totaisRepasse, model, workbook, request, response);
		geraConsolidado(printer,model, workbook, request, response);
		//printer.adjustAlignForAllColumns();
		printer.getCurrentSheet().setColumnWidth((short) 3,(short) 1000);
	}

	
	private void geraConsolidado(ExcelPrinter printer,Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response)
	throws Exception
	{
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDescricao", "DESCRIÇÃO", style, 80));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadas", "CHAMADAS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutos", "MINUTOS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getLiquido", "LÍQUIDO", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getPis", "PIS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getCofins", "COFINS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIcms", "ICMS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getIss", "ISS", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getBruto", "VALOR BRUTO", style, 15));
		printer.setNewDefinition(columnDefinitions, false);
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)getFormFromCache(DemonstrativoRepassePosPagoController.class,request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. DemonstrativoRepassePosPagoForm não encontrado no cache.");
		List<DemonstrativoRepassePosDecorator> data = getServiceManager(request).getRepasseService().carregarDemonstrativoRepasseConsolidado(null, form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo(), form.getDtFinalPeriodo(), true);
		printer.addData(data);
		printer.generateColumnsTitleAtColumn(4,6);
		printer.writeDataAtColumn(4,7);
	}
	
	private void incluirTotal(List<TotaisRepasseDecorator> list){
		
		double total = 0;
		String operadoraLd = "";
		for (TotaisRepasseDecorator totaisRepasseDecorator : list) {
			operadoraLd = totaisRepasseDecorator.getOperadoraLD();
			total = total + totaisRepasseDecorator.getValorRepasse();
		}
		TotaisRepasseDecorator totalGeral = new TotaisRepasseDecorator();
		totalGeral.setOperadoraLD(operadoraLd);
		totalGeral.setOperadoraClaro("Total");
		totalGeral.setValorRepasse(total);
		list.add(totalGeral);
	}

	
	private void geraTotaisRepasse(ExcelPrinter printer,List<TotaisRepasseDecorator> totais, Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		incluirTotal(totais);
		printer.addSheet("Consolidado");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD", "Operadora LD", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepasseTotal", "Total", style, 15));
		printer.setNewDefinition(columnDefinitions, false);
		List<Short> mergeRanges = new ArrayList<Short>();
		mergeRanges.add((short) 0);
		mergeRanges.add((short) 2);
		ExcelStyle myStyle = new ExcelStyle() {
			
			public String getFontName() {
				return "Arial";
			}
			 
			public short getFontColor() {
				return HSSFColor.BLUE.index;
			}

			public short getFontHeight() {
				return 8;
			}

			public short getBoldweight() {
				return HSSFFont.BOLDWEIGHT_BOLD;
			}

			public short getAlignment() {
				return HSSFCellStyle.ALIGN_CENTER;
			}

			public boolean getWrapText() {
				return true;
			}

			public String getFormat() {		
				return null;
			}

		};
		List<String> linha = new ArrayList<String>();
		linha.add("Valores Finais a Repassar");
		linha.add("");
		linha.add("");
		
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)getFormFromCache(DemonstrativoRepassePosPagoController.class,request);
		SccProdutoCobilling produtoCobilling = getServiceManager(request).getContratoService().pesquisaProdutoByPk(form.getCdProdutoCobilling());
		SccOperadora operadoraLD = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOT());
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PÓS PAGO");
		linhasCabecalho.add("PRESTADORA LD: "+operadoraLD.getDsOperadora());
		linhasCabecalho.add("FILIAL CLARO: Todas");
		linhasCabecalho.add("PRODUTO: "+produtoCobilling.getNoProdutoCobilling());
		linhasCabecalho.add("PERÍODO DO REPASSE:"+dateFormat.format(form.getDtInicialPeriodo())+" a "+dateFormat.format(form.getDtFinalPeriodo()));		
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);

		printer.addLine(linha, mergeRanges, myStyle);
		printer.generateColumnsTitle();
		printer.addData(totais);
		printer.writeSum();
	}
}
