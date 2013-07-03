package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccParamProcesso;

public class SccRequisicaoDecorator extends TableDecorator {
	
	
	public SccParamProcesso gerarEntity(){
		
		return (SccParamProcesso) getCurrentRowObject();
		
	}
	
	public String getAnoMesReferencia(){
		
		String value = "";
		
		if(StringUtils.isNotEmpty(gerarEntity().getId().getNmParametro())){
			value = gerarEntity().getId().getNmParametro().substring(0,7);
		}
		return value;
	}
	
	public String getNoRequisicao(){
		String value = "";
		
		if(StringUtils.isNotEmpty(gerarEntity().getId().getNmParametro())){
			value = gerarEntity().getId().getNmParametro().substring(11,gerarEntity().getId().getNmParametro().length());
		}
		return value;
		
	}
	
	public static void main(String[] args){
		
		String teste = "07/2012 2110000000000000000023";
		String value = teste.substring(11, teste.length());
		System.out.println(value);
		System.out.println(value.length());
	}

}
