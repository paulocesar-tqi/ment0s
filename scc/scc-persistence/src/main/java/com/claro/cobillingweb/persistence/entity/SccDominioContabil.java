package com.claro.cobillingweb.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCC_DOMINIO_CONTABIL")
public class SccDominioContabil {

	private static final long serialVersionUID = 1L;
	private String cdDominioContabil;
	private String dsDominioContabil;


    public SccDominioContabil() {
    }


	@Id
	@Column(name="CD_DOMINIO_CONTABIL")
	public String getCdDominioContabil() {
		return this.cdDominioContabil;
	}

	public void setCdDominioContabil(String cdDominioContabil) {
		this.cdDominioContabil = cdDominioContabil;
	}


	@Column(name="DS_DOMINIO_CONTABIL")
	public String getDsDominioContabil() {
		return this.dsDominioContabil;
	}

	public void setDsDominioContabil(String dsDominioContabil) {
		this.dsDominioContabil = dsDominioContabil;
	}



	
}
