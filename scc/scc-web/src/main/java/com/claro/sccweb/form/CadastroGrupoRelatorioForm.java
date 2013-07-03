/**
 * 
 */
package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;

/**
 * @author 92038883
 *
 */
public class CadastroGrupoRelatorioForm extends BaseForm {
	
	private Long sqRelatorio;
	
	private List<SccRelatorioCobilling> listRelatorios;
	
	private List<SccAssociacaoRelatorioGrupo> listAssociados;
	
	private SccAssociacaoRelatorioGrupo entity;
	
	private String operacao;

	public Long getSqRelatorio() {
		return sqRelatorio;
	}

	public void setSqRelatorio(Long sqRelatorio) {
		this.sqRelatorio = sqRelatorio;
	}

	public List<SccRelatorioCobilling> getListRelatorios() {
		return listRelatorios;
	}

	public void setListRelatorios(List<SccRelatorioCobilling> listRelatorios) {
		this.listRelatorios = listRelatorios;
	}

	public List<SccAssociacaoRelatorioGrupo> getListAssociados() {
		return listAssociados;
	}

	public void setListAssociados(List<SccAssociacaoRelatorioGrupo> listAssociados) {
		this.listAssociados = listAssociados;
	}

	public SccAssociacaoRelatorioGrupo getEntity() {
		return entity;
	}

	public void setEntity(SccAssociacaoRelatorioGrupo entity) {
		this.entity = entity;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	
	

}
