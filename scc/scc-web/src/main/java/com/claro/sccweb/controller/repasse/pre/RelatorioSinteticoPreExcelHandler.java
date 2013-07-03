package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;
import com.claro.sccweb.excel.style.ExcelStyle;
import com.claro.sccweb.form.RelatoriosRepassePreForm;

public class RelatorioSinteticoPreExcelHandler extends BasicExcelHandler {

	private ExcelStyle myStyle = new ExcelStyle() {
		
		public String getFontName() {
			return "Arial";
		}
		 
		public short getFontColor() {
			return HSSFColor.BLACK.index;
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
	 
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<RelSinteticoFechamentoPrePagoView> tabela = (List<RelSinteticoFechamentoPrePagoView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		@SuppressWarnings("unchecked")
		List<RelSinteticoServicoPrePagoView> lstServicoPrestado = (List<RelSinteticoServicoPrePagoView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_2, request);
		RelSinteticoServicoPrePagoView servicoPrestado = null;
		if(lstServicoPrestado != null && lstServicoPrestado.size() > 0)
			servicoPrestado = lstServicoPrestado.get(0);
		if (tabela == null)
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		
		if(servicoPrestado == null)
			throw new ControllerExecutionException("Navegação inválida. ServicoPrestado é nulo!.");

		RelatoriosRepassePreForm form = (RelatoriosRepassePreForm)getFormFromCache(RelatoriosRepassePreController.class, request);
		if (form == null)
			throw new ControllerExecutionException("Navegação inválida. Form é nulo!.");
		
		gerarPlanilha(form, workbook, request, tabela, servicoPrestado);
	}

	public void gerarPlanilha(RelatoriosRepassePreForm form, HSSFWorkbook workbook, HttpServletRequest request, List<RelSinteticoFechamentoPrePagoView> tabela, RelSinteticoServicoPrePagoView servicoPrestado) throws Exception {
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getOperadoraClaroSemUF", "Op. Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getUf", "UF", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTrafego", "Tráfego", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnAssinante", "CN Ass.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCnOrigem", "CN Orig.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdEotOrigem", "EOT Orig.", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTipo", "Tipo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPeriodo", "Período", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getChamadas", "Chamadas", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getMinutosString", "Minutos", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorLiquidoString", "Vlr. Líquido", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getPisCofinsString", "Pis/Cofins", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsRepassarString", "ICMS Repassar", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorRepassarString", "Vlr. Repassar", currencyStyle, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getIcmsNaoRepassadoString", "ICMS Não Repassado", currencyStyle, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getValorBrutoString", "Vlr. Bruto", currencyStyle, 30));
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Relatório Sintético");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		String prestadoraLd = "Todas";
		if (!form.getCdEOTLD().equals("*")) {
			prestadoraLd = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTLD()).getDsOperadora();
		}
		
		String filial = "Todas";
		if (!form.getCdEOTClaro().equals("*")) {
			filial = getServiceManager(request).getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTClaro()).getDsOperadora();
		}
		
		String produto = "PRODUTO PADRÃO";
		if (!form.getCdProdutoPrepago().equals("0")) {
			produto = getServiceManager(request).getProdutoPrepagoService().findByPk(form.getCdProdutoPrepago()).getNoProdutoPrepago();
		}
		
		linhasCabecalho.add("RELATÓRIO SINTÉTICO DE CHAMADAS");
		linhasCabecalho.add("PRESTADORA LD: " + prestadoraLd);
		linhasCabecalho.add("FILIAL CLARO: "+filial);
		linhasCabecalho.add("MÊS DE REFERÊNCIA: "+form.getMesRelatorio()+"/"+form.getAnoRelatorio());		
		linhasCabecalho.add("DATA DO DEMONSTRATIVO: "+dateFormat.format(new Date()));
		linhasCabecalho.add("PRODUTO: "+produto);		
		
		tabela = calculaTotais(tabela, prestadoraLd);

		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(tabela);
		printer.writeData();
		printer.addBlankLines(1);
		printer.addLine(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", "Chamadas", " ", "Valor Liquido", "Pis e Cofins", "ISS", "Valor Bruto"), null, null);
		printer.addLine(Arrays.asList("Serviço Prestado (Utilização da Plataforma)", " ", " ", " ", " ", " ", " ", " ", servicoPrestado.getChamadasString(), " ", servicoPrestado.getValorLiquidoString(), servicoPrestado.getPisCofinsString(), servicoPrestado.getISSString(), servicoPrestado.getValorBrutoString()), Arrays.asList(new Short("0"),new Short("1")) , myStyle);
		//printer.adjustAlignForAllColumns();
	}
	
	
	private List<RelSinteticoFechamentoPrePagoView> calculaTotais(List<RelSinteticoFechamentoPrePagoView> tabela, String prestadoraLd) {
		List<RelSinteticoFechamentoPrePagoView> newTabela = new ArrayList<RelSinteticoFechamentoPrePagoView>();
		
		if(tabela != null && tabela.size() > 0) {
			RelSinteticoFechamentoPrePagoView linhaAnterior = tabela.get(0);
			RelSinteticoFechamentoPrePagoView totalEotUf = new RelSinteticoFechamentoPrePagoView();
			RelSinteticoFechamentoPrePagoView totalEot = new RelSinteticoFechamentoPrePagoView();
			RelSinteticoFechamentoPrePagoView totalGeral = new RelSinteticoFechamentoPrePagoView();
			
			totalEotUf.setOperadoraClaro("Subtotal");
			totalEotUf.setUf(prestadoraLd + " - " + linhaAnterior.getOperadoraClaroSemUF() + " - " + linhaAnterior.getUf());
			totalEotUf.clear();
			totalEotUf.summarize(linhaAnterior);
			
			totalEot.setOperadoraClaro("Subtotal");
			totalEot.setUf(linhaAnterior.getOperadoraClaroSemUF());
			totalEot.clear();
			totalEot.summarize(linhaAnterior);

			totalGeral.setOperadoraClaro("Total");
			totalGeral.clear();
			totalGeral.summarize(linhaAnterior);

			newTabela.add(linhaAnterior);
			
			for(int i=1; i < tabela.size(); ++i) {
				RelSinteticoFechamentoPrePagoView linha = tabela.get(i);
				String operadora = linha.getOperadoraClaroSemUF();
				String uf = linha.getUf();
				if(operadora.equals(linhaAnterior.getOperadoraClaroSemUF()) && !uf.equals(linhaAnterior.getUf())) {
					//Imprime o total op e uf
					newTabela.add(totalEotUf.copy());
					totalEotUf.setUf(prestadoraLd + " - " + linha.getOperadoraClaroSemUF() + " - " + linha.getUf());
					totalEotUf.clear();
				} else if(!operadora.equals(linhaAnterior.getOperadoraClaroSemUF()) && !uf.equals(linhaAnterior.getUf())) {
					//Imprime o total op e uf
					newTabela.add(totalEotUf.copy());
					totalEotUf.setUf(prestadoraLd + " - " + linha.getOperadoraClaroSemUF() + " - " + linha.getUf());
					totalEotUf.clear();
					//Imprime o total op
					newTabela.add(totalEot.copy());
					totalEot.setUf(linha.getOperadoraClaroSemUF());
					totalEot.clear();
				}
				totalEotUf.summarize(linha);
				totalEot.summarize(linha);
				totalGeral.summarize(linha);
				
				newTabela.add(linha);
				linhaAnterior = linha;
			}
			//Imprime o total op e uf
			newTabela.add(totalEotUf);
			newTabela.add(totalEot);			
			newTabela.add(totalGeral);
		}
		
		return newTabela;
	}
	
}
