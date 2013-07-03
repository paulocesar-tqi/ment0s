package com.claro.cobillingweb.persistence.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="SCC_PRE_TARIFA_ACB")
public class SccPreTarifaAcb extends FwjBaseEntidade {

	private static final long serialVersionUID = 1L;
	private long sqPreTarifaAcb;
	private String cdEotLd;
	private BigDecimal cdSoc;
	private String cdStatus;
	private String cdTipoPlano;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtIniVigencia;
	private String nmPlano;
	
	private List<SccPreItemTarifaAcb> listItems = new ArrayList<SccPreItemTarifaAcb>();

    public SccPreTarifaAcb() {
    }


	@Id
	@Column(name="SQ_PRE_TARIFA_ACB")
	public long getSqPreTarifaAcb() {
		return this.sqPreTarifaAcb;
	}

	public void setSqPreTarifaAcb(long sqPreTarifaAcb) {
		this.sqPreTarifaAcb = sqPreTarifaAcb;
	}


	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}


	@Column(name="CD_SOC")
	public BigDecimal getCdSoc() {
		return this.cdSoc;
	}

	public void setCdSoc(BigDecimal cdSoc) {
		this.cdSoc = cdSoc;
	}


	@Column(name="CD_STATUS")
	public String getCdStatus() {
		return this.cdStatus;
	}

	public void setCdStatus(String cdStatus) {
		this.cdStatus = cdStatus;
	}


	@Column(name="CD_TIPO_PLANO")
	public String getCdTipoPlano() {
		return this.cdTipoPlano;
	}

	public void setCdTipoPlano(String cdTipoPlano) {
		this.cdTipoPlano = cdTipoPlano;
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
	@Column(name="DT_INI_VIGENCIA")
	public Date getDtIniVigencia() {
		return this.dtIniVigencia;
	}

	public void setDtIniVigencia(Date dtIniVigencia) {
		this.dtIniVigencia = dtIniVigencia;
	}


	@Column(name="NM_PLANO")
	public String getNmPlano() {
		return this.nmPlano;
	}

	public void setNmPlano(String nmPlano) {
		this.nmPlano = nmPlano;
	}

	
	@OneToMany(mappedBy="id.sqPreTarifaAcb", fetch = FetchType.LAZY)
	public List<SccPreItemTarifaAcb> getListItems() {
		return listItems;
	}


	public void setListItems(List<SccPreItemTarifaAcb> listItems) {
		this.listItems = listItems;
	}
	
	
}
