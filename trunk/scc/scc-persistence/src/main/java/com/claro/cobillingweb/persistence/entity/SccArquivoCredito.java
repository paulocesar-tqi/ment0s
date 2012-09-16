package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_ARQUIVO_CREDITO")
public class SccArquivoCredito {

	private static final long serialVersionUID = 1L;
	private long sqArquivoCredito;
	private String cdOrigem;
	private String cdStatus;
	private Date dtCarga;
	private String noArquivo;
	private Long qtRegistros;

    public SccArquivoCredito() {
    }


	@Id
	@Column(name="SQ_ARQUIVO_CREDITO")
	public long getSqArquivoCredito() {
		return this.sqArquivoCredito;
	}

	public void setSqArquivoCredito(long sqArquivoCredito) {
		this.sqArquivoCredito = sqArquivoCredito;
	}


	@Column(name="CD_ORIGEM")
	public String getCdOrigem() {
		return this.cdOrigem;
	}

	public void setCdOrigem(String cdOrigem) {
		this.cdOrigem = cdOrigem;
	}


	@Column(name="CD_STATUS")
	public String getCdStatus() {
		return this.cdStatus;
	}

	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_CARGA")
	public Date getDtCarga() {
		return this.dtCarga;
	}

	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}


	@Column(name="NO_ARQUIVO")
	public String getNoArquivo() {
		return this.noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}


	@Column(name="QT_REGISTROS")
	public Long getQtRegistros() {
		return this.qtRegistros;
	}

	public void setQtRegistros(Long qtRegistros) {
		this.qtRegistros = qtRegistros;
	}
	
}
