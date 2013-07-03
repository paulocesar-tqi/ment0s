package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * The persistent class for the SCC_GRUPO_COBILLING database table.
 * 
 */
@Entity
@SequenceGenerator(name="SCC_GRUPO_COBILLING_SQ01" , sequenceName="SCC_GRUPO_COBILLING_SQ01")
@Table(name="SCC_GRUPO_COBILLING")
public class SccGrupoCobilling extends FwjBaseEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_GRUPO_COBILLING_SQ01")
	@Column(name="SQ_GRUPO")
	private Long sqGrupo;

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

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	private Date dtInicioVigencia;

	@Column(name="NO_GRUPO")
	private String noGrupo;

	//bi-directional many-to-one association to SccAssociacaoRelatorioGrupo
	@JsonIgnore
	@OneToMany(mappedBy="sccGrupoCobilling", fetch=FetchType.LAZY)
	private List<SccAssociacaoRelatorioGrupo> sccAssociacaoRelatorioGrupos;

	//bi-directional many-to-one association to SccComposicaoGrupoEmail
	@JsonIgnore
	@OneToMany(mappedBy="sccGrupoCobilling", fetch=FetchType.LAZY)
	private List<SccComposicaoGrupoEmail> sccComposicaoGrupoEmails;

	public SccGrupoCobilling() {
	}

	public Long getSqGrupo() {
		return this.sqGrupo;
	}

	public void setSqGrupo(Long sqGrupo) {
		this.sqGrupo = sqGrupo;
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

	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public String getNoGrupo() {
		return this.noGrupo;
	}

	public void setNoGrupo(String noGrupo) {
		this.noGrupo = noGrupo;
	}

	public List<SccAssociacaoRelatorioGrupo> getSccAssociacaoRelatorioGrupos() {
		return this.sccAssociacaoRelatorioGrupos;
	}

	public void setSccAssociacaoRelatorioGrupos(List<SccAssociacaoRelatorioGrupo> sccAssociacaoRelatorioGrupos) {
		this.sccAssociacaoRelatorioGrupos = sccAssociacaoRelatorioGrupos;
	}

	public List<SccComposicaoGrupoEmail> getSccComposicaoGrupoEmails() {
		return this.sccComposicaoGrupoEmails;
	}

	public void setSccComposicaoGrupoEmails(List<SccComposicaoGrupoEmail> sccComposicaoGrupoEmails) {
		this.sccComposicaoGrupoEmails = sccComposicaoGrupoEmails;
	}

}