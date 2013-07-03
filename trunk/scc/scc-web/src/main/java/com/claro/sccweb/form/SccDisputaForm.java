/**
 * 
 */
package com.claro.sccweb.form;

import java.util.Collection;
import java.util.Date;

import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputa;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaForm extends BaseForm {
	
	private SccFiltroDisputa filtro = new SccFiltroDisputa();
	
	private SccDisputa entity;
	
	private Collection<SccDisputa> listDisputa;
	
	public SccFiltroDisputa getFiltro() {
		return filtro;
	}

	public void setFiltro(SccFiltroDisputa filtro) {
		this.filtro = filtro;
	}

	public SccDisputa getEntity() {
		return entity;
	}

	public void setEntity(SccDisputa entity) {
		this.entity = entity;
	}

	public Collection<SccDisputa> getListDisputa() {
		return listDisputa;
	}

	public void setListDisputa(Collection<SccDisputa> listDisputa) {
		this.listDisputa = listDisputa;
	}

	
	
	
	
	
}
