package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SCC_STATUS_QUESTIONAMENTO database table.
 * 
 */
@Entity
@Table(name="SCC_STATUS_QUESTIONAMENTO")
public class SccStatusQuestionamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SCC_STATUS_QUESTIONAMENTO_CDSTATUSQUESTIONAMENTO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_STATUS_QUESTIONAMENTO_CDSTATUSQUESTIONAMENTO_GENERATOR")
	@Column(name="CD_STATUS_QUESTIONAMENTO")
	private String cdStatusQuestionamento;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

	@Column(name="DS_STATUS_QUESTIONAMENTO")
	private String dsStatusQuestionamento;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

	//bi-directional many-to-one association to SccControleQuestionamento
	@OneToMany(mappedBy="sccStatusQuestionamento")
	private Set<SccControleQuestionamento> sccControleQuestionamentos;

    public SccStatusQuestionamento() {
    }

	public String getCdStatusQuestionamento() {
		return this.cdStatusQuestionamento;
	}

	public void setCdStatusQuestionamento(String cdStatusQuestionamento) {
		this.cdStatusQuestionamento = cdStatusQuestionamento;
	}

	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public String getDsStatusQuestionamento() {
		return this.dsStatusQuestionamento;
	}

	public void setDsStatusQuestionamento(String dsStatusQuestionamento) {
		this.dsStatusQuestionamento = dsStatusQuestionamento;
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

	public Set<SccControleQuestionamento> getSccControleQuestionamentos() {
		return this.sccControleQuestionamentos;
	}

	public void setSccControleQuestionamentos(Set<SccControleQuestionamento> sccControleQuestionamentos) {
		this.sccControleQuestionamentos = sccControleQuestionamentos;
	}
	
}