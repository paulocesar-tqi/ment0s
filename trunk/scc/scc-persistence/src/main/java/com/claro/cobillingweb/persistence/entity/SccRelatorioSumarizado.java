package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the SCC_RELATORIO_SUMARIZADO database table.
 * 
 */
@Entity
@Table(name="SCC_RELATORIO_SUMARIZADO")
public class SccRelatorioSumarizado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccRelatorioSumarizadoPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

	@Column(name="NO_ARQUIVO_GERADO")
	private String noArquivoGerado;

	@Column(name="QT_REGISTROS")
	private BigDecimal qtRegistros;

	//bi-directional many-to-one association to SccDistribuicaoRelatorio
	@OneToMany(mappedBy="sccRelatorioSumarizado", fetch=FetchType.LAZY)
	private Set<SccDistribuicaoRelatorio> sccDistribuicaoRelatorios;

	//bi-directional many-to-one association to SccRelatorioCobilling
    @ManyToOne
	@JoinColumn(name="SQ_RELATORIO")
	private SccRelatorioCobilling sccRelatorioCobilling;

    public SccRelatorioSumarizado() {
    }

	public SccRelatorioSumarizadoPK getId() {
		return this.id;
	}

	public void setId(SccRelatorioSumarizadoPK id) {
		this.id = id;
	}
	
	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNoArquivoGerado() {
		return this.noArquivoGerado;
	}

	public void setNoArquivoGerado(String noArquivoGerado) {
		this.noArquivoGerado = noArquivoGerado;
	}

	public BigDecimal getQtRegistros() {
		return this.qtRegistros;
	}

	public void setQtRegistros(BigDecimal qtRegistros) {
		this.qtRegistros = qtRegistros;
	}

	public Set<SccDistribuicaoRelatorio> getSccDistribuicaoRelatorios() {
		return this.sccDistribuicaoRelatorios;
	}

	public void setSccDistribuicaoRelatorios(Set<SccDistribuicaoRelatorio> sccDistribuicaoRelatorios) {
		this.sccDistribuicaoRelatorios = sccDistribuicaoRelatorios;
	}
	
	public SccRelatorioCobilling getSccRelatorioCobilling() {
		return this.sccRelatorioCobilling;
	}

	public void setSccRelatorioCobilling(SccRelatorioCobilling sccRelatorioCobilling) {
		this.sccRelatorioCobilling = sccRelatorioCobilling;
	}
	
}