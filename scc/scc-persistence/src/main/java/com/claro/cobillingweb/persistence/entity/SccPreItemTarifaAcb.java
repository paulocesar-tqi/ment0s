package com.claro.cobillingweb.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SCC_PRE_ITEM_TARIFA_ACB")
public class SccPreItemTarifaAcb {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private SccPreItemTarifaAcbPK id;
	private BigDecimal vlBruto;

    public SccPreItemTarifaAcb() {
    }


	@EmbeddedId
	public SccPreItemTarifaAcbPK getId() {
		return this.id;
	}

	public void setId(SccPreItemTarifaAcbPK id) {
		this.id = id;
	}
	

	@Column(name="VL_BRUTO")
	public BigDecimal getVlBruto() {
		return this.vlBruto;
	}

	public void setVlBruto(BigDecimal vlBruto) {
		this.vlBruto = vlBruto;
	}
	
}
