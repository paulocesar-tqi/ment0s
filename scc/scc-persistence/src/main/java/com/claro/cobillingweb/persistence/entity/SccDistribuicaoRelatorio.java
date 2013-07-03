package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCC_DISTRIBUICAO_RELATORIO database table.
 * 
 */
@Entity
@Table(name="SCC_DISTRIBUICAO_RELATORIO")
public class SccDistribuicaoRelatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SccDistribuicaoRelatorioPK id;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ENVIO_EMAIL")
	private Date dtEnvioEmail;

	@Column(name="SQ_RELATORIO")
	private BigDecimal sqRelatorio;

	//bi-directional many-to-one association to SccRelatorioSumarizado
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="DT_RELATORIO", referencedColumnName="DT_RELATORIO", insertable=false, updatable=false),
		@JoinColumn(name="SQ_RELATORIO_SUMARIZADO", referencedColumnName="SQ_RELATORIO_SUMARIZADO", insertable=false, updatable=false)
		})
	private SccRelatorioSumarizado sccRelatorioSumarizado;

    public SccDistribuicaoRelatorio() {
    }

	public SccDistribuicaoRelatorioPK getId() {
		return this.id;
	}

	public void setId(SccDistribuicaoRelatorioPK id) {
		this.id = id;
	}
	
	public Date getDtEnvioEmail() {
		return this.dtEnvioEmail;
	}

	public void setDtEnvioEmail(Date dtEnvioEmail) {
		this.dtEnvioEmail = dtEnvioEmail;
	}

	public BigDecimal getSqRelatorio() {
		return this.sqRelatorio;
	}

	public void setSqRelatorio(BigDecimal sqRelatorio) {
		this.sqRelatorio = sqRelatorio;
	}

	public SccRelatorioSumarizado getSccRelatorioSumarizado() {
		return this.sccRelatorioSumarizado;
	}

	public void setSccRelatorioSumarizado(SccRelatorioSumarizado sccRelatorioSumarizado) {
		this.sccRelatorioSumarizado = sccRelatorioSumarizado;
	}
	
}