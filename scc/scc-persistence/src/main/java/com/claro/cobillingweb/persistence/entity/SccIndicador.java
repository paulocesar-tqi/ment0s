package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the SCC_INDICADOR database table.
 * 
 */
@Entity
@Table(name="SCC_INDICADOR")
public class SccIndicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CD_INDICADOR")
	private String cdIndicador;

	@Column(name="CD_DWLIND")
	private String cdDwlind;

	@Column(name="CD_STATUS_INDICADOR")
	private String cdStatusIndicador;

	@Column(name="CD_TIPO_INDICADOR")
	private String cdTipoIndicador;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

	@Column(name="DS_FORMATO_INDICADOR")
	private String dsFormatoIndicador;

	@Column(name="DS_INDICADOR")
	private String dsIndicador;

	@Column(name="DS_PERIODICIDADE")
	private String dsPeriodicidade;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Temporal( TemporalType.DATE)
	@Column(name="DT_REFERENCIA")
	private Date dtReferencia;

	@Column(name="IN_RESPONS_INFORMACAO")
	private String inResponsInformacao;

	@Column(name="PC_META_INDICADOR")
	private BigDecimal pcMetaIndicador;

	@Column(name="PC_PESO_INDICADOR")
	private BigDecimal pcPesoIndicador;

	//bi-directional many-to-one association to SccAgingIndicador
	@JsonIgnore
	@OneToMany(mappedBy="sccIndicador", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<SccAgingIndicador> sccAgingIndicadors;

	//bi-directional many-to-one association to SccResultadoIndicador
	@JsonIgnore
	@OneToMany(mappedBy="sccIndicador", fetch=FetchType.LAZY)
	private Set<SccResultadoIndicador> sccResultadoIndicadors;

    public SccIndicador() {
    }

	public String getCdIndicador() {
		return this.cdIndicador;
	}

	public void setCdIndicador(String cdIndicador) {
		this.cdIndicador = cdIndicador;
	}

	public String getCdDwlind() {
		return this.cdDwlind;
	}

	public void setCdDwlind(String cdDwlind) {
		this.cdDwlind = cdDwlind;
	}

	public String getCdStatusIndicador() {
		return this.cdStatusIndicador;
	}

	public void setCdStatusIndicador(String cdStatusIndicador) {
		this.cdStatusIndicador = cdStatusIndicador;
	}

	public String getCdTipoIndicador() {
		return this.cdTipoIndicador;
	}

	public void setCdTipoIndicador(String cdTipoIndicador) {
		this.cdTipoIndicador = cdTipoIndicador;
	}

	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public String getDsFormatoIndicador() {
		return this.dsFormatoIndicador;
	}

	public void setDsFormatoIndicador(String dsFormatoIndicador) {
		this.dsFormatoIndicador = dsFormatoIndicador;
	}

	public String getDsIndicador() {
		return this.dsIndicador;
	}

	public void setDsIndicador(String dsIndicador) {
		this.dsIndicador = dsIndicador;
	}

	public String getDsPeriodicidade() {
		return this.dsPeriodicidade;
	}

	public void setDsPeriodicidade(String dsPeriodicidade) {
		this.dsPeriodicidade = dsPeriodicidade;
	}

	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtReferencia() {
		return this.dtReferencia;
	}

	public void setDtReferencia(Date dtReferencia) {
		this.dtReferencia = dtReferencia;
	}

	public String getInResponsInformacao() {
		return this.inResponsInformacao;
	}

	public void setInResponsInformacao(String inResponsInformacao) {
		this.inResponsInformacao = inResponsInformacao;
	}

	public BigDecimal getPcMetaIndicador() {
		return this.pcMetaIndicador;
	}

	public void setPcMetaIndicador(BigDecimal pcMetaIndicador) {
		this.pcMetaIndicador = pcMetaIndicador;
	}

	public BigDecimal getPcPesoIndicador() {
		return this.pcPesoIndicador;
	}

	public void setPcPesoIndicador(BigDecimal pcPesoIndicador) {
		this.pcPesoIndicador = pcPesoIndicador;
	}

	public Set<SccAgingIndicador> getSccAgingIndicadors() {
		return this.sccAgingIndicadors;
	}

	public void setSccAgingIndicadors(Set<SccAgingIndicador> sccAgingIndicadors) {
		this.sccAgingIndicadors = sccAgingIndicadors;
	}
	
	public Set<SccResultadoIndicador> getSccResultadoIndicadors() {
		return this.sccResultadoIndicadors;
	}

	public void setSccResultadoIndicadors(Set<SccResultadoIndicador> sccResultadoIndicadors) {
		this.sccResultadoIndicadors = sccResultadoIndicadors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdDwlind == null) ? 0 : cdDwlind.hashCode());
		result = prime * result
				+ ((cdIndicador == null) ? 0 : cdIndicador.hashCode());
		result = prime
				* result
				+ ((cdStatusIndicador == null) ? 0 : cdStatusIndicador
						.hashCode());
		result = prime * result
				+ ((cdTipoIndicador == null) ? 0 : cdTipoIndicador.hashCode());
		result = prime * result
				+ ((cdUsuarioManut == null) ? 0 : cdUsuarioManut.hashCode());
		result = prime
				* result
				+ ((dsFormatoIndicador == null) ? 0 : dsFormatoIndicador
						.hashCode());
		result = prime * result
				+ ((dsIndicador == null) ? 0 : dsIndicador.hashCode());
		result = prime * result
				+ ((dsPeriodicidade == null) ? 0 : dsPeriodicidade.hashCode());
		result = prime * result
				+ ((dtAtualizacao == null) ? 0 : dtAtualizacao.hashCode());
		result = prime * result
				+ ((dtCriacao == null) ? 0 : dtCriacao.hashCode());
		result = prime * result
				+ ((dtReferencia == null) ? 0 : dtReferencia.hashCode());
		result = prime
				* result
				+ ((inResponsInformacao == null) ? 0 : inResponsInformacao
						.hashCode());
		result = prime * result
				+ ((pcMetaIndicador == null) ? 0 : pcMetaIndicador.hashCode());
		result = prime * result
				+ ((pcPesoIndicador == null) ? 0 : pcPesoIndicador.hashCode());
		result = prime
				* result
				+ ((sccAgingIndicadors == null) ? 0 : sccAgingIndicadors
						.hashCode());
		result = prime
				* result
				+ ((sccResultadoIndicadors == null) ? 0
						: sccResultadoIndicadors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SccIndicador other = (SccIndicador) obj;
		if (cdDwlind == null) {
			if (other.cdDwlind != null)
				return false;
		} else if (!cdDwlind.equals(other.cdDwlind))
			return false;
		if (cdIndicador == null) {
			if (other.cdIndicador != null)
				return false;
		} else if (!cdIndicador.equals(other.cdIndicador))
			return false;
		if (cdStatusIndicador == null) {
			if (other.cdStatusIndicador != null)
				return false;
		} else if (!cdStatusIndicador.equals(other.cdStatusIndicador))
			return false;
		if (cdTipoIndicador == null) {
			if (other.cdTipoIndicador != null)
				return false;
		} else if (!cdTipoIndicador.equals(other.cdTipoIndicador))
			return false;
		if (cdUsuarioManut == null) {
			if (other.cdUsuarioManut != null)
				return false;
		} else if (!cdUsuarioManut.equals(other.cdUsuarioManut))
			return false;
		if (dsFormatoIndicador == null) {
			if (other.dsFormatoIndicador != null)
				return false;
		} else if (!dsFormatoIndicador.equals(other.dsFormatoIndicador))
			return false;
		if (dsIndicador == null) {
			if (other.dsIndicador != null)
				return false;
		} else if (!dsIndicador.equals(other.dsIndicador))
			return false;
		if (dsPeriodicidade == null) {
			if (other.dsPeriodicidade != null)
				return false;
		} else if (!dsPeriodicidade.equals(other.dsPeriodicidade))
			return false;
		if (dtAtualizacao == null) {
			if (other.dtAtualizacao != null)
				return false;
		} else if (!dtAtualizacao.equals(other.dtAtualizacao))
			return false;
		if (dtCriacao == null) {
			if (other.dtCriacao != null)
				return false;
		} else if (!dtCriacao.equals(other.dtCriacao))
			return false;
		if (dtReferencia == null) {
			if (other.dtReferencia != null)
				return false;
		} else if (!dtReferencia.equals(other.dtReferencia))
			return false;
		if (inResponsInformacao == null) {
			if (other.inResponsInformacao != null)
				return false;
		} else if (!inResponsInformacao.equals(other.inResponsInformacao))
			return false;
		if (pcMetaIndicador == null) {
			if (other.pcMetaIndicador != null)
				return false;
		} else if (!pcMetaIndicador.equals(other.pcMetaIndicador))
			return false;
		if (pcPesoIndicador == null) {
			if (other.pcPesoIndicador != null)
				return false;
		} else if (!pcPesoIndicador.equals(other.pcPesoIndicador))
			return false;
		if (sccAgingIndicadors == null) {
			if (other.sccAgingIndicadors != null)
				return false;
		} else if (!sccAgingIndicadors.equals(other.sccAgingIndicadors))
			return false;
		if (sccResultadoIndicadors == null) {
			if (other.sccResultadoIndicadors != null)
				return false;
		} else if (!sccResultadoIndicadors.equals(other.sccResultadoIndicadors))
			return false;
		return true;
	}
	
}