package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
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

@Entity
@SequenceGenerator(name="SCC_FAIXA_PENALIDADE_SQ01" , sequenceName="SCC_FAIXA_PENALIDADE_SQ01")
@Table(name="SCC_FAIXA_PENALIDADE")
public class SccFaixaPenalidade extends FwjBaseEntidade implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9020402178818334318L;
	
	private Long cdFaixaPenalidade;
	private String dsFaixaPenalidade;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private String inTipoPenalidade;
	private Double peFimFaixaPenalidade;
	private Double peInicioFaixaPenalidade;
	private Double vlFatorCalculoPenalidade;
	

    public SccFaixaPenalidade() {
    }


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_FAIXA_PENALIDADE_SQ01")
	@Column(name="CD_FAIXA_PENALIDADE")
	public Long getCdFaixaPenalidade() {
		return this.cdFaixaPenalidade;
	}

	public void setCdFaixaPenalidade(Long cdFaixaPenalidade) {
		this.cdFaixaPenalidade = cdFaixaPenalidade;
	}


	@Column(name="DS_FAIXA_PENALIDADE")
	public String getDsFaixaPenalidade() {
		return this.dsFaixaPenalidade;
	}

	public void setDsFaixaPenalidade(String dsFaixaPenalidade) {
		this.dsFaixaPenalidade = dsFaixaPenalidade;
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
	@Column(name="DT_INICIO_VIGENCIA")
	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}


	@Column(name="IN_TIPO_PENALIDADE")
	public String getInTipoPenalidade() {
		return this.inTipoPenalidade;
	}

	public void setInTipoPenalidade(String inTipoPenalidade) {
		this.inTipoPenalidade = inTipoPenalidade;
	}


	@Column(name="PE_FIM_FAIXA_PENALIDADE")
	public Double getPeFimFaixaPenalidade() {
		return this.peFimFaixaPenalidade;
	}

	public void setPeFimFaixaPenalidade(Double peFimFaixaPenalidade) {
		this.peFimFaixaPenalidade = peFimFaixaPenalidade;
	}


	@Column(name="PE_INICIO_FAIXA_PENALIDADE")
	public Double getPeInicioFaixaPenalidade() {
		return this.peInicioFaixaPenalidade;
	}

	public void setPeInicioFaixaPenalidade(Double peInicioFaixaPenalidade) {
		this.peInicioFaixaPenalidade = peInicioFaixaPenalidade;
	}


	@Column(name="VL_FATOR_CALCULO_PENALIDADE")
	public Double getVlFatorCalculoPenalidade() {
		return this.vlFatorCalculoPenalidade;
	}

	public void setVlFatorCalculoPenalidade(Double vlFatorCalculoPenalidade) {
		this.vlFatorCalculoPenalidade = vlFatorCalculoPenalidade;
	}



	
}
