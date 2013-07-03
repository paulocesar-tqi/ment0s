/**
 * 
 */
package com.claro.sccweb.form;

import java.util.List;

import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

/**
 * @author 93046251
 *
 */
public class CadastroGrupoCobillingForm extends BaseForm {
	
	private SccGrupoCobilling entity;
	
	private List<SccGrupoCobilling> listGrupo;

	public SccGrupoCobilling getEntity() {
		return entity;
	}

	public void setEntity(SccGrupoCobilling entity) {
		this.entity = entity;
	}

	public List<SccGrupoCobilling> getListGrupo() {
		return listGrupo;
	}

	public void setListGrupo(List<SccGrupoCobilling> listGrupo) {
		this.listGrupo = listGrupo;
	}
	
	
	
	

}
