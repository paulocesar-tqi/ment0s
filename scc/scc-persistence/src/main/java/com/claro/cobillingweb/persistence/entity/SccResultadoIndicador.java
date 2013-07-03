package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCC_RESULTADO_INDICADOR database table.
 * 
 */
@Entity
@Table(name="SCC_RESULTADO_INDICADOR")
public class SccResultadoIndicador extends FwjBaseEntidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccResultadoIndicadorPK id;
    @Temporal( TemporalType.DATE)
	@Column(name="DT_CARGA")
	private Date dtCarga;

	@Column(name="VL_INDICADOR")
	private BigDecimal vlIndicador;

	@Column(name="VL_ORIGEM_INDICADOR_1")
	private BigDecimal vlOrigemIndicador1;

	@Column(name="VL_ORIGEM_INDICADOR_2")
	private BigDecimal vlOrigemIndicador2;

	//bi-directional many-to-one association to SccAgingIndicador
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CD_INDICADOR", referencedColumnName="CD_INDICADOR" , insertable=false, updatable=false),
		@JoinColumn(name="SQ_AGING_INDICADOR", referencedColumnName="SQ_AGING_INDICADOR" , insertable=false, updatable=false)
		})
	private SccAgingIndicador sccAgingIndicador;

	//bi-directional many-to-one association to SccIndicador
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_INDICADOR", insertable=false, updatable=false)
	private SccIndicador sccIndicador;
	

    public SccResultadoIndicador() {
    }

	public SccResultadoIndicadorPK getId() {
		return this.id;
	}

	public void setId(SccResultadoIndicadorPK id) {
		this.id = id;
	}
	
	public Date getDtCarga() {
		return this.dtCarga;
	}

	public void setDtCarga(Date dtCarga) {
		this.dtCarga = dtCarga;
	}

	public BigDecimal getVlIndicador() {
		return this.vlIndicador;
	}

	public void setVlIndicador(BigDecimal vlIndicador) {
		this.vlIndicador = vlIndicador;
	}

	public BigDecimal getVlOrigemIndicador1() {
		return this.vlOrigemIndicador1;
	}

	public void setVlOrigemIndicador1(BigDecimal vlOrigemIndicador1) {
		this.vlOrigemIndicador1 = vlOrigemIndicador1;
	}

	public BigDecimal getVlOrigemIndicador2() {
		return this.vlOrigemIndicador2;
	}

	public void setVlOrigemIndicador2(BigDecimal vlOrigemIndicador2) {
		this.vlOrigemIndicador2 = vlOrigemIndicador2;
	}

	public SccAgingIndicador getSccAgingIndicador() {
		return this.sccAgingIndicador;
	}

	public void setSccAgingIndicador(SccAgingIndicador sccAgingIndicador) {
		this.sccAgingIndicador = sccAgingIndicador;
	}
	
	public SccIndicador getSccIndicador() {
		return this.sccIndicador;
	}

	public void setSccIndicador(SccIndicador sccIndicador) {
		this.sccIndicador = sccIndicador;
	}
	
}