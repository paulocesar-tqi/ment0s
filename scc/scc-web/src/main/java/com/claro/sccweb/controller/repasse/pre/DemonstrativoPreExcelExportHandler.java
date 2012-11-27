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

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.ConsolidadoProdutoPreView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.RelApuracaoFechamentoPrePagoViewDecorator;
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator;
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
		Map<SccOperadora, DemonstrativoRepassePreDecorator> excelModel = (Map<SccOperadora, DemonstrativoRepassePreDecorator>) getFromSession(
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

		Iterator<SccOperadora> itr = excelModel.keySet().iterator();
		while (itr.hasNext()) {
			SccOperadora holding = itr.next();
			DemonstrativoRepassePreDecorator demonstrativoHolding = excelModel.get(holding);

			gerarPlanilhaHolding(printer, holding, demonstrativoHolding, dataDemonstrativo);
		}

		gerarPlanilhaApuracao(request, workbook);
		gerarPlanilhaSintetico(request, workbook);
		gerarPlanilhaAssinatura(request, workbook);
		gerarPlanilhaConsolidadoProduto(request, workbook);
	}

	private void gerarPlanilhaApuracao(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelApuracaoFechamentoPrePagoViewDecorator> lstApuracao = (List<RelApuracaoFechamentoPrePagoViewDecorator>) getFromSession(DemonstrativoRepassePrePagoController.DEMONSTRATIVO_APURACAO,
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
		DemonstrativoRepassePrePagoForm cachedForm = (DemonstrativoRepassePrePagoForm) getFromSession(BaseOperationController.FORM_NAME, request);
		
		if (lstApuracao == null || cachedForm == null) return;

		RelatoriosRepassePreForm form = new RelatoriosRepassePreForm();
		
		form.setAnoRelatorio(cachedForm.getAnoDemonstrativo());
		form.setMesRelatorio(cachedForm.getMesDemonstrativo());
		form.setCdEOTClaro(BasicDAO.GET_ALL_STRING);
		form.setCdEOTLD(cachedForm.getCdEOTLD());
		form.setCdProdutoPrepago(cachedForm.getCdProdutoPrepago());
		form.setCdStatusFechamento(BasicDAO.GET_ALL_STRING);
		form.setCdTipoRelatorio(RelatoriosRepassePreController.TIPO_RELATORIOS_SINTETICO);
		
		RelatorioSinteticoPreExcelHandler excelHandler = new RelatorioSinteticoPreExcelHandler();
		excelHandler.gerarPlanilha(form, workbook, request, lstApuracao);
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

		printer.addSheet(holding.getDsOperadora().replaceAll("/", " "));

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

}
