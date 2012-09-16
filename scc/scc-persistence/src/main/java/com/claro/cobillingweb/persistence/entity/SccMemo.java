package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_MEMO")
public class SccMemo {

	private static final long serialVersionUID = 1L;
	private Long nuMemo;
	private String cdUsuarioManut;
	private String dsOperacao;
	private String dsProcesso;
	private Date dtCriacao;
	private Date dtMemo;
	private String vlParametros;

    public SccMemo() {
    }


	@Id
	@Column(name="NU_MEMO")
	public Long getNuMemo() {
		return this.nuMemo;
	}

	public void setNuMemo(Long nuMemo) {
		this.nuMemo = nuMemo;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_OPERACAO")
	public String getDsOperacao() {
		return this.dsOperacao;
	}

	public void setDsOperacao(String dsOperacao) {
		this.dsOperacao = dsOperacao;
	}


	@Column(name="DS_PROCESSO")
	public String getDsProcesso() {
		return this.dsProcesso;
	}

	public void setDsProcesso(String dsProcesso) {
		this.dsProcesso = dsProcesso;
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
	@Column(name="DT_MEMO")
	public Date getDtMemo() {
		return this.dtMemo;
	}

	public void setDtMemo(Date dtMemo) {
		this.dtMemo = dtMemo;
	}


	@Column(name="VL_PARAMETROS")
	public String getVlParametros() {
		return this.vlParametros;
	}

	public void setVlParametros(String vlParametros) {
		this.vlParametros = vlParametros;
	}
	
}
