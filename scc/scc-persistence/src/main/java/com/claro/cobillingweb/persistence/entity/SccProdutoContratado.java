package com.claro.cobillingweb.persistence.entity;

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
@Table(name="SCC_PRODUTO_CONTRATADO")
public class SccProdutoContratado {

	private static final long serialVersionUID = 1L;
	private SccProdutoContratadoPK id;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private String fgProdutoPadrao;
	private SccContratoCobl sccContratoCobl;
	private SccProdutoCobilling sccProdutoCobilling;

    public SccProdutoContratado() {
    }
    
	@EmbeddedId
	public SccProdutoContratadoPK getId() {
		return this.id;
	}
	
	public void setId(SccProdutoContratadoPK id) {
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
	
    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}
    
	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}
	
	@Column(name="FG_PRODUTO_PADRAO")
	public String getFgProdutoPadrao() {
		return this.fgProdutoPadrao;
	}
	
	public void setFgProdutoPadrao(String fgProdutoPadrao) {
		this.fgProdutoPadrao = fgProdutoPadrao;
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
	
	//bi-directional many-to-one association to SccProdutoCobilling
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_PRODUTO_COBILLING",updatable=false,insertable=false)
	public SccProdutoCobilling getSccProdutoCobilling() {
		return this.sccProdutoCobilling;
	}
	
	public void setSccProdutoCobilling(SccProdutoCobilling sccProdutoCobilling) {
		this.sccProdutoCobilling = sccProdutoCobilling;
	}
	
}
