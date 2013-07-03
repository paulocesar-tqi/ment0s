/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Collection;

import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;



/**
 * @author vagner.souza
 *
 */
public class SccIndicadorForm extends BaseForm {
	
	private SccAgingIndicador entity;
	
	private SccIndicador entidade;
	
	private String cdTipoContrato;
	
	private String idIndicador;

	
	private SccFiltroIndicador filtro = new SccFiltroIndicador();
	
	private Collection<SccAgingIndicador> listAgingIndicador;
	
	private Collection<SccIndicador> listIndicador;
	
	
	
	

	public SccAgingIndicador getEntity() {
		return entity;
	}

	public void setEntity(SccAgingIndicador entity) {
		this.entity = entity;
	}
	
	

	public SccIndicador getEntidade() {
		return entidade;
	}

	public void setEntidade(SccIndicador entidade) {
		this.entidade = entidade;
	}

	public String getCdTipoContrato() {
		return cdTipoContrato;
	}

	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}

	public String getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(String idIndicador) {
		this.idIndicador = idIndicador;
	}

	public SccFiltroIndicador getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroIndicador filtro) {
		this.filtro = filtro;
	}

	public Collection<SccAgingIndicador> getListAgingIndicador() {
		return listAgingIndicador;
	}

	public void setListAgingIndicador(
			Collection<SccAgingIndicador> listAgingIndicador) {
		this.listAgingIndicador = listAgingIndicador;
	}

	public Collection<SccIndicador> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(Collection<SccIndicador> listIndicador) {
		this.listIndicador = listIndicador;
	}
	
	
	

}
