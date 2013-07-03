package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;

public class SccResultadoIndicadorDecorator extends TableDecorator {
	
	
	
	public String getAgingDias(){
		
		String value = "";
		SccResultadoIndicadorView view = (SccResultadoIndicadorView) getCurrentRowObject();
		
		if(view.getAgingDias() != null){
			value = view.getAgingDias();
		}
		
		return getLink(value);
	}
	
	public String getResultado(){

		String value = "";
		SccResultadoIndicadorView view = (SccResultadoIndicadorView) getCurrentRowObject();
		
		if(view.getVlIndicador() != null){
			value = view.getVlIndicador().toString();
		}
		
		return getLink(value);
		
	}
	
	public String getLink(String value){
		
		SccResultadoIndicadorView view = (SccResultadoIndicadorView) getCurrentRowObject();
		
		return "<a href=\"listarArquivos.scc?cdEotLd=" + view.getCdEotLd() + "&cdEotClaro="+view.getCdEotClaro()+"&dataInicial="+view.getDtIniFiltro()+"&dataFinal="+view.getDtFimFiltro()+ "\">"+value+"</a>" ;
		
	}
	

}
