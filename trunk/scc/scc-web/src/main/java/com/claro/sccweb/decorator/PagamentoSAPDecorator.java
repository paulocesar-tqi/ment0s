package com.claro.sccweb.decorator;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepassePK;
import com.claro.cobillingweb.persistence.entity.SccRepasse;

public class PagamentoSAPDecorator extends BasicSccDecorator {

	private String operadoraClaro = " ";
	private String operadoraLD = " ";
	private String nuRepasse = " ";
	private String vlrRepasse = " ";
	private String sqArquivoRemessaSap = " ";
	private String sqArquivoRetornoSap = " ";
	private String dtPagamentoSap = " ";
	private String vlPagamentoSap = " ";
	private String lancado;
	private String uf;
	private String dtRepasse = " ";
	private String holding;
	private String cdEotLd;
	private String cdTipoContrato;
	private Date dtReferencia;
	private int index;
	private String novoStatus;
	private boolean modificado;
	private SccPagamentoRepasse pagamentoRepasse;
	
	
	public PagamentoSAPDecorator(SccPagamentoRepasse pagamentoRepasse,SccOperadora operadoraClaro,SccOperadora operadoraLD, int index) throws Exception{
		
		if(pagamentoRepasse == null || operadoraClaro == null || operadoraLD == null){
			throw new Exception("Valores obrigatorios não estao preenchidos");
		}
		
		this.index = index;
		this.pagamentoRepasse = pagamentoRepasse;
		this.operadoraClaro = operadoraClaro.getDsOperadora();
		this.operadoraLD = operadoraLD.getDsOperadora();
		this.nuRepasse = String.valueOf(pagamentoRepasse.getId().getNuRepasse());
		if(pagamentoRepasse.getFgContabAutomatica() != null){
			this.lancado = pagamentoRepasse.getFgContabAutomatica();
		}
		if(pagamentoRepasse.getVlRepasse() != null){
			this.vlrRepasse = decimalFormat.format(pagamentoRepasse.getVlRepasse());
		}
		
		if(pagamentoRepasse.getSgUf() != null){
			this.uf = pagamentoRepasse.getSgUf();
		}
		
		this.sqArquivoRemessaSap = spaceIfNull(pagamentoRepasse.getSqArquivoRemessaSap());
		this.sqArquivoRetornoSap = spaceIfNull(pagamentoRepasse.getSqArquivoRetornoSap());
		if (pagamentoRepasse.getDtPagamentoSap() != null)
			{
			this.dtPagamentoSap = dateFormat.format(pagamentoRepasse.getDtPagamentoSap());	
			}
		if (pagamentoRepasse.getVlPagamentoSap() != null)
			{
			this.vlPagamentoSap = decimalFormat.format(pagamentoRepasse.getVlPagamentoRepasse());
			}
		 
		if (pagamentoRepasse.getDtPagamentoRepasse() != null)
		{
		this.dtRepasse = dateFormat.format(pagamentoRepasse.getDtPagamentoRepasse());	
		}
		
		if(pagamentoRepasse.getId() != null){
			
			if (pagamentoRepasse.getId().getCdEotHolding() != null){
				this.holding = pagamentoRepasse.getId().getCdEotHolding();
			}
			
			if (pagamentoRepasse.getId().getCdEotLd() != null){
				this.cdEotLd = pagamentoRepasse.getId().getCdEotLd();
			}
			if (pagamentoRepasse.getId().getCdEotHolding() != null){
				this.cdTipoContrato = pagamentoRepasse.getId().getCdTipoContrato();
			}
			
			if (pagamentoRepasse.getId().getCdEotLd() != null){
				this.dtReferencia = pagamentoRepasse.getId().getDtReferencia();
			}

			
		}
		
		
	}
	
	public SccPagamentoRepassePK carregarChaves(){
		SccPagamentoRepassePK id = new SccPagamentoRepassePK();
		id.setCdEotHolding(getHolding());
		id.setCdEotLd(getCdEotLd());
		id.setCdTipoContrato(getCdTipoContrato());
		id.setDtReferencia(getDataReferencia());
		id.setNuRepasse(new Long(getNuRepasse()));
		return id;
	}
	
	
	
	public String getCheck(){		
		StringBuffer sb = new StringBuffer();	
		String status = pagamentoRepasse.getFgContabAutomatica();
		if (status != null)	{
			sb.append("<input type='checkbox' name='index_"+index+"' "); 
			novoStatus = "S";
			sb.append(" checked ");
			sb.append("value='S"+index+"'>");
		}else{
			sb.append("<input type='checkbox' name='index_"+index+"' ");
			sb.append("value='N"+index+"'>");
			novoStatus = "N";
		}
			
		return sb.toString();
	}
	
	public boolean getLancadoChecked(){
		boolean isLancado = false;
		
		if("S".equalsIgnoreCase(this.lancado)){
			isLancado = true;
		} 
		
		return isLancado;
	}
	
	
	public String getOperadoraClaro() {
		return operadoraClaro;
	}
	public String getOperadoraLD() {
		return operadoraLD;
	}
	public String getNuRepasse() {
		return nuRepasse;
	}
	public String getVlrRepasse() {
		return vlrRepasse;
	}
	public String getSqArquivoRemessaSap() {
		return sqArquivoRemessaSap;
	}
	public String getSqArquivoRetornoSap() {
		return sqArquivoRetornoSap;
	}
	public String getDtPagamentoSap() {
		return dtPagamentoSap;
	}
	public String getVlPagamentoSap() {
		return vlPagamentoSap;
	}
	
	public String getLancado(){
		return lancado;
	}
	
	public String getUf(){
		return uf;
	}
	
	public String getDtRepasse(){
		return dtRepasse;
	}
	
	public String getHolding(){
		return holding;
	}
	
	public Date getDataReferencia(){
		return dtReferencia;
	}
	
	public String getCdTipoContrato(){
		return cdTipoContrato;
	}
	
	public String getCdEotLd(){
		return cdEotLd;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getNovoStatus() {
		return novoStatus;
	}

	public void setNovoStatus(String novoStatus) {
		this.novoStatus = novoStatus;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public SccPagamentoRepasse getPagamentoRepasse() {
		return pagamentoRepasse;
	}

	public void setPagamentoRepasse(SccPagamentoRepasse pagamentoRepasse) {
		this.pagamentoRepasse = pagamentoRepasse;
	}
	
	
	
	
	
}
