package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_CONTRATO_ACORDADO")
public class SccContratoAcordado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private SccContratoAcordadoPK id;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private SccContratoCobl sccContratoCobl;
	private SccOperadora operadoraClaro;
	private SccOperadora operadoraExterna;
	
    public SccContratoAcordado() { }
    
	@EmbeddedId
	public SccContratoAcordadoPK getId() {
		return this.id;
	}
	public void setId(SccContratoAcordadoPK id) {
		this.id = id;
	}
	
	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}
	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}
	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return this.dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_VIGENCIA")
	public Date getDtFimVigencia() {
		return this.dtFimVigencia;
	}
	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}
	
	//bi-directional many-to-one association to SccContratoCobl
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_CONTRATO_COBILLING",updatable=false,insertable=false)
	public SccContratoCobl getSccContratoCobl() {
		return this.sccContratoCobl;
	}
	public void setSccContratoCobl(SccContratoCobl sccContratoCobl) {
		this.sccContratoCobl = sccContratoCobl;
	}
	
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_EOT_CLARO",updatable=false,insertable=false)
	public SccOperadora getOperadoraClaro() {
		return this.operadoraClaro;
	}
	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_EOT_LD",updatable=false,insertable=false)
	public SccOperadora getOperadoraExterna() {
		return this.operadoraExterna;
	}
	public void setOperadoraExterna(SccOperadora operadoraExterna) {
		this.operadoraExterna = operadoraExterna;
	}
	
}
