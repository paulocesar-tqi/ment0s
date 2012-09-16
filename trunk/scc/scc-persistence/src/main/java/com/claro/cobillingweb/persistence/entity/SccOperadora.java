package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the SCC_OPERADORA database table.
 * 
 */
@Entity
@Table(name="SCC_OPERADORA")
public class SccOperadora implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cdEot;
	private String cdCsp;
	private String cdEmpresaContabil;
	private String cdEmpresaMobile;
	private String cdLocalNegocio;
	private String cdRegional;
	private String cdTipoServico;
	private String cdUsuarioManut;
	private String dsOperadora;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String fgEotFiscal;
	private Integer qtDiasChamadaIntExpira;
	private Integer qtDiasChamadaNacExpira;
	private Integer qtDiasExclusao;
	private Integer qtLimiteIdadeInt;
	private Integer qtLimiteIdadeNac;
	private String sgUf;
	private String cdOperadoraHolding;
	
	
    public SccOperadora() {
    }


	@Id
	@Column(name="CD_EOT")
	public String getCdEot() {
		return this.cdEot;
	}

	public void setCdEot(String cdEot) {
		this.cdEot = cdEot;
	}


	@Column(name="CD_CSP")
	public String getCdCsp() {
		return this.cdCsp;
	}

	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}


	@Column(name="CD_EMPRESA_CONTABIL")
	public String getCdEmpresaContabil() {
		return this.cdEmpresaContabil;
	}

	public void setCdEmpresaContabil(String cdEmpresaContabil) {
		this.cdEmpresaContabil = cdEmpresaContabil;
	}


	@Column(name="CD_EMPRESA_MOBILE")
	public String getCdEmpresaMobile() {
		return this.cdEmpresaMobile;
	}

	public void setCdEmpresaMobile(String cdEmpresaMobile) {
		this.cdEmpresaMobile = cdEmpresaMobile;
	}


	@Column(name="CD_LOCAL_NEGOCIO")
	public String getCdLocalNegocio() {
		return this.cdLocalNegocio;
	}

	public void setCdLocalNegocio(String cdLocalNegocio) {
		this.cdLocalNegocio = cdLocalNegocio;
	}


	@Column(name="CD_REGIONAL")
	public String getCdRegional() {
		return this.cdRegional;
	}

	public void setCdRegional(String cdRegional) {
		this.cdRegional = cdRegional;
	}


	@Column(name="CD_TIPO_SERVICO")
	public String getCdTipoServico() {
		return this.cdTipoServico;
	}

	public void setCdTipoServico(String cdTipoServico) {
		this.cdTipoServico = cdTipoServico;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_OPERADORA")
	public String getDsOperadora() {
		return this.dsOperadora;
	}

	public void setDsOperadora(String dsOperadora) {
		this.dsOperadora = dsOperadora;
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


	@Column(name="FG_EOT_FISCAL")
	public String getFgEotFiscal() {
		return this.fgEotFiscal;
	}

	public void setFgEotFiscal(String fgEotFiscal) {
		this.fgEotFiscal = fgEotFiscal;
	}


	@Column(name="QT_DIAS_CHAMADA_INT_EXPIRA")
	public Integer getQtDiasChamadaIntExpira() {
		return this.qtDiasChamadaIntExpira;
	}

	public void setQtDiasChamadaIntExpira(Integer qtDiasChamadaIntExpira) {
		this.qtDiasChamadaIntExpira = qtDiasChamadaIntExpira;
	}


	@Column(name="QT_DIAS_CHAMADA_NAC_EXPIRA")
	public Integer getQtDiasChamadaNacExpira() {
		return this.qtDiasChamadaNacExpira;
	}

	public void setQtDiasChamadaNacExpira(Integer qtDiasChamadaNacExpira) {
		this.qtDiasChamadaNacExpira = qtDiasChamadaNacExpira;
	}


	@Column(name="QT_DIAS_EXCLUSAO")
	public Integer getQtDiasExclusao() {
		return this.qtDiasExclusao;
	}

	public void setQtDiasExclusao(Integer qtDiasExclusao) {
		this.qtDiasExclusao = qtDiasExclusao;
	}


	@Column(name="QT_LIMITE_IDADE_INT")
	public Integer getQtLimiteIdadeInt() {
		return this.qtLimiteIdadeInt;
	}

	public void setQtLimiteIdadeInt(Integer qtLimiteIdadeInt) {
		this.qtLimiteIdadeInt = qtLimiteIdadeInt;
	}


	@Column(name="QT_LIMITE_IDADE_NAC")
	public Integer getQtLimiteIdadeNac() {
		return this.qtLimiteIdadeNac;
	}

	public void setQtLimiteIdadeNac(Integer qtLimiteIdadeNac) {
		this.qtLimiteIdadeNac = qtLimiteIdadeNac;
	}


	@Column(name="SG_UF")
	public String getSgUf() {
		return this.sgUf;
	}

	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}


	
	@Column(name="CD_EOT_HOLDING")
	public String getCdOperadoraHolding() {
		return cdOperadoraHolding;
	}


	public void setCdOperadoraHolding(String cdOperadoraHolding) {
		this.cdOperadoraHolding = cdOperadoraHolding;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Transient
	public boolean isHolding()
	{
		return (getCdOperadoraHolding() == null) || (getCdEot().equals(getCdOperadoraHolding()));	
	}


	
}