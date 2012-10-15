/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccBatimentoFiscalViewDecorator extends RownumDecorator<SccBatimentoFiscalView> {
	

	private SccBatimentoFiscalView entity;

	private String cdCSP;
	private String nomeOperadora;
	private String nomeEmpresa;
	private String cdEOTClaro;
	private String sgUF;
	private String noArquivo;
	private String cdStatusArquivo;
	private String cdCiclo;
	private String vlTotalNF;
	private String dtConnect;
	
	public SccBatimentoFiscalViewDecorator(SccBatimentoFiscalView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccBatimentoFiscalView entity){

		this.setCdCSP(entity.getCdCSP());
		this.setNomeOperadora(entity.getNomeOperadora());
		this.setNomeEmpresa(entity.getNomeEmpresa());
		this.setCdEOTClaro(entity.getCdEOTClaro());
		this.setSgUF(entity.getSgUF());
		this.setNoArquivo(entity.getNoArquivo());
		this.setCdStatusArquivo(entity.getCdStatusArquivo());
		this.setCdCiclo(formataInteger(entity.getCdCiclo()));
		this.setVlTotalNF(formataDouble(entity.getVlTotalNF()));
		this.setDtConnect(formataDate(entity.getDtConnect()));
		
	}

	public SccBatimentoFiscalView getEntity() {
		return entity;
	}

	public void setEntity(SccBatimentoFiscalView entity) {
		this.entity = entity;
	}

	public String getCdCSP() {
		return cdCSP;
	}

	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}

	public String getNomeOperadora() {
		return nomeOperadora;
	}

	public void setNomeOperadora(String nomeOperadora) {
		this.nomeOperadora = nomeOperadora;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCdEOTClaro() {
		return cdEOTClaro;
	}

	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getNoArquivo() {
		return noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}

	public String getCdStatusArquivo() {
		return cdStatusArquivo;
	}

	public void setCdStatusArquivo(String cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
	}

	public String getCdCiclo() {
		return cdCiclo;
	}

	public void setCdCiclo(String cdCiclo) {
		this.cdCiclo = cdCiclo;
	}

	public String getVlTotalNF() {
		return vlTotalNF;
	}

	public void setVlTotalNF(String vlTotalNF) {
		this.vlTotalNF = vlTotalNF;
	}

	public String getDtConnect() {
		return dtConnect;
	}

	public void setDtConnect(String dtConnect) {
		this.dtConnect = dtConnect;
	}
	
}
