/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccAssexuadosView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccAssexuadosViewDecorator extends RownumDecorator<SccAssexuadosView> {
	

	private SccAssexuadosView entity;

	private String dtRelatorio;
	private String numeroDeA;
	private String qtChamadas;
	private String qtMinutoTarifados;
	private String vlLiquido;
	private String cdErroReciclagem;
	private String nuItem;
	private String sqRelatorioSumarizado;

	public SccAssexuadosViewDecorator(SccAssexuadosView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccAssexuadosView entity){

		this.setDtRelatorio(formataDate(entity.getDtRelatorio()));
		this.setNumeroDeA(entity.getNumeroDeA());
		this.setQtChamadas(formataInteger(entity.getQtChamadas()));
		this.setQtMinutoTarifados(formataInteger(entity.getQtMinutoTarifados()));
		this.setVlLiquido(formataDouble(entity.getVlLiquido()));
		this.setCdErroReciclagem(entity.getCdErroReciclagem());
		this.setNuItem(formataInteger(entity.getNuItem()));
		this.setSqRelatorioSumarizado(formataInteger(entity.getSqRelatorioSumarizado()));
		
	}

	public SccAssexuadosView getEntity() {
		return entity;
	}

	public void setEntity(SccAssexuadosView entity) {
		this.entity = entity;
	}

	public String getDtRelatorio() {
		return dtRelatorio;
	}

	public void setDtRelatorio(String dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}

	public String getNumeroDeA() {
		return numeroDeA;
	}

	public void setNumeroDeA(String numeroDeA) {
		this.numeroDeA = numeroDeA;
	}

	public String getQtChamadas() {
		return qtChamadas;
	}

	public void setQtChamadas(String qtChamadas) {
		this.qtChamadas = qtChamadas;
	}

	public String getQtMinutoTarifados() {
		return qtMinutoTarifados;
	}

	public void setQtMinutoTarifados(String qtMinutoTarifados) {
		this.qtMinutoTarifados = qtMinutoTarifados;
	}

	public String getVlLiquido() {
		return vlLiquido;
	}

	public void setVlLiquido(String vlLiquido) {
		this.vlLiquido = vlLiquido;
	}

	public String getCdErroReciclagem() {
		return cdErroReciclagem;
	}

	public void setCdErroReciclagem(String cdErroReciclagem) {
		this.cdErroReciclagem = cdErroReciclagem;
	}

	public String getNuItem() {
		return nuItem;
	}

	public void setNuItem(String nuItem) {
		this.nuItem = nuItem;
	}

	public String getSqRelatorioSumarizado() {
		return sqRelatorioSumarizado;
	}

	public void setSqRelatorioSumarizado(String sqRelatorioSumarizado) {
		this.sqRelatorioSumarizado = sqRelatorioSumarizado;
	}	
}
