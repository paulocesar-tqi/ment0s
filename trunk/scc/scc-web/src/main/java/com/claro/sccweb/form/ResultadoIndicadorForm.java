package com.claro.sccweb.form;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.entity.SccResultadoIndicador;
import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;

public class ResultadoIndicadorForm extends BaseForm {
	
	private String cdEotLd;
	
	private String cdEotClaro;
	
	private String cdIndicador;
	
	private String dsPeriodicidade;
	
	private String tipoContrato;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private String descricao;
	
	private List<SccResultadoIndicador> listResultadoIndicadores;
	private List<SccIndicador> listIndicadores;
	
	private List<SccResultadoIndicadorView> listResultadoIndicadorView;
	private List<SccResultadoIndicadorView> listResultadoIndicadorViewMensal;
	private List<SccResultadoIndicadorView> listArq;

	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public String getCdEotClaro() {
		return cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public String getCdIndicador() {
		return cdIndicador;
	}

	public void setCdIndicador(String cdIndicador) {
		this.cdIndicador = cdIndicador;
	}

	public String getDsPeriodicidade() {
		return dsPeriodicidade;
	}

	public void setDsPeriodicidade(String dsPeriodicidade) {
		this.dsPeriodicidade = dsPeriodicidade;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
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
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<SccResultadoIndicador> getListResultadoIndicadores() {
		return listResultadoIndicadores;
	}

	public void setListResultadoIndicadores(
			List<SccResultadoIndicador> listResultadoIndicadores) {
		this.listResultadoIndicadores = listResultadoIndicadores;
	}

	public List<SccIndicador> getListIndicadores() {
		return listIndicadores;
	}

	public void setListIndicadores(List<SccIndicador> listIndicadores) {
		this.listIndicadores = listIndicadores;
	}

	public List<SccResultadoIndicadorView> getListResultadoIndicadorView() {
		return listResultadoIndicadorView;
	}

	public void setListResultadoIndicadorView(
			List<SccResultadoIndicadorView> listResultadoIndicadorView) {
		this.listResultadoIndicadorView = listResultadoIndicadorView;
	}

	public List<SccResultadoIndicadorView> getListResultadoIndicadorViewMensal() {
		return listResultadoIndicadorViewMensal;
	}

	public void setListResultadoIndicadorViewMensal(
			List<SccResultadoIndicadorView> listResultadoIndicadorViewMensal) {
		this.listResultadoIndicadorViewMensal = listResultadoIndicadorViewMensal;
	}

	public List<SccResultadoIndicadorView> getListArq() {
		return listArq;
	}

	public void setListArq(List<SccResultadoIndicadorView> listArq) {
		this.listArq = listArq;
	}
	
	
}
