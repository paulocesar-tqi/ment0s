package com.claro.sccweb.decorator.rownum.view;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.RelControleRemessaPorEventoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class RelControleRemessaEventoViewDecorator extends RownumDecorator<RelControleRemessaPorEventoView> {
	
	
	private Date dtReferencia;
	private String cdEotClaro;
	private String cdEotLd;
	private Long statusCdr;
	private String subStatusCdr;
	private String dsProduto;
	private String evento;
	private Long qtdCdr;
	private Double vlrLiquido;
	private Double vlrBruto;
	private Double qtdDuracaoReal;
	private Double qtdDuracaoTarifada;
	private Long cdClico;
	private Long aaCiclo;
	private Long mmCiclo;
	
	
	

	public RelControleRemessaEventoViewDecorator(RelControleRemessaPorEventoView entity,
			int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	public Date getDtReferencia() {
		return dtReferencia;
	}

	public void setDtReferencia(Date dtReferencia) {
		this.dtReferencia = dtReferencia;
	}

	public String getCdEotClaro() {
		return cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public Long getStatusCdr() {
		return statusCdr;
	}

	public void setStatusCdr(Long statusCdr) {
		this.statusCdr = statusCdr;
	}

	public String getSubStatusCdr() {
		return subStatusCdr;
	}

	public void setSubStatusCdr(String subStatusCdr) {
		this.subStatusCdr = subStatusCdr;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Long getQtdCdr() {
		return qtdCdr;
	}

	public void setQtdCdr(Long qtdCdr) {
		this.qtdCdr = qtdCdr;
	}

	public Double getVlrLiquido() {
		return vlrLiquido;
	}

	public void setVlrLiquido(Double vlrLiquido) {
		this.vlrLiquido = vlrLiquido;
	}

	public Double getVlrBruto() {
		return vlrBruto;
	}

	public void setVlrBruto(Double vlrBruto) {
		this.vlrBruto = vlrBruto;
	}

	public Double getQtdDuracaoReal() {
		return qtdDuracaoReal;
	}

	public void setQtdDuracaoReal(Double qtdDuracaoReal) {
		this.qtdDuracaoReal = qtdDuracaoReal;
	}

	public Double getQtdDuracaoTarifada() {
		return qtdDuracaoTarifada;
	}

	public void setQtdDuracaoTarifada(Double qtdDuracaoTarifada) {
		this.qtdDuracaoTarifada = qtdDuracaoTarifada;
	}

	public Long getCdClico() {
		return cdClico;
	}

	public void setCdClico(Long cdClico) {
		this.cdClico = cdClico;
	}

	public Long getAaCiclo() {
		return aaCiclo;
	}

	public void setAaCiclo(Long aaCiclo) {
		this.aaCiclo = aaCiclo;
	}

	public Long getMmCiclo() {
		return mmCiclo;
	}

	public void setMmCiclo(Long mmCiclo) {
		this.mmCiclo = mmCiclo;
	}

	
	
	
}
