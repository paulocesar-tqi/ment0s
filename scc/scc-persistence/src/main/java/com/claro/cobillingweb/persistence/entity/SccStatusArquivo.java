package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entidade que representa a tabela SCC_STATUS_ARQUIVO.
 * 
 */
@NamedQueries({
	@NamedQuery(name="SccStatusArquivo.GET_ALL" , query="SELECT t from SccStatusArquivo t")
		})
@Entity
@Table(name="SCC_STATUS_ARQUIVO")
public class SccStatusArquivo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	private String cdStatusArquivo;
	
	private String cdUsuarioManut;
	private String dsStatusArquivo;
	private Date dtAtualizacao;
	private Date dtCriacao;

    public SccStatusArquivo() {
    }


	@Id
	@Column(name="CD_STATUS_ARQUIVO")
	public String getCdStatusArquivo() {
		return this.cdStatusArquivo;
	}

	public void setCdStatusArquivo(String cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_STATUS_ARQUIVO")
	public String getDsStatusArquivo() {
		return this.dsStatusArquivo;
	}

	public void setDsStatusArquivo(String dsStatusArquivo) {
		this.dsStatusArquivo = dsStatusArquivo;
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

}
