/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author 93046251
 *
 */


@Embeddable
public class SccRelatorioGrupoPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5227165872924610394L;
	

	private SccGrupoCobilling grupoCobilling;
	
	private SccRelatorioCobilling relatorioCobilling;
	
	private Date dtInicioVigencia;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_GRUPO")
	public SccGrupoCobilling getGrupoCobilling() {
		return grupoCobilling;
	}

	public void setGrupoCobilling(SccGrupoCobilling grupoCobilling) {
		this.grupoCobilling = grupoCobilling;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_RELATORIO")
	public SccRelatorioCobilling getRelatorioCobilling() {
		return relatorioCobilling;
	}

	public void setRelatorioCobilling(SccRelatorioCobilling relatorioCobilling) {
		this.relatorioCobilling = relatorioCobilling;
	}

	@Column(name="DT_INICIO_VIGENCIA")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}
	
	
	

}
