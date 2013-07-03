/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccAlocadasMobileSemProcessamentoViewDecorator extends RownumDecorator<SccAlocadasMobileSemProcessamentoView> {
	

	private SccAlocadasMobileSemProcessamentoView entity;

	private String noArquivoReferencia;
	private String dtRelatorio;
	private String cdCiclo;
	private String qtChamadas;
	private String qtMinutoTarifados;
	private String vlLiquido;
	private String dtReferencia;

	public SccAlocadasMobileSemProcessamentoViewDecorator(SccAlocadasMobileSemProcessamentoView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccAlocadasMobileSemProcessamentoView entity){

		this.setNoArquivoReferencia(entity.getNoArquivoReferencia());
		this.setDtRelatorio(formataDate(entity.getDtRelatorio()));
		this.setCdCiclo(formataInteger(entity.getCdCiclo()));
		this.setQtChamadas(formataInteger(entity.getQtChamadas()));
		this.setQtMinutoTarifados(formataDouble(entity.getQtMinutoTarifados()));
		this.setVlLiquido(formataDouble(entity.getVlLiquido()));
		this.setDtReferencia(formataDate(entity.getDtReferencia()));
		
	}

	public SccAlocadasMobileSemProcessamentoView getEntity() {
		return entity;
	}

	public void setEntity(SccAlocadasMobileSemProcessamentoView entity) {
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

	public String getDtReferencia() {
		return dtReferencia;
	}

	public void setDtReferencia(String dtReferencia) {
		this.dtReferencia = dtReferencia;
	}
}
