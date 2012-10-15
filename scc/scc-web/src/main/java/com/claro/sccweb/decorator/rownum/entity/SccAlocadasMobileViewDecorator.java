/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccAlocadasMobileView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccAlocadasMobileViewDecorator extends RownumDecorator<SccAlocadasMobileView> {
	

	private SccAlocadasMobileView entity;

	private String noArquivoReferencia;
	private String dtRelatorio;
	private String cdCiclo;
	private String qtChamadas;
	private String qtMinutoTarifados;
	private String vlLiquido;
	private String dtChamada;

	public SccAlocadasMobileViewDecorator(SccAlocadasMobileView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccAlocadasMobileView entity){

		this.setNoArquivoReferencia(entity.getNoArquivoReferencia());
		this.setDtRelatorio(formataDate(entity.getDtRelatorio()));
		this.setCdCiclo(formataInteger(entity.getCdCiclo()));
		this.setQtChamadas(formataInteger(entity.getQtChamadas()));
		this.setQtMinutoTarifados(formataInteger(entity.getQtMinutoTarifados()));
		this.setVlLiquido(formataDouble(entity.getVlLiquido()));
		this.setDtChamada(formataDate(entity.getDtChamada()));
		
	}

	public SccAlocadasMobileView getEntity() {
		return entity;
	}

	public void setEntity(SccAlocadasMobileView entity) {
		this.entity = entity;
	}

	public String getNoArquivoReferencia() {
		return noArquivoReferencia;
	}

	public void setNoArquivoReferencia(String noArquivoReferencia) {
		this.noArquivoReferencia = noArquivoReferencia;
	}

	public String getDtRelatorio() {
		return dtRelatorio;
	}

	public void setDtRelatorio(String dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}

	public String getCdCiclo() {
		return cdCiclo;
	}

	public void setCdCiclo(String cdCiclo) {
		this.cdCiclo = cdCiclo;
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

	public String getDtChamada() {
		return dtChamada;
	}

	public void setDtChamada(String dtChamada) {
		this.dtChamada = dtChamada;
	}
}
