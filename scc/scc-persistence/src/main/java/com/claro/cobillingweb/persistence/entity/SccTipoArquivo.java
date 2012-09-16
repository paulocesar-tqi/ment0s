package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entidade que representa a tabela SCC_TIPO_ARQUIVO.
 *
 */
@NamedQueries({
	@NamedQuery(name="SccTipoArquivo.GET_ALL" , query="SELECT t from SccTipoArquivo t"),
	@NamedQuery(name="SccTipoArquivo.BY_BATIMENTO" , query="SELECT t.cdTipoArquivo from SccTipoArquivo t where t.cdTipoBatimento = :cdBatimento")
		})
@Entity
@Table(name="SCC_TIPO_ARQUIVO")
public class SccTipoArquivo implements BaseEntity {


	private Long cdTipoArquivo;
	
	
	private String dsTipoArquivo;
	
	
	private Date dtCriacao;
	
	
	private Date dtAtualizacao;
	
	
	private String cdUsuarioManutencao;
	
	
	private String cdTipoBatimento;

	@Column(name="CD_TIPO_ARQUIVO")
	@Id
	public Long getCdTipoArquivo() {
		return cdTipoArquivo;
	}

	public void setCdTipoArquivo(Long cdTipoArquivo) {
		this.cdTipoArquivo = cdTipoArquivo;
	}

	@Column(name="DS_TIPO_ARQUIVO")
	public String getDsTipoArquivo() {
		return dsTipoArquivo;
	}

	public void setDsTipoArquivo(String dsTipoArquivo) {
		this.dsTipoArquivo = dsTipoArquivo;
	}

	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	@Column(name="DT_ATUALIZACAO")
	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManutencao() {
		return cdUsuarioManutencao;
	}

	public void setCdUsuarioManutencao(String cdUsuarioManutencao) {
		this.cdUsuarioManutencao = cdUsuarioManutencao;
	}

	@Column(name="CD_TIPO_BATIMENTO")
	public String getCdTipoBatimento() {
		return cdTipoBatimento;
	}

	public void setCdTipoBatimento(String cdTipoBatimento) {
		this.cdTipoBatimento = cdTipoBatimento;
	}
	
	
	
}
