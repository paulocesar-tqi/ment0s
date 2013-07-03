package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCC_ASSOCIACAO_RELATORIO_GRUPO database table.
 * 
 */
@Entity
@Table(name="SCC_ASSOCIACAO_RELATORIO_GRUPO")
public class SccAssociacaoRelatorioGrupo extends FwjBaseEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccAssociacaoRelatorioGrupoPK id;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FIM_VIGENCIA")
	private Date dtFimVigencia;

	@Column(name="QT_DIAS_ESCALADA")
	private BigDecimal qtDiasEscalada;

	//bi-directional many-to-one association to SccGrupoCobilling
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_GRUPO", insertable=false, updatable=false)
	private SccGrupoCobilling sccGrupoCobilling;

	//bi-directional many-to-one association to SccRelatorioCobilling
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_RELATORIO", insertable=false, updatable=false)
	private SccRelatorioCobilling sccRelatorioCobilling;

	public SccAssociacaoRelatorioGrupo() {
	}

	public SccAssociacaoRelatorioGrupoPK getId() {
		return this.id;
	}

	public void setId(SccAssociacaoRelatorioGrupoPK id) {
		this.id = id;
	}

	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public Date getDtFimVigencia() {
		return this.dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public BigDecimal getQtDiasEscalada() {
		return this.qtDiasEscalada;
	}

	public void setQtDiasEscalada(BigDecimal qtDiasEscalada) {
		this.qtDiasEscalada = qtDiasEscalada;
	}

	public SccGrupoCobilling getSccGrupoCobilling() {
		return this.sccGrupoCobilling;
	}

	public void setSccGrupoCobilling(SccGrupoCobilling sccGrupoCobilling) {
		this.sccGrupoCobilling = sccGrupoCobilling;
	}

	public SccRelatorioCobilling getSccRelatorioCobilling() {
		return this.sccRelatorioCobilling;
	}

	public void setSccRelatorioCobilling(SccRelatorioCobilling sccRelatorioCobilling) {
		this.sccRelatorioCobilling = sccRelatorioCobilling;
	}

}