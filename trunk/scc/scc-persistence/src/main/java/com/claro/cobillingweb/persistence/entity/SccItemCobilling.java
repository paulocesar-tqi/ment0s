package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_ITEM_COBILLING")
public class SccItemCobilling {

	private static final long serialVersionUID = 1L;
	private long cdItemCobilling;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Long inAbrangenciaChamada;
	private String inTipoItem;
	private String inTipoTerminal;
	private String inUsoFranquia;
	private String noItemCobilling;
	
	@Id
	@Column(name="CD_ITEM_COBILLING")
	public long getCdItemCobilling() {
		return this.cdItemCobilling;
	}

	public void setCdItemCobilling(long cdItemCobilling) {
		this.cdItemCobilling = cdItemCobilling;
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


	@Column(name="IN_ABRANGENCIA_CHAMADA")
	public Long getInAbrangenciaChamada() {
		return this.inAbrangenciaChamada;
	}

	public void setInAbrangenciaChamada(Long inAbrangenciaChamada) {
		this.inAbrangenciaChamada = inAbrangenciaChamada;
	}


	@Column(name="IN_TIPO_ITEM")
	public String getInTipoItem() {
		return this.inTipoItem;
	}

	public void setInTipoItem(String inTipoItem) {
		this.inTipoItem = inTipoItem;
	}


	@Column(name="IN_TIPO_TERMINAL")
	public String getInTipoTerminal() {
		return this.inTipoTerminal;
	}

	public void setInTipoTerminal(String inTipoTerminal) {
		this.inTipoTerminal = inTipoTerminal;
	}


	@Column(name="IN_USO_FRANQUIA")
	public String getInUsoFranquia() {
		return this.inUsoFranquia;
	}

	public void setInUsoFranquia(String inUsoFranquia) {
		this.inUsoFranquia = inUsoFranquia;
	}


	@Column(name="NO_ITEM_COBILLING")
	public String getNoItemCobilling() {
		return this.noItemCobilling;
	}

	public void setNoItemCobilling(String noItemCobilling) {
		this.noItemCobilling = noItemCobilling;
	}

	
}
