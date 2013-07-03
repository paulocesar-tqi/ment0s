package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccQuestionamentoViewDecorator extends RownumDecorator<SccQuestionamentoView> {

	public SccQuestionamentoViewDecorator(SccQuestionamentoView entity,	int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}
	
	public String getDescricaoEot(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getDescricaoEotLD())){
			value = getRow().getDescricaoEotLD();
		}
		
		return value;
	}
	
	public String getCdEotClaro(){
		String value ="";
		if(StringUtils.isNotEmpty(getRow().getCdEotClaro())){
			value = getRow().getCdEotClaro();
		}
		return value;
	}
	
	public String getSgUf(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getSgUf())){
			value = getRow().getSgUf();
		}
		return value;
	}
	
	public String getSqQuestionamento(){
		String value = "";
		if(getRow().getSqQuestionamento() != null){
			value = getRow().getSqQuestionamento().toString();
		}
		
		return value;
	}
	
	public String getNoArquivo(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNoArquivo())){
			value = getRow().getNoArquivo();
		}
		
		return value;
	}
	
	public String getTotais(){
		return "Qtde <br>Min Reais<br>Min Arred<br>Vlr Liquido";
	}
	
	public String getCdrQuestionados(){
		String qtd = System.getProperty("line.separator");
		qtd = getRow().getQtdCdrQuest().toString();
		String minReais = formataDouble(zeroIfNull(getRow().getMiReaisQuest()));
		String minArred = formataDouble(zeroIfNull(getRow().getMiArredondadosQuest()));
		String vlrLiquido = formataDouble(zeroIfNull(getRow().getVlrLiquidoQuest()));
		return qtd + "\n" + minReais + "\n" + minArred + "\n" + vlrLiquido;
	}
	
	public static void main(String [] agrg){
		
		String qtd = System.getProperty("line.separator");  
		qtd = new Integer("13").toString()+ "\n" + "26";
		System.out.println(qtd); 
	}
	
	public String getCdrConfirmadosSemAnalise(){
		String qtd = getRow().getQtdCdrCsa().toString();
		String minReais = formataDouble(zeroIfNull(getRow().getMiReaisCsa()));
		String minArred = formataDouble(zeroIfNull(getRow().getMiArredondadosCsa()));
		String vlrLiquido = formataDouble(zeroIfNull(getRow().getVlrLiquidoCsa()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public String getCdrConfirmadosComAnalise(){
		String qtd = getRow().getQtdCdrCcap().toString();
		String minReais = formataDouble(zeroIfNull(getRow().getMiReaisCcap()));
		String minArred = formataDouble(zeroIfNull(getRow().getMiArredondadosCcap()));
		String vlrLiquido = formataDouble(zeroIfNull(getRow().getVlrLiquidoCcap()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public String getCdrConfirmadosComAnaliseSemProcedencia(){

		String qtd = getRow().getQtdCdrCanp().toString();
		String minReais = formataDouble(zeroIfNull(getRow().getMiReaisCanp()));
		String minArred = formataDouble(zeroIfNull(getRow().getMiArredondadosCanp()));
		String vlrLiquido = formataDouble(zeroIfNull(getRow().getVlrLiquidoCanp()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;

	}
	
	public String getCdrPenalidadeClaro(){

		String qtd = getRow().getQtdCdrClaro().toString();
		String minReais = formataDouble(zeroIfNull(getRow().getMiReaisClaro()));
		String minArred = formataDouble(zeroIfNull(getRow().getMiArredondadosClaro()));
		String vlrLiquido = formataDouble(zeroIfNull(getRow().getVlrLiquidoClaro()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}
	
	public String getCdrPenalidadeLd(){

		String qtd = getRow().getQtdCdrLd().toString();
		String minReais = formataDouble(zeroIfNull(getRow().getMiReaisLd()));
		String minArred = formataDouble(zeroIfNull(getRow().getMiArredondadosLd()));
		String vlrLiquido = formataDouble(zeroIfNull(getRow().getVlrLiquidoLd()));
		return qtd + "</br>" + minReais + "</br>" + minArred + "</br>" + vlrLiquido;
		
	}

}
