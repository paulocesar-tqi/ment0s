/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author 93046251
 *
 */
@Entity
@Table(name="SCC_ASSOCIACAO_RELATORIO_GRUPO")
public class SccRelatorioGrupo {
	
	@EmbeddedId
	private SccRelatorioGrupoPk pk;
	
	private Long qtdDiasEscalada;
	
	private Date dtFimVigencia;
	
	private String cdUsuarioManutencao;

	public SccRelatorioGrupoPk getPk() {
		return pk;
	}

	public void setPk(SccRelatorioGrupoPk pk) {
		this.pk = pk;
	}

	public Long getQtdDiasEscalada() {
		return qtdDiasEscalada;
	}

	public void setQtdDiasEscalada(Long qtdDiasEscalada) {
		this.qtdDiasEscalada = qtdDiasEscalada;
	}

	@Column(name="DT_FIM_VIGENCIA")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManutencao() {
		return cdUsuarioManutencao;
	}

	public void setCdUsuarioManutencao(String cdUsuarioManutencao) {
		this.cdUsuarioManutencao = cdUsuarioManutencao;
	}
	
	
	

}
