package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.filtro.SccFiltroFaturas;
import com.claro.cobillingweb.persistence.view.SccFaturaView;

public class SccFaturasForm extends BaseForm{
	
	private String csp;
	private String cdEotClaro;
	
	
	private SccFiltroFaturas filtro;
	
	private Date dataInicialPeriodo;
	
	private Date dataFinalPeriodo;
	
	private Long tipoData;
	
	private String status;
	
	private String numeroFatura;
	
	private String relatorioSelecionado ;
	
	List<SccFaturaView> listFaturas;

	
	public SccFiltroFaturas getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroFaturas filtro) {
		this.filtro = filtro;
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

	public Long getTipoData() {
		return tipoData;
	}

	public void setTipoData(Long tipoData) {
		this.tipoData = tipoData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public String getRelatorioSelecionado() {
		return relatorioSelecionado;
	}

	public void setRelatorioSelecionado(String relatorioSelecionado) {
		this.relatorioSelecionado = relatorioSelecionado;
	}

	public List<SccFaturaView> getListFaturas() {
		return listFaturas;
	}

	public void setListFaturas(List<SccFaturaView> listFaturas) {
		this.listFaturas = listFaturas;
	}

	public String getCsp() {
		return csp;
	}

	public void setCsp(String csp) {
		this.csp = csp;
	}

	public String getCdEotClaro() {
		return cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}
	
	
	
	
}
