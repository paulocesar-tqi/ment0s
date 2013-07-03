package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SCC_COMPOSICAO_GRUPO_EMAIL database table.
 * 
 */
@Entity
@Table(name="SCC_COMPOSICAO_GRUPO_EMAIL")
public class SccComposicaoGrupoEmail extends FwjBaseEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccComposicaoGrupoEmailPK id;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FIM_VIGENCIA")
	private Date dtFimVigencia;

	//bi-directional many-to-one association to SccEmailCobilling
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_EMAIL", insertable=false, updatable=false)
	private SccEmailCobilling sccEmailCobilling;

	//bi-directional many-to-one association to SccGrupoCobilling
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_GRUPO", insertable=false, updatable=false)
	private SccGrupoCobilling sccGrupoCobilling;

	public SccComposicaoGrupoEmail() {
	}

	public SccComposicaoGrupoEmailPK getId() {
		return this.id;
	}

	public void setId(SccComposicaoGrupoEmailPK id) {
		this.id = id;
	}

	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFimVigencia() {
		return this.dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public SccEmailCobilling getSccEmailCobilling() {
		return this.sccEmailCobilling;
	}

	public void setSccEmailCobilling(SccEmailCobilling sccEmailCobilling) {
		this.sccEmailCobilling = sccEmailCobilling;
	}

	public SccGrupoCobilling getSccGrupoCobilling() {
		return this.sccGrupoCobilling;
	}

	public void setSccGrupoCobilling(SccGrupoCobilling sccGrupoCobilling) {
		this.sccGrupoCobilling = sccGrupoCobilling;
	}

}