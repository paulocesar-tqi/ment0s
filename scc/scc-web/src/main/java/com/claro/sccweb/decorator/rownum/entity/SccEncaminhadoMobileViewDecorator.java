/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccEncaminhadoMobileView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccEncaminhadoMobileViewDecorator extends RownumDecorator<SccEncaminhadoMobileView> {
	

	private SccEncaminhadoMobileView entity;

	private String dtRelatorio;
	private String noArquivoReferencia;
	private String qtChamadas;
	private String qtMinutoTarifados;
	private String vlLiquido;
	private String nuItem;
	private String sqRelatorioSumarizado;
	private String dtProcClaro;

	public SccEncaminhadoMobileViewDecorator(SccEncaminhadoMobileView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccEncaminhadoMobileView entity){

		this.setDtRelatorio(formataDate(entity.getDtRelatorio()));
		this.setNoArquivoReferencia(entity.getNoArquivoReferencia());
		this.setQtChamadas(formataInteger(entity.getQtChamadas()));
		this.setQtMinutoTarifados(formataDouble(entity.getQtMinutoTarifados()));
		this.setVlLiquido(formataDouble(entity.getVlLiquido()));
		this.setNuItem(formataInteger(entity.getNuItem()));
		this.setSqRelatorioSumarizado(formataInteger(entity.getSqRelatorioSumarizado()));
		this.setDtProcClaro(formataDate(entity.getDtProcClaro()));
		
	}

	public SccEncaminhadoMobileView getEntity() {
		return entity;
	}

	public void setEntity(SccEncaminhadoMobileView entity) {
		this.entity = entity;
	}

	public String getDtRelatorio() {
		return dtRelatorio;
	}

	public void setDtRelatorio(String dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}

	public String getNoArquivoReferencia() {
		return noArquivoReferencia;
	}

	public void setNoArquivoReferencia(String noArquivoReferencia) {
		this.noArquivoReferencia = noArquivoReferencia;
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

	public String getDtProcClaro() {
		return dtProcClaro;
	}

	public void setDtProcClaro(String dtProcClaro) {
		this.dtProcClaro = dtProcClaro;
	}
}
