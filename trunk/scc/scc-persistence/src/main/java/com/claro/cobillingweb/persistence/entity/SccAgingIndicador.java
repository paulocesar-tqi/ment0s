package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the SCC_AGING_INDICADOR database table.
 * 
 */
@Entity
@Table(name="SCC_AGING_INDICADOR")
public class SccAgingIndicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccAgingIndicadorPK id;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

	@Column(name="VL_MAXIMO_AGING")
	private BigDecimal vlMaximoAging;

	@Column(name="VL_MINIMO_AGING")
	private BigDecimal vlMinimoAging;

	//bi-directional many-to-one association to SccIndicador
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_INDICADOR", insertable=false, updatable=false)
	private SccIndicador sccIndicador;
	
	//bi-directional many-to-one association to SccResultadoIndicador
	@OneToMany(mappedBy="sccAgingIndicador", fetch=FetchType.LAZY)
	private Set<SccResultadoIndicador> sccResultadoIndicadors;

    public SccAgingIndicador() {
    }

	public SccAgingIndicadorPK getId() {
		return this.id;
	}

	public void setId(SccAgingIndicadorPK id) {
		this.id = id;
	}
	
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
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

	public BigDecimal getVlMaximoAging() {
		return this.vlMaximoAging;
	}

	public void setVlMaximoAging(BigDecimal vlMaximoAging) {
		this.vlMaximoAging = vlMaximoAging;
	}

	public BigDecimal getVlMinimoAging() {
		return this.vlMinimoAging;
	}

	public void setVlMinimoAging(BigDecimal vlMinimoAging) {
		this.vlMinimoAging = vlMinimoAging;
	}

	public SccIndicador getSccIndicador() {
		return this.sccIndicador;
	}

	public void setSccIndicador(SccIndicador sccIndicador) {
		this.sccIndicador = sccIndicador;
	}
	
	public Set<SccResultadoIndicador> getSccResultadoIndicadors() {
		return this.sccResultadoIndicadors;
	}

	public void setSccResultadoIndicadors(Set<SccResultadoIndicador> sccResultadoIndicadors) {
		this.sccResultadoIndicadors = sccResultadoIndicadors;
	}
	
}