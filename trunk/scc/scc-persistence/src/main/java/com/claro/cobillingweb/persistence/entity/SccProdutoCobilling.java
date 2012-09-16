package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SCC_PRODUTO_COBILLING_SQ01" , sequenceName="SCC_PRODUTO_COBILLING_SQ01")
@Table(name="SCC_PRODUTO_COBILLING")
public class SccProdutoCobilling implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long cdProdutoCobilling;
	private String cdTipoProduto;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private String noProdutoCobilling;	
	
    public SccProdutoCobilling() {
    }
    
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_PRODUTO_COBILLING_SQ01")
	@Column(name="CD_PRODUTO_COBILLING")
	public Long getCdProdutoCobilling() {
		return this.cdProdutoCobilling;
	}
	
	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}
	
	@Column(name="CD_TIPO_PRODUTO")
	public String getCdTipoProduto() {
		return this.cdTipoProduto;
	}
	
	public void setCdTipoProduto(String cdTipoProduto) {
		this.cdTipoProduto = cdTipoProduto;
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
	
	@Column(name="NO_PRODUTO_COBILLING")
	public String getNoProdutoCobilling() {
		return this.noProdutoCobilling;
	}
	
	public void setNoProdutoCobilling(String noProdutoCobilling) {
		this.noProdutoCobilling = noProdutoCobilling;
	}
	
}
