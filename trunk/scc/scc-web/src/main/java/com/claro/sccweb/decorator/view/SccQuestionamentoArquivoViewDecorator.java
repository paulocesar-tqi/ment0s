package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccQuestionamentoArquivoViewDecorator extends RownumDecorator<SccQuestionamentoArquivoView> {
	
	
	public SccQuestionamentoArquivoViewDecorator(
			SccQuestionamentoArquivoView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}
	
	public String getNoArquivo(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNoArquivo())){
			value = getRow().getNoArquivo();
		}
		return value;
	}
	
	public String getAssinante(){
		String value = "";
		if(getRow().getQtdAssinante() != null){
			value = getRow().getQtdAssinante().toString();
		}
		return value;
	}

	
	public String getQtdRegra(){
		String value = "";
		if(getRow().getQtdRegra() != null){
			value = getRow().getQtdRegra().toString();
		}
		return value;
	}
	
	public String getQtdeParametro(){
		String value = "";
		if(getRow().getQtdParametro() != null){
			value = getRow().getQtdParametro().toString();
		}
		return value;
	}
	
	public String getQtdLogica(){
		String value = "";
		if(getRow().getQtdLogica() != null){
			value = getRow().getQtdLogica().toString();
		}
		
		return value;
	}

	public String getQtdCdr(){
		String value = "";
		if(getRow().getQtdCdr() != null){
			value = getRow().getQtdCdr().toString();
		}
		return value;
	}

	public String getMinutosTarifados(){
		String value = "";
		if(getRow().getMinutosTarifados() != null){
			value = formataDouble(getRow().getMinutosTarifados());
		}
		
		return value;
	}
	
	public String getVlrLiquido(){
		String value = "";
		if(getRow().getVlrLiquido() != null){
			value = formataDouble(getRow().getVlrLiquido());
		}
		return value;
	}
	
	public Long getSqRemessaQuestionamento(){
		Long value = null;
		if(getRow().getSqRemessaQuestionamento() != null){
			value = getRow().getSqRemessaQuestionamento();
		}
		return value;
	}
	
	public Long getsqQuestionamento(){
		Long value = null;
		if(getRow().getSqQuestionamento() != null){
			value = getRow().getSqQuestionamento();
		}
		
		return value;
	}
	
	
	
	

}
