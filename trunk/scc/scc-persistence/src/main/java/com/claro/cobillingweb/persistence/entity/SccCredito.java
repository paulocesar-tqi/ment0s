package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_CREDITO")
public class SccCredito {

	private static final long serialVersionUID = 1L;
	private long sqCredito;
	private String cdEotClaro;
	private String cdEotLd;
	private String cdMotivoRejeicao;
	private String cdStatusCredito;
	private Date dtCredito;
	private String nuMsisdnOrigem;
	private Long sqArquivoCredito;
	private String tpCredito;
	private Double vlCofins;
	private Double vlCredito;
	private Double vlIcms;
	private Double vlLiquido;
	private Double vlPis;

    public SccCredito() {
    }


	@Id
	@Column(name="SQ_CREDITO")
	public long getSqCredito() {
		return this.sqCredito;
	}

	public void setSqCredito(long sqCredito) {
		this.sqCredito = sqCredito;
	}


	@Column(name="CD_EOT_CLARO")
	public String getCdEotClaro() {
		return this.cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}


	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}


	@Column(name="CD_MOTIVO_REJEICAO")
	public String getCdMotivoRejeicao() {
		return this.cdMotivoRejeicao;
	}

	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}


	@Column(name="CD_STATUS_CREDITO")
	public String getCdStatusCredito() {
		return this.cdStatusCredito;
	}

	public void setCdStatusCredito(String cdStatusCredito) {
		this.cdStatusCredito = cdStatusCredito;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_CREDITO")
	public Date getDtCredito() {
		return this.dtCredito;
	}

	public void setDtCredito(Date dtCredito) {
		this.dtCredito = dtCredito;
	}


	@Column(name="NU_MSISDN_ORIGEM")
	public String getNuMsisdnOrigem() {
		return this.nuMsisdnOrigem;
	}

	public void setNuMsisdnOrigem(String nuMsisdnOrigem) {
		this.nuMsisdnOrigem = nuMsisdnOrigem;
	}


	@Column(name="SQ_ARQUIVO_CREDITO")
	public Long getSqArquivoCredito() {
		return this.sqArquivoCredito;
	}

	public void setSqArquivoCredito(Long sqArquivoCredito) {
		this.sqArquivoCredito = sqArquivoCredito;
	}


	@Column(name="TP_CREDITO")
	public String getTpCredito() {
		return this.tpCredito;
	}

	public void setTpCredito(String tpCredito) {
		this.tpCredito = tpCredito;
	}


	@Column(name="VL_COFINS")
	public Double getVlCofins() {
		return this.vlCofins;
	}

	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}


	@Column(name="VL_CREDITO")
	public Double getVlCredito() {
		return this.vlCredito;
	}

	public void setVlCredito(Double vlCredito) {
		this.vlCredito = vlCredito;
	}


	@Column(name="VL_ICMS")
	public Double getVlIcms() {
		return this.vlIcms;
	}

	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}


	@Column(name="VL_LIQUIDO")
	public Double getVlLiquido() {
		return this.vlLiquido;
	}

	public void setVlLiquido(Double vlLiquido) {
		this.vlLiquido = vlLiquido;
	}


	@Column(name="VL_PIS")
	public Double getVlPis() {
		return this.vlPis;
	}

	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}
	
}
