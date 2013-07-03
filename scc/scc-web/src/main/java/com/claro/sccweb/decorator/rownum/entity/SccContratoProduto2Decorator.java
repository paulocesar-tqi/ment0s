package com.claro.sccweb.decorator.rownum.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccContratoProduto2Decorator extends RownumDecorator<SccContratoCobillingSearchView> {
	
	private SccContratoCobillingSearchView sccContratoCobillingSearchView;

	public SccContratoProduto2Decorator(SccContratoCobillingSearchView entity,
			int rownum) {
		super(entity, rownum);
		
	}
	
	public String getEditar(){
		return "<a href='#' onClick='editar("+getRownum()+")'> Editar </a>";
	}
	
	public String getOperadoraLD(){
		String value = "";
		if(getRow() != null && StringUtils.isNotEmpty(getRow().getCdEOTLD()) && StringUtils.isNotEmpty(getRow().getDsOperadoraLD())){
			
			value = getRow().getDsOperadoraLD() + "( " + getRow().getCdEOTLD() + " )";
		}
		return value;
		
	}

	public String getOperadoraClaro(){
		String value = "";
		if(getRow() != null && StringUtils.isNotEmpty(getRow().getCdEOTClaro()) && StringUtils.isNotEmpty(getRow().getDsOperadoraClaro())){
			
			value = getRow().getDsOperadoraClaro() + "( " + getRow().getCdEOTClaro() + " )";
		}
		return value;
	}
	
	
	public String getCdEotLd(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEOTLD())){
			value = getRow().getCdEOTLD();
		}
		return value;
	}

	public String getCdEotClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEOTClaro())){
			value = getRow().getCdEOTClaro();
		}
		return value;
	}
	
	
	
	
	public String getDsOperadoraLD(){
		return getRow().getDsOperadoraLD();
	}
	public String getDsOperadoraClaro(){
		return getRow().getDsOperadoraClaro();
	}
	
	public Date getDtInicioContrato(){
		return getRow().getDtInicioContrato();
	}
	
	public String getDtFinalContrato(){
		return formataDate(getRow().getDtFinalContrato());
	}
	
	public String getCdTipoContrato(){
		return getRow().getCdTipoContrato();
	}
	
	public String getDsCriterioCusto(){
		return getRow().getDsCriterioCusto();
	}
	
	public String getValorAssociadoCriterioCusto(){
		return formataDouble(getRow().getValorAssociadoCriterioCusto()); 
	}
	
	public String getDsPeriodoBase(){
		return getRow().getDsPeriodoBase();
	}
	
	public String getValorCPMF(){
		return formataDouble(getRow().getValorCPMF());
	}
	
	public String getValorISS(){
		return formataDouble(getRow().getValorISS());
	}
	
	public String getQtRepasses(){
		return formataLong(getRow().getQtRepasses());
	}
	
	public String getVlCriterioCustoLiquido(){
		return formataDouble(getRow().getVlCriterioCustoLiquido());
	}
	
	public String getPeRetencao(){
		return formataLong(getRow().getPeRetencao());
	}
	public String getDsPeriodoRepasse(){
		return getRow().getDsPeriodoRepasse();
	}
	public String  getDtCriacao(){
		return getRow().getDtCriacao();
	}
	public String  getDtAtualizacao(){
		return getRow().getDtAtualizacao();
	}
	public String  getCdUsuarioManut(){
		return getRow().getCdUsuarioManut();
	}
	public String  getDsTipoContrato(){
		return getRow().getDsTipoContrato();
	}
	
	public String getPeCofins(){
		return formataDouble(getRow().getPeCofins());
	}
	public String getPePis(){
		return formataDouble(getRow().getPePis());
	}
	public String  getFlagRepasseIcms(){
		return getRow().getFlagRepasseIcms();
	}
	public String  getFlagRepasseCpmf(){
		return getRow().getFlagRepasseCpmf();
	}

	
	
	
	
	
	
	

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}

}
