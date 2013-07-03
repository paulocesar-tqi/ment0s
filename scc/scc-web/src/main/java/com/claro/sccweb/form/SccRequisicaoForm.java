package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;

public class SccRequisicaoForm extends BaseForm{
	
	private Long mes;
	
	private Long ano;
	
	private List<SccCdrQuestionamento> listQuestionamento;
	
	private Long sqCdrQuestionamento;
	
	private List<SccOperadora> listOperadora;
	
	private String cdEotLd;
	
	private List<SccParamProcesso> listLoaded;
	private List<SccParamProcesso> listToLoad;
	private List<SccParamProcesso> listLoading;

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public List<SccCdrQuestionamento> getListQuestionamento() {
		return listQuestionamento;
	}

	public void setListQuestionamento(List<SccCdrQuestionamento> listQuestionamento) {
		this.listQuestionamento = listQuestionamento;
	}

	public Long getSqCdrQuestionamento() {
		return sqCdrQuestionamento;
	}

	public void setSqCdrQuestionamento(Long sqCdrQuestionamento) {
		this.sqCdrQuestionamento = sqCdrQuestionamento;
	}

	public List<SccOperadora> getListOperadora() {
		return listOperadora;
	}

	public void setListOperadora(List<SccOperadora> listOperadora) {
		this.listOperadora = listOperadora;
	}

	public String getCdEotLd() {
		return cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	public List<SccParamProcesso> getListLoaded() {
		return listLoaded;
	}

	public void setListLoaded(List<SccParamProcesso> listLoaded) {
		this.listLoaded = listLoaded;
	}

	public List<SccParamProcesso> getListToLoad() {
		return listToLoad;
	}

	public void setListToLoad(List<SccParamProcesso> listToLoad) {
		this.listToLoad = listToLoad;
	}

	public List<SccParamProcesso> getListLoading() {
		return listLoading;
	}

	public void setListLoading(List<SccParamProcesso> listLoading) {
		this.listLoading = listLoading;
	}
	
	
	

}
