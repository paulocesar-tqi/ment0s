package com.claro.sccweb.controller.questionario;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoFinanceiroExcelView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BasicExcelHandler;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.excel.ExcelColumnDefinition;
import com.claro.sccweb.excel.ExcelPrinter;


public class QuestionamentoFinanceiroExcelHandler extends BasicExcelHandler {
	
	private List<SccQuestionamentoFinanceiroExcelView> gerarListaParaExcel(List<SccQuestionamentoView> list){
		
		List<SccQuestionamentoFinanceiroExcelView> lstExcel = new ArrayList<SccQuestionamentoFinanceiroExcelView>();
		for (SccQuestionamentoView entidade : list) {
			SccQuestionamentoFinanceiroExcelView excel = new SccQuestionamentoFinanceiroExcelView();
			excel.setDescricaoEotLD(entidade.getDescricaoEotLD());
			excel.setCdEotClaro(entidade.getCdEotClaro());
			excel.setSgUf(entidade.getSgUf());
			excel.setSqQuestionamento(entidade.getSqQuestionamento().toString());
			excel.setNoArquivo(entidade.getNoArquivo());
			excel.setTotais("Qtde" + "\n" +"Min Reais" + "\n" +"Min Arred" + "\n" + "Vlr Liquido");
			excel.setCdrQuestionados(getCdrQuestionados(entidade));
			excel.setCdrConfirmadosSemAnalise(getCdrConfirmadosSemAnalise(entidade));
			excel.setCdrConfirmadosComAnalise(getCdrConfirmadosComAnalise(entidade));
			excel.setCdrConfirmadosComAnaliseSemProcedencia(getCdrConfirmadosComAnaliseSemProcedencia(entidade));
			excel.setCdrPenalidadeClaro(getCdrPenalidadeClaro(entidade));
			excel.setCdrPenalidadeLd(getCdrPenalidadeLd(entidade));
			lstExcel.add(excel);
			
		}
		return lstExcel;
		
		
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<SccQuestionamentoView> lstSccQuestionamento = (List<SccQuestionamentoView>)getFromSession(BaseFormController.DISPLAY_TAG_SPACE_1, request);
		List<SccQuestionamentoFinanceiroExcelView> lstSccQuestionamentoExcel = gerarListaParaExcel(lstSccQuestionamento);
		
		if (lstSccQuestionamentoExcel == null){
			throw new ControllerExecutionException("Navegação inválida. Tabela é nula!.");
		}
		
		List<ExcelColumnDefinition> columnDefinitions = new ArrayList<ExcelColumnDefinition>();
		columnDefinitions.add(new ExcelColumnDefinition("getDescricaoEotLD",	"EOT LD", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdEotClaro",	"EOT Claro.", style, 15));
		columnDefinitions.add(new ExcelColumnDefinition("getSgUf",	"UF", style, 5));
		columnDefinitions.add(new ExcelColumnDefinition("getSqQuestionamento",	"Nr(Q)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getNoArquivo",	"Arquivo", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getTotais",	"Totais", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdrQuestionados",	"Cdr Questionados", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdrConfirmadosSemAnalise",	"CDR Confiramdos Sem Análise", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdrConfirmadosComAnalise",	"CDR Confirmados Com Análise (P)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdrConfirmadosComAnaliseSemProcedencia",	"CDR Confirmados com Análise(NP)", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdrPenalidadeClaro",	"Penalidade Claro", style, 30));
		columnDefinitions.add(new ExcelColumnDefinition("getCdrPenalidadeLd",	"Penalidade LD.", style, 30));
		
		ExcelPrinter printer = new ExcelPrinter(columnDefinitions,workbook);
		printer.addSheet("Questionamento Financeiro");
		List<String> linhasCabecalho = new ArrayList<String>();
		
		linhasCabecalho.add("Claro - Relatório de Questionamento Financeiro");
		linhasCabecalho.add("Data Geração "+dateFormat.format(new Date()));
		printer.setHeaderLines(linhasCabecalho);
		printer.generateHeader();
		printer.addBlankLines(1);
		printer.generateColumnsTitle();
		printer.addData(lstSccQuestionamentoExcel);
		printer.writeData();
		

		

	}

	
	private String getCdrQuestionados(SccQuestionamentoView entidade){
		String qtd = System.getProperty("line.separator");
		qtd = entidade.getQtdCdrQuest().toString();
		String minReais = formataDouble(zeroIfNull(entidade.getMiReaisQuest()));
		String minArred = formataDouble(zeroIfNull(entidade.getMiArredondadosQuest()));
		String vlrLiquido = formataDouble(zeroIfNull(entidade.getVlrLiquidoQuest()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;
	}
	
	private String getCdrConfirmadosSemAnalise(SccQuestionamentoView entidade){
		String qtd = entidade.getQtdCdrCsa().toString();
		String minReais = formataDouble(zeroIfNull(entidade.getMiReaisCsa()));
		String minArred = formataDouble(zeroIfNull(entidade.getMiArredondadosCsa()));
		String vlrLiquido = formataDouble(zeroIfNull(entidade.getVlrLiquidoCsa()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;
		
	}
	
	private String getCdrConfirmadosComAnalise(SccQuestionamentoView entidade){
		String qtd = entidade.getQtdCdrCcap().toString();
		String minReais = formataDouble(zeroIfNull(entidade.getMiReaisCcap()));
		String minArred = formataDouble(zeroIfNull(entidade.getMiArredondadosCcap()));
		String vlrLiquido = formataDouble(zeroIfNull(entidade.getVlrLiquidoCcap()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;
		
	}
	
	private String getCdrConfirmadosComAnaliseSemProcedencia(SccQuestionamentoView entidade){

		String qtd = entidade.getQtdCdrCanp().toString();
		String minReais = formataDouble(zeroIfNull(entidade.getMiReaisCanp()));
		String minArred = formataDouble(zeroIfNull(entidade.getMiArredondadosCanp()));
		String vlrLiquido = formataDouble(zeroIfNull(entidade.getVlrLiquidoCanp()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;

	}
	
	private String getCdrPenalidadeClaro(SccQuestionamentoView entidade){

		String qtd = entidade.getQtdCdrClaro().toString();
		String minReais = formataDouble(zeroIfNull(entidade.getMiReaisClaro()));
		String minArred = formataDouble(zeroIfNull(entidade.getMiArredondadosClaro()));
		String vlrLiquido = formataDouble(zeroIfNull(entidade.getVlrLiquidoClaro()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;
		
	}
	
	private String getCdrPenalidadeLd(SccQuestionamentoView entidade){

		String qtd = entidade.getQtdCdrLd().toString();
		String minReais = formataDouble(zeroIfNull(entidade.getMiReaisLd()));
		String minArred = formataDouble(zeroIfNull(entidade.getMiArredondadosLd()));
		String vlrLiquido = formataDouble(zeroIfNull(entidade.getVlrLiquidoLd()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;
		
	}
	


	
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected SimpleDateFormat datePeriodoFormat = new SimpleDateFormat("MM/yyyy");
	protected SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	protected static NumberFormat decimalFormat34 = new DecimalFormat("###.####");
	
	static {
    	Locale locale = new Locale("pt","BR");
    	decimalFormat = NumberFormat.getInstance(locale);
    	decimalFormat.setMinimumFractionDigits(2);
    	decimalFormat.setMaximumFractionDigits(2);
    }
	
	protected static NumberFormat sccCurrencyFormat = new DecimalFormat("#.##");
	protected static NumberFormat integerFormat = new DecimalFormat("#.##");
	static {
    	Locale locale = new Locale("pt","BR");
    	sccCurrencyFormat = NumberFormat.getInstance(locale);
    	sccCurrencyFormat.setMinimumFractionDigits(2);
    	sccCurrencyFormat.setMaximumFractionDigits(2);
    	integerFormat = NumberFormat.getInstance(locale);
    	integerFormat.setMinimumFractionDigits(0);
    	integerFormat.setMaximumFractionDigits(0);
    }
	
	protected Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;
	}
	
	protected String formataDouble(Double valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat.format(trunc(valor,2));
	}
	
	protected String formataDouble34(Double valor) {
		if (valor == null) {
			return " ";
		}
		return decimalFormat34.format(trunc(valor, 4));
	}

	protected double trunc(Double db, int casas) {
		if (db == null) {
			return trunc(0.0000,2);
		}
    	double fator = 1.0;
    	do {
    		fator = fator*10.0;
    		casas--;
    	}
    	while(casas>0);
    	return Math.round(db*fator)/fator;
    }


}
