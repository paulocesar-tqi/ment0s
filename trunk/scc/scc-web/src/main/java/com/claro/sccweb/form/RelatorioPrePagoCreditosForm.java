/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;

/**
 * @author 92031709
 *
 */
public class RelatorioPrePagoCreditosForm extends BaseForm {
	
	private SccPrePagoCreditosView entity;
	
	private SccPrePagoCreditosView arquivoSelecionado;
	
	private SccPrePagoCreditosView cdrSelecionado;
	
	private SccPrePagoCreditosView filtroSelecionado;
	
	private String operadoraLD;
	
	private String operadoraClaro;
	
	private String tipoCredito;
	
	private String cdStatusCredito;
	
	private Date dtInicial;
	
	private Date dtFinal;
	
	public SccPrePagoCreditosView getEntity() {
		return entity;
	}

	public void setEntity(SccPrePagoCreditosView entity) {
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

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getCdStatusCredito() {
		return cdStatusCredito;
	}

	public void setCdStatusCredito(String cdStatusCredito) {
		this.cdStatusCredito = cdStatusCredito;
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

	public SccPrePagoCreditosView getFiltroSelecionado() {
		return filtroSelecionado;
	}

	public void setFiltroSelecionado(SccPrePagoCreditosView filtroSelecionado) {
		this.filtroSelecionado = filtroSelecionado;
	}

	public SccPrePagoCreditosView getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(SccPrePagoCreditosView arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public SccPrePagoCreditosView getCdrSelecionado() {
		return cdrSelecionado;
	}

	public void setCdrSelecionado(SccPrePagoCreditosView cdrSelecionado) {
		this.cdrSelecionado = cdrSelecionado;
	}
}
