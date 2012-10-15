package com.claro.sccweb.decorator.view;

import com.claro.cobillingweb.persistence.view.FaturamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class FaturamentoViewDecorator extends RownumDecorator<FaturamentoView> {

	public FaturamentoViewDecorator(FaturamentoView entity, int rownum) {
		super(entity, rownum);
		
	}
	
	public String getOperadoraLd(){
		return getRow().getCdCsp();
	}
	
	public String getOperadoraClaro(){
		return getRow().getOperadoraClaro();
	}
	
	public String getUf(){
		return getRow().getUf();
	}
	
	public String getCiclo(){
		//TODO Nao esquecer de fazer tratamento da String apos conseguir inserir o Commons.lang
		String str = getRow().getCdCiclo().toString() +"/"+ getRow().getMmCiclo().toString() +"/"+ getRow().getAaCiclo().toString();
		return str;
	}
	
	public String getSerieNotaFiscal(){
		String str = "";
		if(getRow().getSerieNotaFiscal() != null && !getRow().getSerieNotaFiscal().equals("")){
			
			str = getRow().getSerieNotaFiscal();
		}
		
		return str;
	}
	
	public String getSubSerieNotaFiscal(){
		String str = "";
		
		if(getRow().getSubSerieNotaFiscal() != null && !getRow().getSubSerieNotaFiscal().equals("")){
			str = getRow().getSubSerieNotaFiscal();
		}
		
		return str;
	}
	
	public String  getNumeroNota(){
		
		String str = "";
		
		if(getRow().getNumNotaFiscal() != null){
			str = getRow().getNumNotaFiscal().toString();
		}
		
		return str;
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}

}
