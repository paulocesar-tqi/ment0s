package com.claro.sccweb.form;

import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccCtlParalizacaoPlat;


public class ControleParalisacaoForm extends BaseForm {

	/*
	private String cdEotLd;
	private String nuBoletimClaro;
	private String cdAreaPlataforma;
	private Long cdTecnologiaPlataforma;
	private String cdTipoFalha;
	private String fgSolicDesbloqueioLd;
	private String nuBoletimLd;
	private Date dtInicial;
	private Date dtFinal;
	*/
	
	private SccCtlParalizacaoPlat entity;
	private SccCtlParalizacaoPlat filtro;
	private Date dtInicial;
	private Date dtFinal;
	
	public SccCtlParalizacaoPlat getEntity() {
		return entity;
	}
	public void setEntity(SccCtlParalizacaoPlat entity) {
		this.entity = entity;
	}
	public SccCtlParalizacaoPlat getFiltro() {
		return filtro;
	}
	public void setFiltro(SccCtlParalizacaoPlat filtro) {
		this.filtro = filtro;
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
