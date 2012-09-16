package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;

public class RelatorioPrestacaoServicoPreForm extends BaseForm {
	
	private RelPrestacaoServicoView entity;
	
	private String cdEOTClaro;
	
	private String cdEOTLD;
	
	private Integer periodo;
	
	private Long mesRelatorio;
	
	private Long anoRelatorio;
	
	private Long cdProdutoCobilling;
	
	private Long cdPeriodicidade;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private String mesAno;
	
	

	public RelPrestacaoServicoView getEntity() {
		return entity;
	}

	public void setEntity(RelPrestacaoServicoView entity) {
		this.entity = entity;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getCdEOTLD() {
		return cdEOTLD;
	}

	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Long getMesRelatorio() {
		return mesRelatorio;
	}

	public void setMesRelatorio(Long mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

	public Long getAnoRelatorio() {
		return anoRelatorio;
	}

	public void setAnoRelatorio(Long anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}

	public Long getCdProdutoCobilling() {
		return cdProdutoCobilling;
	}

	public void setCdProdutoCobilling(Long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public Long getCdPeriodicidade() {
		return cdPeriodicidade;
	}

	public void setCdPeriodicidade(Long cdPeriodicidade) {
		this.cdPeriodicidade = cdPeriodicidade;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	

	

}
