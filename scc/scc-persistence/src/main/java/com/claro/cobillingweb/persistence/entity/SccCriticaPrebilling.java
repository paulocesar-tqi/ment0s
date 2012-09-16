package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_CRITICA_PREBILLING")
public class SccCriticaPrebilling {

	private static final long serialVersionUID = 1L;
	private String cdCritica;
	private String cdErroReciclagem;
	private String cdSaida;
	private String cdTipoSaida1;
	private String cdTipoSaida2;
	private String cdTipoSaida3;
	private String cdUsuarioManut;
	private String dsCritica;
	private Date dtAtualizacao;
	private Date dtCriacao;
	

    public SccCriticaPrebilling() {
    }


	@Id
	@Column(name="CD_CRITICA")
	public String getCdCritica() {
		return this.cdCritica;
	}

	public void setCdCritica(String cdCritica) {
		this.cdCritica = cdCritica;
	}


	@Column(name="CD_ERRO_RECICLAGEM")
	public String getCdErroReciclagem() {
		return this.cdErroReciclagem;
	}

	public void setCdErroReciclagem(String cdErroReciclagem) {
		this.cdErroReciclagem = cdErroReciclagem;
	}


	@Column(name="CD_SAIDA")
	public String getCdSaida() {
		return this.cdSaida;
	}

	public void setCdSaida(String cdSaida) {
		this.cdSaida = cdSaida;
	}


	@Column(name="CD_TIPO_SAIDA_1")
	public String getCdTipoSaida1() {
		return this.cdTipoSaida1;
	}

	public void setCdTipoSaida1(String cdTipoSaida1) {
		this.cdTipoSaida1 = cdTipoSaida1;
	}


	@Column(name="CD_TIPO_SAIDA_2")
	public String getCdTipoSaida2() {
		return this.cdTipoSaida2;
	}

	public void setCdTipoSaida2(String cdTipoSaida2) {
		this.cdTipoSaida2 = cdTipoSaida2;
	}


	@Column(name="CD_TIPO_SAIDA_3")
	public String getCdTipoSaida3() {
		return this.cdTipoSaida3;
	}

	public void setCdTipoSaida3(String cdTipoSaida3) {
		this.cdTipoSaida3 = cdTipoSaida3;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_CRITICA")
	public String getDsCritica() {
		return this.dsCritica;
	}

	public void setDsCritica(String dsCritica) {
		this.dsCritica = dsCritica;
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
