package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoPenalidadeView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccQuestionamentoPenalidadeViewDecorator extends RownumDecorator<SccQuestionamentoPenalidadeView> {

	public SccQuestionamentoPenalidadeViewDecorator(
			SccQuestionamentoPenalidadeView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}
	
	public String getDescricao(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getDsMotivoRejeicao())){
			value = getRow().getDsMotivoRejeicao();
		}
		
		return value;
	}
	
	public String getQtdChamadas(){
		String value = "";
		if(getRow().getQtCdrs() != null){
			value = getRow().getQtCdrs().toString();
		}
		
		return value;
	}
	
	public String getQtdMinutos(){
		String value = "";
		if(getRow().getQtMinutos() != null){
			value = getRow().getQtMinutos().toString();
		}
		
		return value;
	}
	
	
	public String getVlrLiquido(){
		String value ="";
		if(getRow().getVlLiquido() != null){
			value = formataDouble(getRow().getVlLiquido());
		}
		return value;
	}
	
	public String getVlrBruto(){
		String value = "";
		if(getRow().getVlBruto() != null){
			value = formataDouble(getRow().getVlBruto());
		}
		return value;
	}
	
	public String getQtdChamadasRefaturadas(){
		String value = "";
		if(getRow().getQtCdrsRefaturadas() != null){
			value = getRow().getQtCdrsNaoRefaturadas().toString();
		}
		return value ;
	}
	
	public String getQtdMinutosRefaturadas(){
		String value = "";
		if(getRow().getQtMinutosRefaturadas() != null){
			value = getRow().getQtMinutosRefaturadas().toString();
		}
		
		return value;
	}
	
	public String getQtdChamadasNaoRefaturadas(){
		String value = "";
		if(getRow().getQtCdrsNaoRefaturadas() != null){
			value = getRow().getQtCdrsNaoRefaturadas().toString();
		}
		
		return value;
	}
	
	public String getQtdMinutosNaoRefaturadas(){
		String value = "";
		if(getRow().getQtMinutosNaoRefaturadas() != null){
			value = getRow().getQtMinutosNaoRefaturadas().toString();
		}
		return value;
	}
	
	public String getVlrPenalidade(){
		String value = "";
		if(getRow().getVlPenalidade() != null){
			value = formataDouble(getRow().getVlPenalidade());
		}
		return value;
	}
	

}
