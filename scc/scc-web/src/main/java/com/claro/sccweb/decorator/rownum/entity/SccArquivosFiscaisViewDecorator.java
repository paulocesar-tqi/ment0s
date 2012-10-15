/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92031709
 *
 */
public class SccArquivosFiscaisViewDecorator extends RownumDecorator<SccArquivosFiscaisView> {
	

	private SccArquivosFiscaisView entity;

	private String cdCSP;
	private String sgUF;
	private String anoCiclo;
	private String mesCiclo;
	private String codigoCiclo;
	private String noArquivo;
	private String noDiretorioArquivo;
	private String dtStatusArquivo;
	private String cdStatusArquivo;
	private String cdMotivoRejeicaoArquivo;
	private String dtConnect;
	private String qtRegistros;
	private String vlBrutoArquivo;
	private String vlICMS;
	
	public SccArquivosFiscaisViewDecorator(SccArquivosFiscaisView entity,
			int rownum) {
		super(entity, rownum);
		formatarCampos(entity);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	private void formatarCampos(SccArquivosFiscaisView entity){

		this.setCdCSP(entity.getCdCSP());
		this.setSgUF(entity.getSgUF());
		this.setAnoCiclo(entity.getAnoCiclo());
		this.setMesCiclo(formataInteger(entity.getMesCiclo()));
		this.setCodigoCiclo(formataInteger(entity.getCodigoCiclo()));
		this.setNoArquivo(entity.getNoArquivo());
		this.setNoDiretorioArquivo(entity.getNoDiretorioArquivo());
		this.setDtStatusArquivo(formataDate(entity.getDtStatusArquivo()));
		this.setCdStatusArquivo(entity.getCdStatusArquivo());
		this.setCdMotivoRejeicaoArquivo(entity.getCdMotivoRejeicaoArquivo());
		this.setDtConnect(formataDate(entity.getDtConnect()));
		this.setQtRegistros(formataInteger(entity.getQtRegistros()));
		this.setVlBrutoArquivo(formataDouble(entity.getVlBrutoArquivo()));
		this.setVlICMS(formataDouble(entity.getVlICMS()));
		
	}

	public SccArquivosFiscaisView getEntity() {
		return entity;
	}

	public void setEntity(SccArquivosFiscaisView entity) {
		this.entity = entity;
	}

	public String getCdCSP() {
		return cdCSP;
	}

	public void setCdCSP(String cdCSP) {
		this.cdCSP = cdCSP;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getAnoCiclo() {
		return anoCiclo;
	}

	public void setAnoCiclo(String anoCiclo) {
		this.anoCiclo = anoCiclo;
	}

	public String getMesCiclo() {
		return mesCiclo;
	}

	public void setMesCiclo(String mesCiclo) {
		this.mesCiclo = mesCiclo;
	}

	public String getCodigoCiclo() {
		return codigoCiclo;
	}

	public void setCodigoCiclo(String codigoCiclo) {
		this.codigoCiclo = codigoCiclo;
	}

	public String getNoArquivo() {
		return noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}

	public String getNoDiretorioArquivo() {
		return noDiretorioArquivo;
	}

	public void setNoDiretorioArquivo(String noDiretorioArquivo) {
		this.noDiretorioArquivo = noDiretorioArquivo;
	}

	public String getDtStatusArquivo() {
		return dtStatusArquivo;
	}

	public void setDtStatusArquivo(String dtStatusArquivo) {
		this.dtStatusArquivo = dtStatusArquivo;
	}

	public String getCdStatusArquivo() {
		return cdStatusArquivo;
	}

	public void setCdStatusArquivo(String cdStatusArquivo) {
		this.cdStatusArquivo = cdStatusArquivo;
	}

	public String getCdMotivoRejeicaoArquivo() {
		return cdMotivoRejeicaoArquivo;
	}

	public void setCdMotivoRejeicaoArquivo(String cdMotivoRejeicaoArquivo) {
		this.cdMotivoRejeicaoArquivo = cdMotivoRejeicaoArquivo;
	}

	public String getDtConnect() {
		return dtConnect;
	}

	public void setDtConnect(String dtConnect) {
		this.dtConnect = dtConnect;
	}

	public String getQtRegistros() {
		return qtRegistros;
	}

	public void setQtRegistros(String qtRegistros) {
		this.qtRegistros = qtRegistros;
	}

	public String getVlBrutoArquivo() {
		return vlBrutoArquivo;
	}

	public void setVlBrutoArquivo(String vlBrutoArquivo) {
		this.vlBrutoArquivo = vlBrutoArquivo;
	}

	public String getVlICMS() {
		return vlICMS;
	}

	public void setVlICMS(String vlICMS) {
		this.vlICMS = vlICMS;
	}
	
}
