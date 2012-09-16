package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_TIPO_EVENTO")
public class SccTipoEvento {

	private static final long serialVersionUID = 1L;
	private SccTipoEventoPK id;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String fgAtribuirProdPadrao = "N";
	private String noTipoEvento;
	
    public SccTipoEvento() {
    }


	@EmbeddedId
	public SccTipoEventoPK getId() {
		return this.id;
	}

	public void setId(SccTipoEventoPK id) {
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


	@Column(name="FG_ATRIBUIR_PROD_PADRAO")
	public String getFgAtribuirProdPadrao() {
		return this.fgAtribuirProdPadrao;
	}

	public void setFgAtribuirProdPadrao(String fgAtribuirProdPadrao) {
		if(fgAtribuirProdPadrao == null && "".equals(fgAtribuirProdPadrao)){
			this.fgAtribuirProdPadrao = "N";
		}
		this.fgAtribuirProdPadrao = fgAtribuirProdPadrao;
	}


	@Column(name="NO_TIPO_EVENTO")
	public String getNoTipoEvento() {
		return this.noTipoEvento;
	}

	public void setNoTipoEvento(String noTipoEvento) {
		this.noTipoEvento = noTipoEvento;
	}


	
}
