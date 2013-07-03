/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccChamadasRefaturamentoView;

/**
 * @author 92031709
 *
 */
public class RelatorioChamadasRefaturamentoForm extends BaseForm {
	
	private SccChamadasRefaturamentoView entity;
	
	private String operadoraLD;
	
	private String operadoraClaro;
	
	private String tipoRefaturamento;
	
	private String statusChamada;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	public SccChamadasRefaturamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccChamadasRefaturamentoView entity) {
		this.entity = entity;
	}

	public String getOperadoraLD() {
		return operadoraLD;
	}

	public void setOperadoraLD(String operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getTipoRefaturamento() {
		return tipoRefaturamento;
	}

	public void setTipoRefaturamento(String tipoRefaturamento) {
		this.tipoRefaturamento = tipoRefaturamento;
	}

	public String getStatusChamada() {
		return statusChamada;
	}

	public void setStatusChamada(String statusChamada) {
		this.statusChamada = statusChamada;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

}
