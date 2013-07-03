package com.claro.sccweb.controller.repasse.pre;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.ConsolidadoProdutoPreView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelatorioApuracaoPreSumarizado;
import com.claro.cobillingweb.persistence.view.SccOperadoraComparator;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreItemDecorator;
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator;
import com.claro.sccweb.decorator.TotaisRepasseDecorator;
import com.claro.sccweb.decorator.rownum.view.ConsolidadoProdutoPreViewDecorator;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.CurrencyStyle;
import com.claro.sccweb.excel.style.DefaultStyle;
import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.excel.style.IntegerStyle;
import com.claro.sccweb.form.ConsolidadoProdutoPreForm;
import com.claro.sccweb.form.DemonstrativoRepassePrePagoForm;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

public class DemonstrativoPreExcelExportHandler extends BasicExcelHandler {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	ExcelStyle style = new DefaultStyle();
	ExcelStyle currencyStyle = new CurrencyStyle();
	ExcelStyle integerStyle = new IntegerStyle();

	
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<SccOperadora, DemonstrativoRepassePreDecorator> excelModel = new TreeMap<SccOperadora, DemonstrativoRepassePreDecorator>();
		excelModel = (Map<SccOperadora, DemonstrativoRepassePreDecorator>) getFromSession(
				DemonstrativoRepassePrePagoController.DEMONSTRATIVO_COMPLETO, request);
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
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions, workbook);

		List<DemonstrativoRepassePreDecorator> lstTotal = new ArrayList<DemonstrativoRepassePreDecorator>();
		
		TreeSet<SccOperadora> list = new TreeSet<SccOperadora>(new SccOperadoraComparator());
		for(SccOperadora operadora : excelModel.keySet()){
			list.add(operadora);
		}
		
		for (SccOperadora holding : list) {
			DemonstrativoRepassePreDecorator demonstrativoHolding = excelModel.get(holding);
			
			// para realizar o relatorio de Totais
			lstTotal.add(demonstrativoHolding);
			gerarPlanilhaHolding(printer, holding, demonstrativoHolding, dataDemonstrativo);
			
		}
		
		gerarPlanilhaApuracao(request, workbook);
		gerarPlanilhaSintetico(request, workbook);
		gerarPlanilhaAssinatura(request, workbook);
		gerarPlanilhaConsolidadoProduto(request, workbook);
		gerarTotais(lstTotal, printer, request);
		gerarPlanilhaConsolidadoGeral(request, printer);
		printer.getCurrentSheet().setColumnWidth((short) 3,(short) 1000);
	}
	
	private Double sumarizarItens(List<DemonstrativoRepassePreItemDecorator> lst){
		
		double vlrRepassar = 0;
		for (DemonstrativoRepassePreItemDecorator demonstrativoRepassePreItemDecorator : lst) {
			
			if(demonstrativoRepassePreItemDecorator.getDescricao().equalsIgnoreCase("8-VALOR FINAL A REPASSAR")){
			
				vlrRepassar = vlrRepassar + (demonstrativoRepassePreItemDecorator.getValorRepassar() == null ? 0 : demonstrativoRepassePreItemDecorator.getValorRepassar().doubleValue());
			}
		}
		return vlrRepassar;
		
	}
	
	private TotaisRepasseDecorator gerarTotal(DemonstrativoRepassePreDecorator demonstrativoHolding){
		
		TotaisRepasseDecorator total = new TotaisRepasseDecorator();
		total.setOperadoraLD(demonstrativoHolding.getOperadoraLD().getDsOperadora());
		total.setOperadoraClaro(demonstrativoHolding.getOperadora().getOperadoraClaroSemUF().replaceAll("/", " ")  + "(" + demonstrativoHolding.getOperadora().getCdOperadoraHolding() +")");
		total.setValorRepasse(sumarizarItens(demonstrativoHolding.getItens()));
		return total;
		
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
	
	private void gerarTotais(List<DemonstrativoRepassePreDecorator> lstTotal, ExcelPrinter printer, HttpServletRequest request) throws Exception {
		
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);
		List<TotaisRepasseDecorator> lstSumarizado = new ArrayList<TotaisRepasseDecorator>();

		String operadoraLd = "";
		String produto = "";
		
		operadoraLd = lstTotal.get(0).getOperadoraLD().getDsOperadora();
		produto = lstTotal.get(0).getProdutoPrepago().getNoProdutoPrepago();
		
		for (DemonstrativoRepassePreDecorator demonstrativoRepassePreDecorator : lstTotal) {
			
			TotaisRepasseDecorator total = gerarTotal(demonstrativoRepassePreDecorator);
			lstSumarizado.add(total);
			
		}
		
		List<String> linhasCabecalho = new ArrayList<String>();
		linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PRÉ PAGO");
		linhasCabecalho.add("PRESTADORA LD: " + operadoraLd);
		linhasCabecalho.add("FILIAL CLARO: Todas");
		linhasCabecalho.add("MÊS DE REFERÊNCIA: " + cachedForm.getMesDemonstrativo() + cachedForm.getAnoDemonstrativo());
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: " + new Date().toString());
		incluirTotal(lstSumarizado);
		gerarTotaisRepasse(printer,lstSumarizado, linhasCabecalho);
		
	}
	
	private void gerarTotaisRepasse(ExcelPrinter printer,List<TotaisRepasseDecorator> totais, List<String> linhasCabecalho) throws Exception{
		printer.addSheet("Consolidado");
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraLD", "Operadora LD", style, 33));
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaroSemUF", "Operadora Claro", style, 15));
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

		printer.generateHeader();
		printer.addBlankLines(1);
		
		printer.addLine(linha, mergeRanges, myStyle);
		printer.generateColumnsTitle();
		printer.addData(totais);
		printer.writeSum();
	}


	private void gerarPlanilhaApuracao(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelatorioApuracaoPreSumarizado> lstApuracao = (List<RelatorioApuracaoPreSumarizado>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_APURACAO,
				request);
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);
		
		if (lstApuracao == null || cachedForm == null) return;

		RelatoriosRepassePreForm form = new RelatoriosRepassePreForm();
		
		form.setAnoRelatorio(cachedForm.getAnoDemonstrativo());
		form.setMesRelatorio(cachedForm.getMesDemonstrativo());
		form.setCdEOTClaro(BasicDAO.GET_ALL_STRING);
		form.setCdEOTLD(cachedForm.getCdEOTLD());
		form.setCdProdutoPrepago(cachedForm.getCdProdutoPrepago());
		form.setCdStatusFechamento(BasicDAO.GET_ALL_STRING);
		form.setCdTipoRelatorio(RelatoriosRepassePreController.TIPO_RELATORIOS_APURACAO);
		
		RelatorioApuracaoPreExcelHandler excelHandler = new RelatorioApuracaoPreExcelHandler();
		excelHandler.gerarPlanilha(form, workbook, request, lstApuracao);
	}
	
	
	private void gerarPlanilhaSintetico(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelSinteticoFechamentoPrePagoView> lstApuracao = (List<RelSinteticoFechamentoPrePagoView>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_SINTETICO,
				request);
		@SuppressWarnings("unchecked")
		List<RelSinteticoServicoPrePagoView> lstServicoPrestado = (List<RelSinteticoServicoPrePagoView>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_SINTETICO_SERVICO_PRESTADO,
				request);
		RelSinteticoServicoPrePagoView servicoPrestado = null;
		if(lstServicoPrestado != null && lstServicoPrestado.size() > 0)
			servicoPrestado = lstServicoPrestado.get(0);

		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);
		
		if (lstApuracao == null || servicoPrestado == null || cachedForm == null) return;

		RelatoriosRepassePreForm form = new RelatoriosRepassePreForm();
		
		form.setAnoRelatorio(cachedForm.getAnoDemonstrativo());
		form.setMesRelatorio(cachedForm.getMesDemonstrativo());
		form.setCdEOTClaro(BasicDAO.GET_ALL_STRING);
		form.setCdEOTLD(cachedForm.getCdEOTLD());
		form.setCdProdutoPrepago(cachedForm.getCdProdutoPrepago());
		form.setCdStatusFechamento(BasicDAO.GET_ALL_STRING);
		form.setCdTipoRelatorio(RelatoriosRepassePreController.TIPO_RELATORIOS_SINTETICO);
		
		RelatorioSinteticoPreExcelHandler excelHandler = new RelatorioSinteticoPreExcelHandler();
		excelHandler.gerarPlanilha(form, workbook, request, lstApuracao, servicoPrestado);
	}
	
	private void gerarPlanilhaAssinatura(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
		@SuppressWarnings("unchecked")
		List<SccPreFechamentoAssinaturaDecorator> lst = (List<SccPreFechamentoAssinaturaDecorator>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_ASSINATURA, request);

		if (lst == null) return;
		
		DemonstrativoAssinaturaPreExcelExportHandler excelHander = new DemonstrativoAssinaturaPreExcelExportHandler();
		excelHander.gerarPlanilha(workbook, request, lst);
	}

	private void gerarPlanilhaConsolidadoProduto(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
		@SuppressWarnings("unchecked")
		List<ConsolidadoProdutoPreView> rows = (List<ConsolidadoProdutoPreView>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_CONS_PRODUTO, request);

		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);
		
		if (rows == null || cachedForm == null) return;
		
		ConsolidadoProdutoPreForm form = new ConsolidadoProdutoPreForm();
		form.setCdEOTClaro(BasicDAO.GET_ALL_STRING);
		form.setCdEOTLD(cachedForm.getCdEOTLD());
		form.setCdProdutoPrepago(cachedForm.getCdProdutoPrepago());
		form.setDataInicial(cachedForm.getDtInicial());
		form.setDataFinal(cachedForm.getDtFinal());
		
		List<ConsolidadoProdutoPreViewDecorator> decoratorList = new ArrayList<ConsolidadoProdutoPreViewDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			ConsolidadoProdutoPreViewDecorator decorator = new ConsolidadoProdutoPreViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		ConsolidadoProdutoPreExcelHandler excelHandler = new ConsolidadoProdutoPreExcelHandler();
		excelHandler.gerarPlanilha(form, workbook, request, decoratorList);
	}
	
	private void gerarPlanilhaHolding(ExcelPrinter printer, SccOperadora holding, DemonstrativoRepassePreDecorator demonstrativoHolding, String dataDemonstrativo) throws Exception {
		List<String> linhasCabecalho = new ArrayList<String>();

		printer.addSheet(holding.getOperadoraClaroSemUF().replaceAll("/", " ") + "(" + holding.getCdOperadoraHolding() + ")");
		
		linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PRÉ PAGO");
		linhasCabecalho.add("PRESTADORA LD: " + demonstrativoHolding.getOperadoraLD().getDsOperadora() + "(" + demonstrativoHolding.getOperadoraLD().getCdEot() + ")");
		linhasCabecalho.add("FILIAL CLARO: " + holding.getDsOperadora() + "(Holding)");
		linhasCabecalho.add("MÊS DE REFERÊNCIA: " + demonstrativoHolding.getPeriodo());
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: " + dataDemonstrativo);
		linhasCabecalho.add("PRODUTO: " + demonstrativoHolding.getProdutoPrepago().getNoProdutoPrepago());
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(demonstrativoHolding.getItens());
		printer.writeData();
		List<DemonstrativoRepassePreDecorator> demonstrativosOperadoras = demonstrativoHolding.getDemonstrativosOperadorasHolding();
		for (int o = 0; o < demonstrativosOperadoras.size(); o++) {
			DemonstrativoRepassePreDecorator demonstrativoOperadora = demonstrativosOperadoras.get(o);
			SccOperadora operadora = demonstrativoOperadora.getOperadora();
			printer.addBlankLines(1);
			linhasCabecalho = new ArrayList<String>();
			linhasCabecalho.add("DEMONSTRATIVO DE REPASSE PRÉ PAGO");
			linhasCabecalho.add("PRESTADORA LD: " + demonstrativoOperadora.getOperadoraLD().getDsOperadora() + "(" + demonstrativoOperadora.getOperadoraLD().getCdEot() + ")");
			linhasCabecalho.add("FILIAL CLARO: " + operadora.getDsOperadora() + "(" + operadora.getCdEot() + ")");
			linhasCabecalho.add("MÊS DE REFERÊNCIA: " + demonstrativoOperadora.getPeriodo());
			linhasCabecalho.add("DATA DO DEMONSTRATIVO: " + dataDemonstrativo);
			linhasCabecalho.add("PRODUTO: " + demonstrativoOperadora.getProdutoPrepago().getNoProdutoPrepago());
			printer.setHeaderLines(linhasCabecalho);
			printer.generateHeader();
			printer.addBlankLines(1);
			printer.generateColumnsTitle();
			printer.addData(demonstrativoOperadora.getItens());
			printer.writeData();
		}
	}
	
	@SuppressWarnings("null")
	private void gerarPlanilhaConsolidadoGeral(HttpServletRequest request, ExcelPrinter printer) throws Exception{

		DemonstrativoRepassePreDecorator decorator = (DemonstrativoRepassePreDecorator) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_CONSOLIDADO_GERAL, request);
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);

		if((decorator == null && decorator.getItens() == null) || cachedForm == null){
			return;
		}
		DemonstrativoRepassePrePagoForm form = new DemonstrativoRepassePrePagoForm();
		form.setAnoDemonstrativo(cachedForm.getAnoDemonstrativo());
		form.setMesDemonstrativo(cachedForm.getMesDemonstrativo());
		
		form.setCdEOTLD(cachedForm.getCdEOTLD());
		form.setCdProdutoPrepago(cachedForm.getCdProdutoPrepago());
		
		RelatorioPreConsolidadoExcelHandler excelHandler = new RelatorioPreConsolidadoExcelHandler();
		excelHandler.gerarPlanilhaDemonstrativo(form, printer, request, decorator.getItens());
	}


}
