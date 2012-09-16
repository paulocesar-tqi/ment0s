/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

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

/**
 * @author 93046251
 *
 */

@Entity
@SequenceGenerator(name="SCC_EMAIL_COBILLING_SQ01" , sequenceName="SCC_EMAIL_COBILLING_SQ01")
@Table(name="SCC_EMAIL_COBILLING")
public class SccEmailCobilling {
	
	private Long codigo;
	
	private String descricao;
	
	private Date dtCriacao;
	
	private Date dtAtualizacao;
	
	private String cdUsuarioManutencao;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_EMAIL_COBILLING_SQ01")
	@Column(name="SQ_EMAIL")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name="NO_EMAIL")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="DT_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	@Column(name="DT_ATUALIZACAO")
	@Temporal(TemporalType.TIMESTAMP)
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
	
	
	
	

}
