/**
 * 
 */
package com.claro.sccweb.decorator.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;

/**
 * @author vagner.souza
 *
 */
public class SccQuestionamentoFinanceiroDecorator extends TableDecorator {
	
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
	
	
	public String getDescricaoEot(){
		
		SccQuestionamentoView getSccQuestionamentoView = (SccQuestionamentoView) getCurrentRowObject();
		
		String value = "";
		if(StringUtils.isNotEmpty(getSccQuestionamentoView.getDescricaoEotLD())){
			value = getSccQuestionamentoView.getDescricaoEotLD();
		}
		
		return value;
	}
	
	public String getCdEotClaro(){
		SccQuestionamentoView getSccQuestionamentoView = (SccQuestionamentoView) getCurrentRowObject();
		String value ="";
		if(StringUtils.isNotEmpty(getSccQuestionamentoView.getCdEotClaro())){
			value = getSccQuestionamentoView.getCdEotClaro();
		}
		return value;
	}
	
	public String getSgUf(){
		
		SccQuestionamentoView getSccQuestionamentoView = (SccQuestionamentoView) getCurrentRowObject();
		String value = "";
		if(StringUtils.isNotEmpty(getSccQuestionamentoView.getSgUf())){
			value = getSccQuestionamentoView().getSgUf();
		}
		return value;
	}
	
	public String getSqQuestionamento(){
		String value = "";
		if(getSccQuestionamentoView().getSqQuestionamento() != null){
			value = getSccQuestionamentoView().getSqQuestionamento().toString();
		}
		
		return value;
	}
	
	public String getNoArquivo(){
		String value = "";
		if(StringUtils.isNotEmpty(getSccQuestionamentoView().getNoArquivo())){
			value = getSccQuestionamentoView().getNoArquivo();
		}
		
		return value;
	}
	
	public String getTotais(){
		return "Qtde <br>Min Reais<br>Min Arred<br>Vlr Liquido";
	}
	
	public String getCdrQuestionados(){
		String qtd = System.getProperty("line.separator");
		qtd = getSccQuestionamentoView().getQtdCdrQuest().toString();
		String minReais = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiReaisQuest()));
		String minArred = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiArredondadosQuest()));
		String vlrLiquido = formataDouble(zeroIfNull(getSccQuestionamentoView().getVlrLiquidoQuest()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
	}
	
	public static void main(String [] agrg){
		
		String qtd = System.getProperty("line.separator");  
		qtd = new Integer("13").toString()+ "\n" + "26";
		System.out.println(qtd); 
	}
	
	public String getCdrConfirmadosSemAnalise(){
		String qtd = getSccQuestionamentoView().getQtdCdrCsa().toString();
		String minReais = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiReaisCsa()));
		String minArred = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiArredondadosCsa()));
		String vlrLiquido = formataDouble(zeroIfNull(getSccQuestionamentoView().getVlrLiquidoCsa()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public String getCdrConfirmadosComAnalise(){
		String qtd = getSccQuestionamentoView().getQtdCdrCcap().toString();
		String minReais = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiReaisCcap()));
		String minArred = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiArredondadosCcap()));
		String vlrLiquido = formataDouble(zeroIfNull(getSccQuestionamentoView().getVlrLiquidoCcap()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public String getCdrConfirmadosComAnaliseSemProcedencia(){

		String qtd = getSccQuestionamentoView().getQtdCdrCanp().toString();
		String minReais = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiReaisCanp()));
		String minArred = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiArredondadosCanp()));
		String vlrLiquido = formataDouble(zeroIfNull(getSccQuestionamentoView().getVlrLiquidoCanp()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;

	}
	
	public String getCdrPenalidadeClaro(){

		String qtd = getSccQuestionamentoView().getQtdCdrClaro().toString();
		String minReais = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiReaisClaro()));
		String minArred = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiArredondadosClaro()));
		String vlrLiquido = formataDouble(zeroIfNull(getSccQuestionamentoView().getVlrLiquidoClaro()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public String getCdrPenalidadeLd(){

		String qtd = getSccQuestionamentoView().getQtdCdrLd().toString();
		String minReais = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiReaisLd()));
		String minArred = formataDouble(zeroIfNull(getSccQuestionamentoView().getMiArredondadosLd()));
		String vlrLiquido = formataDouble(zeroIfNull(getSccQuestionamentoView().getVlrLiquidoLd()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public SccQuestionamentoView getSccQuestionamentoView(){
		return (SccQuestionamentoView)getCurrentRowObject();
	}


	
	


}
