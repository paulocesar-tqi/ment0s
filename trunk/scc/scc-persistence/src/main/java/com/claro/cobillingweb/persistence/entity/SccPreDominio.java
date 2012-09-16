package com.claro.cobillingweb.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SCC_PRE_DOMINIO")
public class SccPreDominio {

	private static final long serialVersionUID = 1L;
	private SccPreDominioPK id;
	private String dsDominio;

    public SccPreDominio() {
    }


	@EmbeddedId
	public SccPreDominioPK getId() {
		return this.id;
	}

	public void setId(SccPreDominioPK id) {
		this.id = id;
	}
	

	@Column(name="DS_DOMINIO")
	public String getDsDominio() {
		return this.dsDominio;
	}

	public void setDsDominio(String dsDominio) {
		this.dsDominio = dsDominio;
	}
	
}
