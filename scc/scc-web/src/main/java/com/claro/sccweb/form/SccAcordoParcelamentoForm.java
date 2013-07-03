package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;
import com.claro.sccweb.decorator.view.SccAcordoParcelamentoViewDecorator;

public class SccAcordoParcelamentoForm extends BaseForm {
	
	private SccAcordoParcelamentoView entity;
	
	private String tipoRelatorio;
	
	private String operadoraLd;
	
	private String operadoraClaro;
	
	private String status;
	
	private Long numeroConta;
	
	private Long numeroAcordo;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private Boolean isSintetico;
	
	private List <SccAcordoParcelamentoViewDecorator> listSintetico;
	private List <SccAcordoParcelamentoViewDecorator> listAnalitico;
	
	public SccAcordoParcelamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccAcordoParcelamentoView entity) {
		this.entity = entity;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getOperadoraLd() {
		return operadoraLd;
	}

	public void setOperadoraLd(String operadoraLd) {
		this.operadoraLd = operadoraLd;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Long getNumeroAcordo() {
		return numeroAcordo;
	}

	public void setNumeroAcordo(Long numeroAcordo) {
		this.numeroAcordo = numeroAcordo;
	}

	public Date getDataInicialPeriodo() {
		return dataInicialPeriodo;
	}

	public void setDataInicialPeriodo(Date dataInicialPeriodo) {
		this.dataInicialPeriodo = dataInicialPeriodo;
	}

	public Date getDataFinalPeriodo() {
		return dataFinalPeriodo;
	}

	public void setDataFinalPeriodo(Date dataFinalPeriodo) {
		this.dataFinalPeriodo = dataFinalPeriodo;
	}

	public Boolean getIsSintetico() {
		return isSintetico;
	}

	public void setIsSintetico(Boolean isSintetico) {
		isSintetico = this.tipoRelatorio.equalsIgnoreCase("S");
		this.isSintetico = isSintetico;
	}

	public List<SccAcordoParcelamentoViewDecorator> getListSintetico() {
		return listSintetico;
	}

	public void setListSintetico(
			List<SccAcordoParcelamentoViewDecorator> listSintetico) {
		this.listSintetico = listSintetico;
	}

	public List<SccAcordoParcelamentoViewDecorator> getListAnalitico() {
		return listAnalitico;
	}

	public void setListAnalitico(
			List<SccAcordoParcelamentoViewDecorator> listAnalitico) {
		this.listAnalitico = listAnalitico;
	}
	
	

	

}
