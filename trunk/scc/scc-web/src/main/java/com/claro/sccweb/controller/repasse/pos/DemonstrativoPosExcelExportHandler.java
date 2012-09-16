package com.claro.sccweb.controller.repasse.pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.DemonstrativoRepassePosDecorator;
import com.claro.sccweb.decorator.TotaisRepasseDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
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
			totaisRepasseDecorator.setOperadoraClaro(listaHolding.get(h).getDsOperadora());
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
			printer.addSheet(operadoraClaro.getDsOperadora().replaceAll("/", " "));
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
		geraConsolidado(printer,model, workbook, request, response);
		geraTotaisRepasse(printer,totaisRepasse);
	}

	
	private void geraConsolidado(ExcelPrinter printer,Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response)
	throws Exception
	{		
		printer.addSheet("Consolidado");
		DemonstrativoRepassePosPagoForm form = (DemonstrativoRepassePosPagoForm)getFormFromCache(DemonstrativoRepassePosPagoController.class,request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. DemonstrativoRepassePosPagoForm não encontrado no cache.");
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
		List<DemonstrativoRepassePosDecorator> data = getServiceManager(request).getRepasseService().carregaDemonstrativoRepasse(null, form.getCdEOT(), form.getCdProdutoCobilling(), form.getDtInicialPeriodo(), form.getDtFinalPeriodo(), true);
		printer.addData(data);
		printer.generateColumnsTitle();
		printer.writeData();
	}
	
	private void geraTotaisRepasse(ExcelPrinter printer,List<TotaisRepasseDecorator> totais) throws Exception
	{
		printer.addSheet("Totais");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD", "Operadora LD", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaro", "Operadora Claro", style, 40));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepasse", "Total", currencyStyle, 40));
		printer.setNewDefinition(columnDefinitions, false);		
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("Valores Finais a Repassar");
		printer.generateHeader();
		printer.generateColumnsTitle();
		printer.addData(totais);
		printer.writeSum();
	}
	
}
