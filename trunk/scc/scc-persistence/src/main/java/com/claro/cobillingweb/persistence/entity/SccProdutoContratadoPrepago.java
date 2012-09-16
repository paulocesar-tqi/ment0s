package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_PRODUTO_CONTRATADO_PREPAGO")
public class SccProdutoContratadoPrepago {

	
	private SccProdutoContratadoPrepagoPK id;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String inDesabilitarRepasse;
	

    public SccProdutoContratadoPrepago() {
    }


	@EmbeddedId
	public SccProdutoContratadoPrepagoPK getId() {
		return this.id;
	}

	public void setId(SccProdutoContratadoPrepagoPK id) {
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


	@Column(name="IN_DESABILITAR_REPASSE")
	public String getInDesabilitarRepasse() {
		return this.inDesabilitarRepasse;
	}

	public void setInDesabilitarRepasse(String inDesabilitarRepasse) {
		this.inDesabilitarRepasse = inDesabilitarRepasse;
	}
	

	
}
